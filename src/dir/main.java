package dir;
/**
 * TP1
 *
 * HERRARD Clément
 */
public class main
{
 /**
     * @param diametre
     *      Le diametre du disque (nombre positif).
     * @return la surface du disque
     */
    public static double surfaceDisque(double diametre) {
        double pi, rayon, surface;
        
        pi = 3.14;
        rayon = diametre / 2; 
        surface = rayon * rayon * pi;

        return surface;
    }
        /**
     * @param longueur
     *      La longueur du rectangle (nombre positif).
     * @param largeur
     *      La largeur du rectangle (nombre positif, inférieur ou égal à la longueur).
     * @return la surface du rectangle
     */
    public static double surfaceRectangle(double longueur, double largeur) {
        double surface;
    // Endroit pour transcrire l'algorithme en Java
    surface = longueur * largeur;

        return surface;
    }
    
        /**
     * @param cote
     *      La longueur d'un cote du carré (nombre positif).
     * @return la surface du carré
     */
    public static double surfaceCarre(double cote) {
        double surface;
    surface = surfaceRectangle(cote, cote);
        return surface;
    }
    
        /**
     * Surface du gazon, en m2. Toutes les données sont en m.
     * Terrain, maison, appenti et piscine s'organisent comme vu en TD.
     */
    public static double surfaceGazon(double longueurTerrain, double largeurTerrain,
    double diametrePiscine, double longueurMaison, double largeurMaison, double largeurAppenti) {
        double surfaceG;

        surfaceG = surfaceRectangle(longueurTerrain, largeurTerrain) - surfaceRectangle(largeurMaison, longueurMaison) - (surfaceRectangle(largeurMaison, largeurAppenti))/2;
        surfaceG = surfaceG - surfaceDisque(diametrePiscine);
        
        // (10 * 5) - 4*3 - (3*2)/2 - 2pi = 28.7168
        // (6 * 4) - 9*2 - 2*4/2 - 3pi = −7.4248 
       
     
        return surfaceG;
    }
        /**
     * Affiche les informations sur la tonte du gazon. Toutes les données sont en m.
     */
    public static void informationTonte(double longueurTerrain, double largeurTerrain,
    double diametrePiscine, double longueurMaison, double largeurMaison, double largeurAppenti) {
	/* déclaration des variables de travail */
	// à vous !
	double surface, vitesse, duree;

	/* Calcul */
	// à vous !
	vitesse = 100.0;
	surface = surfaceGazon(longueurTerrain, largeurTerrain, diametrePiscine, longueurMaison, largeurMaison, largeurAppenti); 
	duree = surface / vitesse;
	
	/* Affichage */
	// à vous !
	System.out.println("Il y a " + surface + " m2 à tondre");
	System.out.println("Cela prendra : " + duree + "heures");
	
    }
    public static double oval(double d1, double d2) {
        double pi, surface;
        /* d1 , diagonal dans la longueur, d2 diagonal dans la largeur de l'oval */
        pi = 3.14;
        surface = (d1/2) * (d2/2) * pi;

        return surface;
    }
    
}

