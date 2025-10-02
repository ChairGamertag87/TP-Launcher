package dir.TP5Plus;

public class Environnement {
    public static void main(String[] args) {
        System.out.println("os.arch = " + System.getProperty("os.arch"));
        System.out.println("os.name = " + System.getProperty("os.name"));
        System.out.println("os.version = " + System.getProperty("os.version"));
        System.out.println("file.separator = " + System.getProperty("file.separator"));
        System.out.println("line.separator = " + System.getProperty("line.separator"));
        System.out.println("user.dir = " + System.getProperty("user.dir"));
        System.out.println("user.home = " + System.getProperty("user.home"));
        System.out.println("user.name = " + System.getProperty("user.name"));
        System.out.println("java.class.path = " + System.getProperty("java.class.path"));
        System.out.println("java.home = " + System.getProperty("java.home"));
        System.out.println("java.vendor = " + System.getProperty("java.vendor"));
        System.out.println("java.vendor.url = " + System.getProperty("java.vendor.url"));
        System.out.println("java.version = " + System.getProperty("java.version"));
        System.out.println("path.separator = " + System.getProperty("path.separator"));
        System.getProperties().list(System.out);
    }
}
