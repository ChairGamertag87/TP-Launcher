package dir.TP7;

import java.util.Scanner;


public class RandomGen {

    public static void main(String[] args) {
        int largeur = -1;
        Scanner sc = new Scanner(System.in);
        System.out.println("Pour N x N tirages aleatoirs : choisissez N ?");
        largeur = sc.nextInt();
        while(largeur < 0){
            System.out.println("Pour N x N tirages aleatoirs ? ");
            largeur = sc.nextInt();
        }

        int hauteur = largeur;
        System.out.println(largeur + " x " + hauteur + " = " + largeur * hauteur + " tirages avec Math.random() :");
        int nb0 = 0, nb1 = 0, nb2 = 0, nb3 = 0, nb4 = 0, nb5 = 0, nb6 = 0, nb7 = 0, nb8 = 0, nb9 = 0;
        String matrice = "";
        for(int indiceLigne = 1; indiceLigne <= hauteur; indiceLigne ++){
            String ligne = "";
            for(int indiceColonne = 1; indiceColonne <= hauteur; indiceColonne ++){
                int tirage =(int) (Math.random() * 10);
                ligne = ligne + tirage;
                switch (tirage) {
                    case 0: nb0++; break;
                    case 1: nb1++; break;
                    case 2: nb2++; break;
                    case 3: nb3++; break;
                    case 4: nb4++; break;
                    case 5: nb5++; break;
                    case 6: nb6++; break;
                    case 7: nb7++; break;
                    case 8: nb8++; break;
                    case 9: nb9++; break;
                }
                System.out.print(tirage);
            }
            System.out.println();

        }
        System.out.println("Totaux :");
        System.out.println("Nombre de 0 : " + nb0);
        System.out.println("Nombre de 1 : " + nb1);
        System.out.println("Nombre de 2 : " + nb2);
        System.out.println("Nombre de 3 : " + nb3);
        System.out.println("Nombre de 4 : " + nb4);
        System.out.println("Nombre de 5 : " + nb5);
        System.out.println("Nombre de 6 : " + nb6);
        System.out.println("Nombre de 7 : " + nb7);
        System.out.println("Nombre de 8 : " + nb8);
        System.out.println("Nombre de 9 : " + nb9);

        sc.close();
        }


    }
