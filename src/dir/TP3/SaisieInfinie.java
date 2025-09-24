package dir.TP3;

import java.util.Scanner;

public class SaisieInfinie {

    public static void versLInfini() {
        // Déclaration des variables
        Scanner clavier = new Scanner(System.in);
        int saisie ;
        String message = "Entrer un nombre entier ?" ;
        int compteur ;

        // Traitements
        compteur = 10 ;
        while ( compteur > 0 ){
            System.out.println(message);
            saisie = clavier.nextInt() ;
        }	

        // Affichages
        System.out.println("Vous ne verrez jamais ce OUPS");
    }

    public static void exemple1(){
        // Déclaration des variables
        Scanner clavier = new Scanner(System.in);
        int saisie ;
        String message = "Entrer un nombre entier ?" ;
        int compteur ;
        // Traitements
        compteur = 10 ;
        while ( compteur > 0 ){
            System.out.println(message);
            saisie = clavier.nextInt() ;
            compteur = compteur + 1 ;
        }
        // Affichages
        System.out.println("Vous ne verrez jamais ce OUPS");
    }
    public static void exemple2(){
        // Déclaration des variables
        Scanner clavier = new Scanner(System.in);
        int saisie ;
        String message = "Entrer un nombre entier ?" ;
        int compteur ;
        // Traitements
	compteur = 0 ;
	while ( compteur > 0 ){
		System.out.println(message);
		saisie = clavier.nextInt() ;
		compteur = compteur - 1 ;
	}
        compteur = 10 ;
        while ( compteur > 0 ){
            System.out.println(message);
            saisie = clavier.nextInt() ;
            compteur = compteur + 1 ;
        }
        // Affichages
        System.out.println("Vous ne verrez jamais ce OUPS");
    }
    public static void exemple3(){
        // Déclaration des variables
        Scanner clavier = new Scanner(System.in);
        int saisie ;
        String message = "Entrer un nombre entier ?" ;
        int compteur ;
        // Traitements
	compteur = 0 ;
	while ( compteur < 11 ){
		System.out.println(message);
		saisie = clavier.nextInt() ;
		compteur = (compteur + 1) % 10 ;
	}
        // Affichages
        System.out.println("Vous ne verrez jamais ce OUPS");
    }
}