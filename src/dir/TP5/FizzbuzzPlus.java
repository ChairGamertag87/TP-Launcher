import java.util.Scanner;

public class FizzbuzzPlus {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Saisir un nombre ... ?");
        int n1 = sc.nextInt();
        System.out.println("... et son annonce associée ?");
        String a1 = sc.next();

        System.out.println("Saisir un nombre ... ?");
        int n2 = sc.nextInt();
        System.out.println("... et son annonce associée ?");
        String a2 = sc.next();

        int n;
        do {
            System.out.println("Nombre d'annonces " + a1 + a2 + " a afficher (entre 1 et 100) ?");
            n = sc.nextInt();
        } while (n < 1 || n > 100);

        for (int i = 1; i <= n; i++) {
            String res = "";
            if (i % n1 == 0) res += a1;
            if (i % n2 == 0) res += a2;
            if (res.equals("")) res += i;
            System.out.print(res);
            if (i < n) System.out.print(" ");
        }
        System.out.println();
        sc.close();
    }
}
