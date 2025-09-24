package dir.TP3;

import java.util.Scanner;
public class Mention {

    /**
     * permet de retourner une valeur entiere saisie au clavier comprise entre pfBorneInf et pfBorneSup
     * @param pfBorneInf IN borne inférieure
     * @param pfBorneSup IN borne supérieure
     * @return une valeur comprise entre pfBorneInf et pfBorneSup
     */
    public static double saisieC(double pfBorneInf, double pfBorneSup){
        double valeurC;
        Scanner clavier = new Scanner(System.in) ;
        System.out.println("Donnez une valeur comprise entre "+pfBorneInf+" et "+pfBorneSup+ "?");
        valeurC=clavier.nextDouble();
        while (valeurC<pfBorneInf || valeurC>pfBorneSup){
            System.out.println("Erreur ! Donnez une valeur comprise entre "+pfBorneInf+" et "+pfBorneSup+ "?");
            valeurC=clavier.nextDouble();
        }
        return valeurC;
    }

    // Mais que fait ce programme ???   
    public static void calcul() {

        // Declaration des variables
        double note1, note2, note3 ;
        double somme ;
        String mention = " " ;

        // Saisie des 3 notes
        System.out.println("Saisir la note 1");
        note1 = saisieC(0,20);
        System.out.println("Saisir la note 2");
        note2 = saisieC(0,20);
        System.out.println("Saisir la note 3");
        note3 = saisieC(0,20);

        // Calcul de la somme et de la moyenne
        somme = (note1 + note2 + note3)/3 ;
        
        System.out.println(somme);
        
        //Calcul de la mention
        if (somme >= 0 && somme <10) {
            mention = "collé";
        }
        else if (somme >=10 && somme < 12){
            mention = "passable";
        }
        else if (somme >= 12 && somme <14) {
            mention = "assez bien";
        }
        else if (somme >=14 && somme < 16){
            mention = "bien";
        }
        else if (somme >=16 && somme <= 20){
            mention = "très bien";
        }

        //Affichage
        System.out.println("La Moyenne des 3 notes est : "+ somme + "\n" );
        System.out.println("la mention obtenue est : " + mention);

    }
} 