package dir.TP9;
import java.util.Scanner;

public class AlgosTableaux {

    public static int saisieIntC(int pfBorneInf, int pfBorneSup) {
        int valeur;
        Scanner clavier = new Scanner(System.in);
        System.out.println("Donnez une valeur comprise entre " + pfBorneInf + " et " + pfBorneSup + "?");
        valeur = clavier.nextInt();
        while (valeur < pfBorneInf || valeur > pfBorneSup) {
            System.out.println("Erreur ! Donnez une valeur comprise entre " + pfBorneInf + " et " + pfBorneSup + "?");
            valeur = clavier.nextInt();
        }
        return valeur;
    }

    public static int saisirTableau(int[] pfTab) {
        Scanner clavier = new Scanner(System.in);
        System.out.println("Combien de valeurs souhaitez-vous saisir (1 à " + pfTab.length + ")?");
        int nbEl = saisieIntC(1, pfTab.length);
        for (int i = 0; i < nbEl; i++) {
            System.out.print("Valeur " + (i + 1) + ": ");
            pfTab[i] = clavier.nextInt();
        }
        System.out.println("J'avais " + pfTab.length + " cases disponibles, et j'en ai rempli " + nbEl);
        return nbEl;
    }

    public static void afficherTableau(int[] pfTab, int pfNbEl) {
        for (int i = 0; i < pfNbEl; i++) {
            System.out.print(pfTab[i] + " ");
        }
        System.out.println();
    }

    public static void inverserTableau(int[] pfTab, int pfNbEl) {
        int debut = 0;
        int fin = pfNbEl - 1;
        while (debut < fin) {
            int tmp = pfTab[debut];
            pfTab[debut] = pfTab[fin];
            pfTab[fin] = tmp;
            debut++;
            fin--;
        }
    }

    public static void eliminerDoublons(int[] pfTab, int pfNbEl) {
        int[] temp = new int[pfNbEl];
        int nbUnique = 0;
        for (int i = 0; i < pfNbEl; i++) {
            boolean existe = false;
            for (int j = 0; j < nbUnique; j++) {
                if (pfTab[i] == temp[j]) {
                    existe = true;
                    break;
                }
            }
            if (!existe) {
                temp[nbUnique] = pfTab[i];
                nbUnique++;
            }
        }
        afficherTableau(temp, nbUnique);
    }

    public static int indicesMax(int[] pfTab, int pfNbEl, int[] pfIndices) {
        int max = pfTab[0];
        for (int i = 1; i < pfNbEl; i++) {
            if (pfTab[i] > max) {
                max = pfTab[i];
            }
        }
        int nbOcc = 0;
        for (int i = 0; i < pfNbEl; i++) {
            if (pfTab[i] == max) {
                pfIndices[nbOcc] = i;
                nbOcc++;
            }
        }
        return nbOcc;
    }

    public static void eliminerDoublonsbis(int[] pfTab, int pfNbEl) {
        int nbUnique = 0;
        for (int i = 0; i < pfNbEl; i++) {
            boolean existe = false;
            for (int j = 0; j < nbUnique; j++) {
                if (pfTab[i] == pfTab[j]) {
                    existe = true;
                    break;
                }
            }
            if (!existe) {
                pfTab[nbUnique] = pfTab[i];
                nbUnique++;
            }
        }
        afficherTableau(pfTab, nbUnique);
    }

    public static int indicesMaxbis(int[] pfTab, int pfNbEl, int[] pfIndices) {
        int max = pfTab[0];
        int nbOcc = 1;
        pfIndices[0] = 0;
        for (int i = 1; i < pfNbEl; i++) {
            if (pfTab[i] > max) {
                max = pfTab[i];
                nbOcc = 0;
                pfIndices[nbOcc] = i;
                nbOcc++;
            } else if (pfTab[i] == max) {
                pfIndices[nbOcc] = i;
                nbOcc++;
            }
        }
        return nbOcc;
    }

    public static void main(String[] args) {
        int[] tab = new int[100];
        int nbVal = saisirTableau(tab);
        afficherTableau(tab, nbVal);

        inverserTableau(tab, nbVal);
        System.out.println("Tableau inversé :");
        afficherTableau(tab, nbVal);

        System.out.println("Tableau sans doublons :");
        eliminerDoublons(tab, nbVal);

        int[] indices = new int[100];
        int nbOcc = indicesMax(tab, nbVal, indices);
        System.out.println("Indices du maximum :");
        afficherTableau(indices, nbOcc);

        System.out.println("Tableau sans doublons (optimisé) :");
        eliminerDoublonsbis(tab, nbVal);

        System.out.println("Indices du maximum (optimisé) :");
        nbOcc = indicesMaxbis(tab, nbVal, indices);
        afficherTableau(indices, nbOcc);
    }
}
