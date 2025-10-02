package dir.TP5;

import java.util.Scanner;

public class Fizzbuzz {
    /**
     * Fonction qui calcule la suite des annonces du jeu FizzBuzz.
     * <p>Il est à noter que cette fonction comporte la précondition
     * suivante : 1 <= nbMax.</p>
     * <p>Si cette inégalité n'est pas respectée, la fonction est
     * libre de faire ce que bon lui semble.</p>
     * @param nbMax le nombre d'annonces au total.
     * @return une chaîne de caractères contenant les annonces, séparées
     *         par des espaces
     */
    public static String genererSuiteFizzBuzz(int nbMax) {
        if (nbMax < 1) return "";
        String res = "";
        for (int i = 1; i <= nbMax; i++) {
            if (i % 3 == 0 && i % 5 == 0) res += "FizzBuzz";
            else if (i % 3 == 0) res += "Fizz";
            else if (i % 5 == 0) res += "Buzz";
            else res += i;
            if (i < nbMax) res += " ";
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n;
        do {
            System.out.println("Nombre d'annonces FizzBuzz à afficher (entre 1 et 100) ?");
            n = sc.nextInt();
        } while (n < 1 || n > 100);
        System.out.println(genererSuiteFizzBuzz(n));
        sc.close();
    }
}

