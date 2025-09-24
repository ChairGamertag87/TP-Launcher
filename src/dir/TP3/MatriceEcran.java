package dir.TP3;

public class MatriceEcran {

    /**
     *
     * @return une chaîne de caractères représentant une matrice
     */
    public static String genererStringMatrice() {
        int hauteur;
        String matrice;
        int compteurLigne;
        hauteur = 10;
        matrice = "";
        compteurLigne = hauteur;
        while (compteurLigne > 0){
            matrice = matrice + genererStringLigne() + "\n";
            compteurLigne = compteurLigne - 1;
        }
        return matrice;

    }
    /**
     *
     * @return une chaîne de caractères représentant une ligne de la matrice
     */
    public static String genererStringLigne() {
        int largeur;
        String ligne;
        int compteurColonne;

        largeur = 10;
        ligne = "";
        compteurColonne = largeur;
        while (compteurColonne > 0){
            ligne = ligne + "*";
            compteurColonne = compteurColonne - 1;
        }
        return ligne;
    }

    /**
     *
     * Affiche une matrice à l'écran
     */
    public static void printMatrice() {
        System.out.println(genererStringMatrice());
    }
}