package Test;

import FloydWarshall.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        String input = null;
        do {
            System.out.println("Veuillez entrer le numéro du graphe que vous souhaitez traiter, entrez 'exit' si vous voulez arrêter :");
            try {
                input = stdin.readLine();
                System.out.println(input);
                int graphNumber = Integer.parseInt(input);
            } catch (IOException | NumberFormatException e) {
                if (e.getClass().equals(NumberFormatException.class)) {
                    if (! "exit".equals(input)) {
                        System.out.println("Veuillez entre un nombre !");
                    }
                } else {
                    System.out.println(e.getMessage());
                }
            }
            Graph g = new Graph("./graphes/Graphe" + input + ".txt");
            double[][] weightAdjencyMatrix = g.getWeightAdjencyMatrix();
            double[][] nextAdjencyMatrix = g.getNextAdjencyMatrix();
            List<double[][]> floydWarshall = g.floydWarshall();
            System.out.println(g.toString());
            System.out.println(g.getMatrixString(weightAdjencyMatrix));
            System.out.println(g.getMatrixString(nextAdjencyMatrix));
            System.out.println(g.getMatrixString(floydWarshall.get(0)));
            System.out.println(g.getMatrixString(floydWarshall.get(1)));
            String pathInput = null;
            do {
                System.out.println("Voulez-vous afficher tous les chemins, un chemin particulier ou arrêter ? (Saisir '1', '2' ou 'stop')");
                try {
                    pathInput = stdin.readLine();
                    System.out.println(pathInput);
                    if ("1".equals(pathInput)) {
                        System.out.println(g.getAllPaths(nextAdjencyMatrix));
                    } else if ("2".equals(pathInput)) {

                    } else if (! "stop".equals(pathInput)) {
                        System.out.println("Veuillez saisir '1', '2' ou 'stop' !");
                    }
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            } while (! "stop".equals(pathInput));
        } while (! "exit".equals(input));
    }
}