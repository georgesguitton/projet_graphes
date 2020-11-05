package Test;

import FloydWarshall.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * The type Test.
 */
public class Test {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        String input = null;
        do {
            System.out.println("Veuillez entrer le numéro du graphe que vous souhaitez traiter, entrez exit si vous voulez arrêter :");
            try {
                input = stdin.readLine();
                System.out.println(input);
                int graphNumber = Integer.parseInt(input);
                Graph g = new Graph("./graphes/Graphe" + input + ".txt");
                System.out.println(g.toString());
                System.out.println(g.getMatrixString(g.getAdjencyMatrix()));
                System.out.println(g.getMatrixString(g.floydWarshall()));
            } catch (IOException | NumberFormatException e) {
                if (e.getClass().equals(NumberFormatException.class)) {
                    if (! "exit".equals(input)) {
                        System.out.println("Veuillez entre un nombre !");
                    }
                } else {
                    System.out.println(e.getMessage());
                }
            }
        } while (! "exit".equals(input));
    }
}