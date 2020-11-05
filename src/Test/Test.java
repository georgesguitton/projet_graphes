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
            } catch (IOException | NumberFormatException e) {
                if (e.getClass().equals(NumberFormatException.class)) {
                    System.out.println("Veuillez entre un nombre !");
                } else {
                    System.out.println(e.getMessage());
                }
            }
            if (! "exit".equals(input)) {
                Graph g = new Graph("./graphes/Graphe" + input + ".txt");
                int[][] weightAdjencyMatrix = g.getWeightAdjencyMatrix();
                int[][] nextAdjencyMatrix = g.getNextAdjencyMatrix();
                List<int[][]> floydWarshall = g.floydWarshall();
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
                        if ("1".equals(pathInput)) {
                            System.out.println(g.getAllPaths(floydWarshall.get(1)));
                        } else if ("2".equals(pathInput)) {
                            System.out.println("Veuillez saisir le noeud de départ :");
                            int initialNodeId = Integer.parseInt(stdin.readLine());
                            System.out.println("Veuillez saisir le noeud d'arrivé' :");
                            int finalNodeId = Integer.parseInt(stdin.readLine());
                            if (initialNodeId < 0 || initialNodeId > g.getNodesNumber() - 1 || finalNodeId < 0 || finalNodeId > g.getNodesNumber() - 1) {
                                System.out.println("Veuillez saisir des ids de noeuds corrects !");
                            } else {
                                System.out.println(g.getPath(initialNodeId, finalNodeId, floydWarshall.get(1)));
                            }
                        } else if (! "stop".equals(pathInput)) {
                            System.out.println("Veuillez saisir '1', '2' ou 'stop' !");
                        }
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                } while (! "stop".equals(pathInput));
            }
        } while (! "exit".equals(input));
    }
}