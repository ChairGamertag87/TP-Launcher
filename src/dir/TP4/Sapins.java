package dir.TP4;

public class Sapins
{
    public static String genererStringLigne (int nb, char car){
        String resultat = "";
        for (int i = 0; i < nb; i++) {
            resultat += car;
        }
        return resultat;
    }

    public static String genererSapinPlein (int hauteur){
        String resultat = "";
        for (int i = 0; i < hauteur; i++) {
            resultat += genererStringLigne(hauteur - i - 1, ' ');
            resultat += genererStringLigne(2 * i + 1, '*');
            resultat += "\n";
        }
        return resultat;
    }

    public static String genererSapinVide (int hauteur){
        String resultat = "";
        for (int i = 0; i < hauteur; i++) {
            resultat += genererStringLigne(hauteur - i - 1, ' ');
            if (i == 0) {
                resultat += "*";
            } else if (i == hauteur - 1) {
                resultat += genererStringLigne(2 * i + 1, '*');
            } else {
                resultat += "*";
                resultat += genererStringLigne(2 * i - 1, ' ');
                resultat += "*";
            }
            resultat += "\n";
        }
        return resultat;
    }

    public static String genererSapinCouche (int hauteur){
        String resultat = "";
        for (int i = 1; i <= hauteur; i++) {
            resultat += genererStringLigne(i, '*') + "\n";
        }
        for (int i = hauteur - 1; i >= 1; i--) {
            resultat += genererStringLigne(i, '*') + "\n";
        }
        return resultat;
    }

    public static String genererStringMatriceEcranBarre(int taille){
        String resultat = "";
        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                if (i == 0 || j == 0 || i == taille - 1 || j == taille - 1) {
                    resultat += "*";
                } else if (i == j || i + j == taille - 1) {
                    resultat += "*";
                } else {
                    resultat += "0";
                }
            }
            resultat += "\n";
        }
        return resultat;
    }

}

