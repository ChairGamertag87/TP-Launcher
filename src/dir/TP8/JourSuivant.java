package dir.TP8;

import java.io.FileReader;
import java.util.Scanner;

public class JourSuivant {

    /**
     * Fait saisir une date à l'utilisateur
     *
     * @param pfDate OUT : un tableau de trois cases représentant une
     *               date. 1ere case : jour, 2nde case : mois, 3eme case : annee
     */
    public static void saisieDate(int[] pfDate) {
        if (pfDate.length != 3) {
            System.out.print("Le tableau représentant la date a une taille inattendue : ");
            System.out.println(pfDate.length + " case(s) au lieu de 3 !");
        }
        Scanner clavier = new Scanner(System.in);
        System.out.print("Veuillez saisir le jour : ");
        pfDate[0] = clavier.nextInt();
        System.out.print("Veuillez saisir le mois : ");
        pfDate[1] = clavier.nextInt();
        System.out.print("Veuillez saisir l'année : ");
        pfDate[2] = clavier.nextInt();
    }

    /**
     * Calcule la validité d'une date
     *
     * @param pfDate IN : date initiale
     * @return true si et seulement si pfDate est valide
     */
    public static boolean dateValide(int[] pfDate) {
        if (pfDate.length != 3) return false;

        int jour = pfDate[0];
        int mois = pfDate[1];
        int annee = pfDate[2];

        // Mois valide ?
        if (mois < 1 || mois > 12) return false;

        // Nombre de jours max selon le mois
        int joursMax;
        switch (mois) {
            case 4, 6, 9, 11 -> joursMax = 30;
            case 2 -> {
                if (annee % 400 == 0 || (annee % 4 == 0 && annee % 100 != 0))
                    joursMax = 29; // année bissextile
                else
                    joursMax = 28;
            }
            default -> joursMax = 31;
        }

        // Jour valide ?
        return jour >= 1 && jour <= joursMax;
    }

    /**
     * Calcul du jour suivant
     *
     * @param pfDateJourCourant IN : date initiale
     * @param pfDateJourSuivant OUT : date du jour suivant
     */
    public static void jourSuivant(int[] pfDateJourCourant, int[] pfDateJourSuivant) {
        int jour = pfDateJourCourant[0];
        int mois = pfDateJourCourant[1];
        int annee = pfDateJourCourant[2];

        int joursMax;
        switch (mois) {
            case 4, 6, 9, 11 -> joursMax = 30;
            case 2 -> {
                if (annee % 400 == 0 || (annee % 4 == 0 && annee % 100 != 0))
                    joursMax = 29;
                else
                    joursMax = 28;
            }
            default -> joursMax = 31;
        }

        // Incrément du jour
        jour++;
        if (jour > joursMax) {
            jour = 1;
            mois++;
            if (mois > 12) {
                mois = 1;
                annee++;
            }
        }


        pfDateJourSuivant[0] = jour;
        pfDateJourSuivant[1] = mois;
        pfDateJourSuivant[2] = annee;
    }

    public static void surLedemain(int[] fpDateJour) {
        
    }

    public static void main(String[] args) {

        int[] date = new int[3];
        int[] jourSuivant = new int[3];

        // Étape 1 : saisie de la date
        saisieDate(date);

        // Étape 2 : vérification de la date
        boolean valide = dateValide(date);

        // Étape 3 : calcul et affichage du jour suivant
        if (valide) {
            jourSuivant(date, jourSuivant);
            System.out.println("Le jour suivant le "
                    + date[0] + "/" + date[1] + "/" + date[2]
                    + " est le " + jourSuivant[0] + "/" + jourSuivant[1] + "/" + jourSuivant[2] + ".");
        } else {
            System.out.println("La date du "
                    + date[0] + "/" + date[1] + "/" + date[2]
                    + " n'est pas valide.");
        }
    }
}
