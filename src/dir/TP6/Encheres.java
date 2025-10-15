package dir.TP6;

import java.util.Scanner;

public class Encheres {

    /**
     * Programme principal des enchères
     */
    public static void main(String[] args) {
        Scanner clavier = new Scanner(System.in);

        double[] enchere1 = new double[6];
        for (int i = 0; i < 6; i++) {
            System.out.println("Donnez une enchère : ");
            double valeur = clavier.nextDouble();
            while (i > 0 && valeur <= enchere1[i - 1]) {
                System.out.println("Enchère trop faible, recommencez : ");
                valeur = clavier.nextDouble();
            }
            enchere1[i] = valeur;
        }
        System.out.println("Enchères acceptées : ");
        for (int i = 0; i < 6; i++) {
            System.out.println(enchere1[i]);
        }

        double[] enchere2 = new double[6];
        double somme = 0;
        for (int i = 0; i < 6; i++) {
            System.out.println("Donnez une enchère : ");
            double valeur = clavier.nextDouble();
            while (valeur <= somme) {
                System.out.println("Enchère trop faible, recommencez : ");
                valeur = clavier.nextDouble();
            }
            enchere2[i] = valeur;
            somme += valeur;
        }
        System.out.println("Enchères acceptées : ");
        for (int i = 0; i < 6; i++) {
            System.out.println(enchere2[i]);
        }

        double[] enchere3 = new double[6];
        int nb = 0;
        somme = 0;
        boolean continuer = true;
        while (nb < 6 && continuer) {
            System.out.println("Quelqu’un veut-il enchérir ? (o/n) : ");
            String rep = clavier.next();
            if (rep.equals("o")) {
                System.out.println("Donnez une enchère : ");
                double valeur = clavier.nextDouble();
                while (valeur <= somme) {
                    System.out.println("Enchère trop faible, recommencez : ");
                    valeur = clavier.nextDouble();
                }
                enchere3[nb] = valeur;
                somme += valeur;
                nb++;
            } else {
                continuer = false;
            }
        }
        System.out.println("Enchères acceptées : ");
        for (int i = 0; i < nb; i++) {
            System.out.println(enchere3[i]);
        }
    }
}
