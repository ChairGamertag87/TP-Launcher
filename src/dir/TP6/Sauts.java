package dir.TP6;

import java.util.Scanner;

public class Sauts {

    public static int saisieIntMinMax(int pfMin, int pfMax) {
        int saisie;
        Scanner clavier = new Scanner(System.in);

        System.out.println("Donnez une valeur comprise entre " + pfMin + " et " + pfMax + " :");
        saisie = clavier.nextInt();
        while (saisie < pfMin || saisie > pfMax) {
            System.out.println("Donnez une valeur comprise entre " + pfMin + " et " + pfMax + " :");
            saisie = clavier.nextInt();
        }
        return saisie;
    }

    public static int saisieIntMin(int pfMin) {
        int saisie;
        Scanner clavier = new Scanner(System.in);
        System.out.println("Donnez une valeur au moins égale à " + pfMin + " :");
        saisie = clavier.nextInt();
        while (saisie < pfMin) {
            System.out.println("Donnez une valeur au moins égale à " + pfMin + " :");
            saisie = clavier.nextInt();
        }
        return saisie;
    }

    public static double saisieDoubleMin(double pfMin) {
        double saisie;
        Scanner clavier = new Scanner(System.in);
        System.out.println("Donnez une valeur au moins égale à " + pfMin + " :");
        saisie = clavier.nextDouble();
        while (saisie < pfMin) {
            System.out.println("Donnez une valeur au moins égale à " + pfMin + " :");
            saisie = clavier.nextDouble();
        }
        return saisie;
    }

    public static void saisieTabD(int pfNbSaisies, double pfTab[]) {
        int i;
        for (i = 0; i < pfNbSaisies; i++) {
            System.out.println("Valeur n°" + (i + 1) + " :");
            pfTab[i] = saisieDoubleMin(0);
        }
    }

    public static double moyenneSauts(int pfNbSauts, double pfTab[]) {
        if (pfNbSauts == 0) {
            return 0.0;
        }
        double somme = 0.0;
        for (int i = 0; i < pfNbSauts; i++) {
            somme += pfTab[i];
        }
        return somme / pfNbSauts;
    }

    public static double meilleurSaut(int pfNbSauts, double pfTab[]) {
        double max = pfTab[0];
        for (int i = 1; i < pfNbSauts; i++) {
            if (pfTab[i] > max) {
                max = pfTab[i];
            }
        }
        return max;
    }

    public static double pireSaut(int pfNbSauts, double pfTab[]) {
        double min = pfTab[0];
        for (int i = 1; i < pfNbSauts; i++) {
            if (pfTab[i] < min) {
                min = pfTab[i];
            }
        }
        return min;
    }

    public static void main(String[] args) {

        int nbSauts = saisieIntMinMax(0, 15);
        double[] tab = new double[nbSauts];
        saisieTabD(nbSauts, tab);

        double moyenne = moyenneSauts(nbSauts, tab);
        System.out.println("Moyenne des sauts : " + moyenne);

        if (nbSauts > 0) {
            double meilleur = meilleurSaut(nbSauts, tab);
            System.out.println("Meilleur saut : " + meilleur);

            double pire = pireSaut(nbSauts, tab);
            System.out.println("Pire saut : " + pire);
        }
    }
}
