package dir.TP2;

import java.util.Scanner ;


public class estValide {
    public static void saisieCalculAffichageValidite() {
        System.out.println("saisis une date");
        int jour = saisieEntier();
        int mois = saisieEntier();
        int an = saisieEntier();

        boolean validEst = verif(jour,mois,an);

        if (validEst)
            System.out.println("la date : " + jour + "/" + mois + "/" + an + "est valide");
        else
            System.out.println("la date : " + jour + "/" + mois + "/" + an + "n'est pas valide");
    }

    public static boolean verif(int jour, int mois, int an){
        int jourMax = jour_max_mois(mois, an);
        if (an < 1582)
            return false;
        else if (mois < 1 || mois > 12)
            return false;
        else if (jour < 1)
            return false;
        else if (jour > jourMax)
            return false;
        else
            return true;

    }

    public static int jour_max_mois(int mois, int an) {
        if (mois == 2) {
            if (moisbissextile(an)) {
                return 29;
            } else {
                return 28;
            }
        } else if (mois == 1 || mois == 3 || mois == 5 || mois == 7 || mois == 8 || mois == 10 || mois == 12) {
            return 31;
        } else if (mois == 4 || mois == 6 || mois == 9 || mois == 11) {
            return 30;
        } else {
            return 0;
        }
    }

    public static boolean moisbissextile(int an){
        if ( an % 400 == 0 || an % 4 == 0 && an % 100 != 0)
            return true;
        else
            return false;
    }

    public static int saisieEntier() {
        System.out.println("Saisir un entier");
        Scanner clavier = new Scanner(System.in) ;
        int nombreSaisi = clavier.nextInt() ;
        return nombreSaisi ;
    }

    public static void nbJour(){
        int i = 1000;
        double b = 0;
        while(i > 0){
            if (moisbissextile(i) == true){
                b = b + 1;
                i = i - 1;
            }
        }
        System.out.println(365.0 + b / 1000);
    }
}

