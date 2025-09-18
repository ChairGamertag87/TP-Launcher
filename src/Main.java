import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.lang.reflect.*;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.file.*;
import java.util.*;
import java.util.List;

public class Main extends JFrame {

    private static final String PACKAGE_NAME = "dir";

    private JComboBox<String> classSelector;
    private JComboBox<MethodHolder> methodSelector;
    private JPanel paramsPanel;               // champs dynamiques
    private JButton runBtn, refreshBtn;
    private JTextArea outputArea;

    private final Map<String, Class<?>> classCache = new HashMap<>();

    public Main() {
        setTitle("Java Launcher");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(780, 560);
        setLayout(new BorderLayout(10, 10));

        // -------- TOP --------
        JPanel top = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(6,6,6,6);
        c.fill = GridBagConstraints.HORIZONTAL;

        c.gridx=0; c.gridy=0; top.add(new JLabel("Classe :"), c);
        classSelector = new JComboBox<>();
        c.gridx=1; c.gridy=0; c.weightx=1; top.add(classSelector, c);

        c.gridx=0; c.gridy=1; c.weightx=0; top.add(new JLabel("Méthode :"), c);
        methodSelector = new JComboBox<>();
        c.gridx=1; c.gridy=1; c.weightx=1; top.add(methodSelector, c);

        JPanel btns = new JPanel(new FlowLayout(FlowLayout.LEFT,8,0));
        refreshBtn = new JButton("Rafraîchir");
        runBtn = new JButton("Exécuter");
        btns.add(refreshBtn);
        btns.add(runBtn);
        c.gridx=1; c.gridy=2; c.weightx=0; top.add(btns, c);

        add(top, BorderLayout.NORTH);

        // -------- PARAMS (centre haut) --------
        paramsPanel = new JPanel(new GridBagLayout());
        paramsPanel.setBorder(BorderFactory.createTitledBorder("Paramètres"));
        add(paramsPanel, BorderLayout.CENTER);

        // -------- OUTPUT --------
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        JScrollPane scroll = new JScrollPane(outputArea);
        scroll.setBorder(BorderFactory.createTitledBorder("Sortie"));
        add(scroll, BorderLayout.SOUTH);
        ((BorderLayout)getLayout()).setVgap(6);

        // Log vers la zone de texte
        redirectSystemStreams();

        // Actions
        refreshBtn.addActionListener(e -> reloadClasses());
        classSelector.addActionListener(e -> loadMethodsForSelectedClass());
        methodSelector.addActionListener(e -> rebuildParamsPanel());
        runBtn.addActionListener(this::runSelectedMethod);

        reloadClasses();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    /* ===================== Détection du dossier racine compilé ===================== */
    private File detectClassesRoot() {
        try {
            URL rootUrl = Main.class.getProtectionDomain().getCodeSource().getLocation();
            String path = URLDecoder.decode(rootUrl.getPath(), "UTF-8");
            File root = new File(path);
            if (root.isDirectory()) return root;
        } catch (Exception ignored) {}
        return new File("out"); // fallback
    }

    /* ===================== Scan des classes d'un package ===================== */
    private List<String> findClassesInPackage(File classesRoot, String packageName) {
        List<String> result = new ArrayList<>();
        Path base = classesRoot.toPath();
        Path pkgPath = base.resolve(packageName.replace('.', File.separatorChar));
        if (!Files.isDirectory(pkgPath)) return result;

        try (var stream = Files.walk(pkgPath)) {
            stream.filter(p -> p.toString().endsWith(".class"))
                    .forEach(p -> {
                        Path rel = base.relativize(p);
                        String fqcn = rel.toString()
                                .replace(File.separatorChar, '.')
                                .replaceAll("\\.class$", "");
                        if (!fqcn.contains("$")) result.add(fqcn);
                    });
        } catch (IOException e) {
            append("❌ Erreur scan classes: " + e.getMessage() + "\n");
        }
        Collections.sort(result);
        return result;
    }

    /* ===================== Chargement classes & méthodes ===================== */
    private void reloadClasses() {
        classCache.clear();
        classSelector.removeAllItems();
        methodSelector.removeAllItems();
        clearParamsPanel();

        File root = detectClassesRoot();
        List<String> classes = findClassesInPackage(root, PACKAGE_NAME);

        if (classes.isEmpty()) {
            append("ℹ️ Aucune classe trouvée dans '" + PACKAGE_NAME + "'. Compiles-tu bien vers: " + root.getAbsolutePath() + " ?\n");
            return;
        }

        for (String fqcn : classes) {
            try {
                Class<?> cls = Class.forName(fqcn);
                classCache.put(fqcn, cls);
                classSelector.addItem(fqcn);
            } catch (Throwable t) {
                append("⚠️ Impossible de charger " + fqcn + " : " + t.getMessage() + "\n");
            }
        }

        if (classSelector.getItemCount() > 0) {
            classSelector.setSelectedIndex(0);
            loadMethodsForSelectedClass();
        }
    }

    private void loadMethodsForSelectedClass() {
        methodSelector.removeAllItems();
        clearParamsPanel();

        String fqcn = (String) classSelector.getSelectedItem();
        if (fqcn == null) return;
        Class<?> cls = classCache.get(fqcn);
        if (cls == null) return;

        Method[] methods = cls.getMethods(); // publiques (héritées incluses)
        Arrays.sort(methods, Comparator
                .comparing(Method::getName)
                .thenComparingInt(Method::getParameterCount));

        for (Method m : methods) {
            if (m.getDeclaringClass() == Object.class) continue; // allège la liste
            methodSelector.addItem(new MethodHolder(m));
        }
        if (methodSelector.getItemCount() > 0) {
            methodSelector.setSelectedIndex(0);
            rebuildParamsPanel();
        }
    }

    /* ===================== UI paramètres dynamiques ===================== */
    private void clearParamsPanel() {
        paramsPanel.removeAll();
        paramsPanel.revalidate();
        paramsPanel.repaint();
    }

    private void rebuildParamsPanel() {
        clearParamsPanel();
        MethodHolder holder = (MethodHolder) methodSelector.getSelectedItem();
        if (holder == null) return;

        Method m = holder.method;
        Class<?>[] types = m.getParameterTypes();
        String[] names = tryGetParameterNames(m, types.length);

        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(4,8,4,8);
        gc.fill = GridBagConstraints.HORIZONTAL;

        if (types.length == 0) {
            JLabel none = new JLabel("Aucun paramètre.");
            gc.gridx=0; gc.gridy=0; gc.gridwidth=2;
            paramsPanel.add(none, gc);
        } else {
            for (int i = 0; i < types.length; i++) {
                String label = (names[i] != null ? names[i] : ("arg"+i)) + " : " + prettyType(types[i]);
                gc.gridx=0; gc.gridy=i; gc.gridwidth=1; gc.weightx=0;
                paramsPanel.add(new JLabel(label), gc);

                JComponent field = makeFieldForType(types[i]);
                field.setName("argField#" + i);
                gc.gridx=1; gc.gridy=i; gc.weightx=1;
                paramsPanel.add(field, gc);
            }
        }
        paramsPanel.revalidate();
        paramsPanel.repaint();
    }

    private JComponent makeFieldForType(Class<?> t) {
        if (t.isEnum()) {
            JComboBox<String> box = new JComboBox<>();
            for (Object c : t.getEnumConstants()) box.addItem(c.toString());
            return box;
        }
        // Pour les types simples, un JTextField (avec placeholder implicite)
        JTextField tf = new JTextField();
        if (t == boolean.class || t == Boolean.class) tf.setText("true/false");
        return tf;
    }

    private String prettyType(Class<?> t) {
        if (t.isArray()) return prettyType(t.getComponentType()) + "[]";
        return t.getSimpleName();
    }

    private String[] tryGetParameterNames(Method m, int n) {
        // On n’a pas les noms si non compilé avec -parameters ; on renvoie nulls
        String[] arr = new String[n];
        Arrays.fill(arr, null);
        try {
            Parameter[] ps = m.getParameters();
            if (ps.length == n) {
                for (int i = 0; i < n; i++) {
                    if (ps[i].isNamePresent()) arr[i] = ps[i].getName();
                }
            }
        } catch (Throwable ignored) {}
        return arr;
    }

    /* ===================== Exécution ===================== */
    private void runSelectedMethod(ActionEvent e) {
        String fqcn = (String) classSelector.getSelectedItem();
        MethodHolder holder = (MethodHolder) methodSelector.getSelectedItem();

        if (fqcn == null || holder == null) {
            append("⚠️ Sélectionne une classe et une méthode.\n");
            return;
        }

        Method m = holder.method;
        Class<?>[] types = m.getParameterTypes();

        try {
            // Récupérer les valeurs depuis paramsPanel
            Object[] args = new Object[types.length];
            for (int i = 0; i < types.length; i++) {
                JComponent comp = (JComponent) findComponentByName(paramsPanel, "argField#" + i);
                if (comp == null) throw new IllegalStateException("Champ paramètre manquant: " + i);

                Object val;
                if (types[i].isEnum()) {
                    String sel = Objects.toString(((JComboBox<?>) comp).getSelectedItem(), null);
                    val = Enum.valueOf((Class<Enum>) types[i], sel);
                } else {
                    String txt = ((JTextField) comp).getText().trim();
                    val = parseValue(types[i], txt);
                }
                args[i] = val;
            }

            // Cible : instance ou null si static
            Object target = null;
            if (!Modifier.isStatic(m.getModifiers())) {
                target = classCache.get(fqcn).getDeclaredConstructor().newInstance();
            }

            Object res = m.invoke(target, args);
            append("✔ " + fqcn + "." + signature(m) + " exécutée.\n");
            if (m.getReturnType() != void.class) {
                append("→ Résultat : " + String.valueOf(res) + "\n");
            }

        } catch (InvocationTargetException ite) {
            Throwable cause = ite.getTargetException();
            append("❌ Exception de la méthode : " + cause.getClass().getSimpleName() + " - " + cause.getMessage() + "\n");
            StringWriter sw = new StringWriter();
            cause.printStackTrace(new PrintWriter(sw));
            append(sw.toString());
        } catch (Throwable ex) {
            append("❌ Erreur: " + ex.getClass().getSimpleName() + " - " + ex.getMessage() + "\n");
        }
    }

    private String signature(Method m) {
        StringBuilder sb = new StringBuilder();
        sb.append(m.getName()).append('(');
        Class<?>[] ps = m.getParameterTypes();
        for (int i = 0; i < ps.length; i++) {
            if (i>0) sb.append(", ");
            sb.append(prettyType(ps[i]));
        }
        sb.append(')');
        return sb.toString();
    }

    /* ===================== Parsing des valeurs ===================== */
    private Object parseValue(Class<?> targetType, String txt) {
        if (targetType == String.class) return txt;
        if (targetType == int.class || targetType == Integer.class) return Integer.parseInt(txt);
        if (targetType == long.class || targetType == Long.class)   return Long.parseLong(txt);
        if (targetType == double.class || targetType == Double.class) return Double.parseDouble(txt);
        if (targetType == float.class || targetType == Float.class) return Float.parseFloat(txt);
        if (targetType == short.class || targetType == Short.class) return Short.parseShort(txt);
        if (targetType == byte.class || targetType == Byte.class)   return Byte.parseByte(txt);
        if (targetType == boolean.class || targetType == Boolean.class) {
            if (txt.equalsIgnoreCase("true") || txt.equalsIgnoreCase("false")) return Boolean.parseBoolean(txt);
            // tolérance : 1/0, oui/non
            if (txt.equals("1") || txt.equalsIgnoreCase("oui")) return true;
            if (txt.equals("0") || txt.equalsIgnoreCase("non")) return false;
            throw new IllegalArgumentException("Valeur booléenne attendue (true/false, 1/0, oui/non)");
        }
        // Varargs simples sur String/int etc. -> format "a,b,c"
        if (targetType.isArray()) {
            String[] parts = txt.split("\\s*,\\s*");
            Class<?> ct = targetType.getComponentType();
            Object arr = Array.newInstance(ct, parts.length);
            for (int i = 0; i < parts.length; i++) {
                Array.set(arr, i, parseValue(ct, parts[i]));
            }
            return arr;
        }
        throw new UnsupportedOperationException("Type non géré: " + targetType.getName());
    }

    /* ===================== Utilitaires ===================== */
    private Component findComponentByName(Container parent, String name) {
        for (Component comp : parent.getComponents()) {
            if (name.equals(comp.getName())) return comp;
            if (comp instanceof Container) {
                Component c = findComponentByName((Container) comp, name);
                if (c != null) return c;
            }
        }
        return null;
    }

    private void append(String s) {
        SwingUtilities.invokeLater(() -> {
            outputArea.append(s);
            if (!s.endsWith("\n")) outputArea.append("\n");
            outputArea.setCaretPosition(outputArea.getDocument().getLength());
        });
    }

    private void redirectSystemStreams() {
        OutputStream tee = new OutputStream() {
            private final StringBuilder buffer = new StringBuilder();
            @Override public void write(int b) {
                char c = (char) b;
                buffer.append(c);
                if (c == '\n') {
                    append(buffer.toString());
                    buffer.setLength(0);
                }
            }
            @Override public void write(byte[] b, int off, int len) {
                append(new String(b, off, len));
            }
        };
        System.setOut(new PrintStream(tee, true));
        System.setErr(new PrintStream(tee, true));
    }

    private static class MethodHolder {
        final Method method;
        MethodHolder(Method m) { this.method = m; }
        @Override public String toString() {
            StringBuilder sb = new StringBuilder();
            if (Modifier.isStatic(method.getModifiers())) sb.append("[static] ");
            sb.append(method.getName()).append('(');
            Class<?>[] ps = method.getParameterTypes();
            for (int i = 0; i < ps.length; i++) {
                if (i>0) sb.append(", ");
                sb.append(ps[i].getSimpleName());
            }
            sb.append(") : ").append(method.getReturnType().getSimpleName());
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }
}
