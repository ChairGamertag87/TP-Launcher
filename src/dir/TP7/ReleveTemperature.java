package dir.TP7;

import java.util.Scanner;

public class ReleveTemperature {

    public static double moyenne(double[] tab) {
        double somme = 0;
        for (double t : tab) {
            somme += t;
        }
        return somme / tab.length;
    }

    public static double max(double[] tab) {
        double maximum = tab[0];
        for (double t : tab) {
            if (t > maximum) {
                maximum = t;
            }
        }
        return maximum;
    }

    public static double min(double[] tab) {
        double minimum = tab[0];
        for (double t : tab) {
            if (t < minimum) {
                minimum = t;
            }
        }
        return minimum;
    }

    public static boolean appartient(double[] tab, double valeur) {
        for (double t : tab) {
            if (t == valeur) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Combien de relevés de température ? ");
        int nbReleves = sc.nextInt();

        double[] temperatures = new double[nbReleves];

        for (int i = 0; i < nbReleves; i++) {
            System.out.print("Entrer la temperature " + (i + 1) + " : ");
            temperatures[i] = sc.nextDouble();
        }

        System.out.println("Moyenne des températures : " + moyenne(temperatures));
        System.out.println("Température maximale : " + max(temperatures));
        System.out.println("Température minimale : " + min(temperatures));

        System.out.print("Entrer une température à rechercher : ");
        double valeur = sc.nextDouble();
        if (appartient(temperatures, valeur)) {
            System.out.println("La température " + valeur + " a été relevée.");
        } else {
            System.out.println("La température " + valeur + " n'a PAS été relevée.");
        }

        sc.close();
    }
}