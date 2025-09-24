package dir.TP5;
import java.util.Scanner;

public class FinDAnnee {

    public static void main(String[] args) {
        Scanner clavier = new Scanner(System.in);
        int nbJours, mois, annee;
        String nomMois;

        while(true) {
            System.out.println("Saisir un numéro du mois ?");
            mois = clavier.nextInt();
            if(mois > 0 && mois <= 12) {
                break;
            }
            System.out.println("Cette date n'est pas valide.");
        }

        while(true) {
            System.out.println("Saisir une année ?");
            annee = clavier.nextInt();
            if(annee > 0) {
                break;
            }
            System.out.println("Cette date n'est pas valide.");
        }

        for(int m = mois; m <= 12; m++) {
            nomMois = "";
            nbJours = 0;

            switch (m) {
                case 1:
                    nomMois = "Janvier";
                    nbJours = 31;
                    break;
                case 2:
                    nomMois = "Février";
                    if (annee % 400 == 0 || (annee % 4 == 0 && annee % 100 != 0)) {
                        nbJours = 29;
                    } else {
                        nbJours = 28;
                    }
                    break;
                case 3:
                    nomMois = "Mars";
                    nbJours = 31;
                    break;
                case 4:
                    nomMois = "Avril";
                    nbJours = 30;
                    break;
                case 5:
                    nomMois = "Mai";
                    nbJours = 31;
                    break;
                case 6:
                    nomMois = "Juin";
                    nbJours = 30;
                    break;
                case 7:
                    nomMois = "Juillet";
                    nbJours = 31;
                    break;
                case 8:
                    nomMois = "Août";
                    nbJours = 31;
                    break;
                case 9:
                    nomMois = "Septembre";
                    nbJours = 30;
                    break;
                case 10:
                    nomMois = "Octobre";
                    nbJours = 31;
                    break;
                case 11:
                    nomMois = "Novembre";
                    nbJours = 30;
                    break;
                case 12:
                    nomMois = "Décembre";
                    nbJours = 31;
                    break;
            }
            System.out.println(nomMois + " " + annee + ", " + nbJours + " jours");
        }

        clavier.close();
    }
}