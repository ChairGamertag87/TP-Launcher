package dir.TP5Plus;

import java.util.Scanner;

public class Chante99bob {

    public static void main(String[] args) {
        Scanner clavier = new Scanner(System.in);
        System.out.println("Nombre de couplets :");
        int n = clavier.nextInt();

        for (int i = n; i > 0; i--) {
            if (i > 2) {
                System.out.println(i + " bottles of beer on the wall, " + i + " bottles of beer!");
                System.out.println("Take one down, pass it around, " + (i - 1) + " bottles of beer on the wall!");
            } else if (i == 2) {
                System.out.println("2 bottles of beer on the wall, 2 bottles of beer!");
                System.out.println("Take one down, pass it around, one bottle of beer on the wall!");
            } else if (i == 1) {
                System.out.println("One bottle of beer on the wall, one bottle of beer!");
                System.out.println("Take it down, pass it around, no more bottle of beer on the wall!");
            }
            if (i > 1) System.out.println();
        }
        clavier.close();
    }
}

