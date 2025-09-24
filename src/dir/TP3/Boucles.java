package dir.TP3;

import java.util.Scanner;
public class Boucles {
    public static void points() {
	int compteur = 0;
	System.out.print("DEBUT");
	
	while (compteur <= 10 ){
	    System.out.print('.'); 
	    compteur = compteur + 1 ;
	}
	
	System.out.println("FIN");
    }
    
    public static void boum(){
      int compteur = 0;
      while (compteur <= 10){
        System.out.print(compteur + " ");
        compteur = compteur +1;
        }
      System.out.print("BOUM");
    }
    
    public static void boumPair(){
     int compteur = 0;
     while (compteur <= 10){
        System.out.print(compteur + " ");
        compteur = compteur + 2;
        }
      System.out.print("BOUM");
    }
    
    public static void boumPropre(){
     // Déclaration de variables
     String message1 = " ";
     String message2 = " ";
     int compteur = 0;
     // Traitements
     while (compteur <= 10){
         message1 = message1 + " " + compteur;
         compteur = compteur + 1;
        }
     compteur = 0;
     while (compteur <= 10){
         message2 = message2 + " " + compteur;
         compteur = compteur + 2;
        }
     
     //Affichage
     System.out.print(message1 + " " + "BOUM" + "\n");
     System.out.print(message2 + " " + "BOUM" +"\n");
    }
    
  
    
};