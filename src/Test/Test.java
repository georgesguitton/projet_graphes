package Test;

import FloydWarshall.Graph;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

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

        // Strings pour l'affichage dans le fichier txt des traces d'execution
        String separation = "---------------------------";
        String graphSelec = "Voici le graphe sélectionné :";
        String matPoids = "Voici matrice de poids des arcs du graphe :";
        String matAdj = "Voici la matrice d'adjacence du graphe :";
        String matPoids2 = "Voici la matrice de poids des arcs après Floyd-Warshall :";
        String matAdj2 = "Voici la matrice d'adjacence du graphe après Floyd-Warshall:";

        do
        {
            System.out.println("Veuillez entrer le numéro du graphe que vous souhaitez traiter, entrez 'exit' si vous voulez arrêter :");
            try
            {
                input = stdin.readLine();
            }
            catch (IOException | NumberFormatException e)
            {
                if (e.getClass().equals(NumberFormatException.class))
                {
                    System.out.println("Veuillez entrer un nombre !");
                }
                else
                {
                    System.out.println(e.getMessage());
                }
            }
            // Si l'utilisateur décide de poursuivre
            if (! "exit".equals(input))
            {
                Graph g = new Graph("./graphes/Graphe" + input + ".txt");
                File TestGraphExiste = new File("./graphes/graphe" + input + ".txt");
                int[][] weightAdjencyMatrix = g.getWeightAdjencyMatrix();
                int[][] nextAdjencyMatrix = g.getNextAdjencyMatrix();
                List<int[][]> floydWarshall = g.floydWarshall();
                // Si le fichier que l'utilisateur rentre n'existe pas
                if(TestGraphExiste.exists())
                {
                    Path Execution = Paths.get("./graphes/execution.txt");
                    List<String> matrices = new ArrayList<>();
                    String nomGraphe = "Graphe " + input;
                    matrices.add(nomGraphe);

                    // Affichage du graphe
                    System.out.println(graphSelec);
                    System.out.println(separation);
                    System.out.println(g.toString());
                    matrices.add(separation);
                    matrices.add(graphSelec);
                    matrices.add(separation);
                    matrices.add(g.toString());

                    // Affichage de la matrice de poids des arcs
                    System.out.println(separation);
                    System.out.println(matPoids);
                    System.out.println(separation);
                    System.out.println(g.getMatrixString(weightAdjencyMatrix));
                    matrices.add(separation);
                    matrices.add(matPoids);
                    matrices.add(separation);
                    matrices.add(g.getMatrixString(weightAdjencyMatrix));

                    // Affichage de la matrice d'adjacence
                    System.out.println(separation);
                    System.out.println(matAdj);
                    System.out.println(separation);
                    System.out.println(g.getMatrixString(nextAdjencyMatrix));
                    matrices.add(separation);
                    matrices.add(matAdj);
                    matrices.add(separation);
                    matrices.add(g.getMatrixString(nextAdjencyMatrix));

                    // Affichage de la matrice de poids des arcs après Floyd-Warshall
                    System.out.println(separation);
                    System.out.println(matPoids2);
                    System.out.println(separation);
                    System.out.println(g.getMatrixString(floydWarshall.get(0)));
                    matrices.add(separation);
                    matrices.add(matPoids2);
                    matrices.add(separation);
                    matrices.add(g.getMatrixString(floydWarshall.get(0)));

                    // Affichage de la matrice d'adjacence après Floyd-Warshall
                    System.out.println(separation);
                    System.out.println(matAdj2);
                    System.out.println(separation);
                    System.out.println(g.getMatrixString(floydWarshall.get(1)));
                    matrices.add(separation);
                    matrices.add(matAdj2);
                    matrices.add(separation);
                    matrices.add(g.getMatrixString(floydWarshall.get(1)));

                    try
                    {
                        Files.write(Execution, matrices, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                    String pathInput = null;
                    do
                    {
                        System.out.println("Voulez-vous afficher tous les chemins, un chemin particulier ou arrêter ? (Saisir '1', '2' ou 'stop')");
                        try
                        {
                            pathInput = stdin.readLine();
                            // Si l'utilisateur demande à afficher tous les chemins
                            if (pathInput.equals("1"))
                            {
                                List<String> AllPaths = new ArrayList<>();

                                System.out.println("Voici tous les chemins trouvés avec l'algorithme de Floyd-Warshall : \n");
                                System.out.println(g.getAllPaths(floydWarshall.get(1)));
                                AllPaths.add("Voici tous les chemins trouvés avec l'algorithme de Floyd-Warshall : \n");
                                AllPaths.add(g.getAllPaths(floydWarshall.get(1)));

                                try
                                {
                                    Files.write(Execution, AllPaths, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
                                }
                                catch (IOException e)
                                {
                                    e.printStackTrace();
                                }

                            }
                            // Si l'utilisateur demande à afficher un chemin en particulier
                            else if ("2".equals(pathInput))
                            {
                                List<String> SinglePath = new ArrayList<>();

                                System.out.println("Veuillez saisir le noeud de départ :");
                                int initialNodeId = Integer.parseInt(stdin.readLine());
                                SinglePath.add("Le noeud de départ est : " + initialNodeId);

                                System.out.println("Veuillez saisir le noeud d'arrivée' :");
                                int finalNodeId = Integer.parseInt(stdin.readLine());
                                SinglePath.add("Le noeud d'arrivée est : " + finalNodeId);

                                // Si les numéros sont incorrects
                                if (initialNodeId < 0 || initialNodeId > g.getNodesNumber() - 1 || finalNodeId < 0 || finalNodeId > g.getNodesNumber() - 1)
                                {
                                    System.out.println("Veuillez saisir des ids de noeuds corrects !");
                                    SinglePath.add("Ids de noeuds incorrects !");
                                }
                                else
                                {
                                    System.out.println(g.getPath(initialNodeId, finalNodeId, floydWarshall.get(1)));
                                    SinglePath.add("Voici le chemin : " + g.getPath(initialNodeId, finalNodeId, floydWarshall.get(1)));
                                }
                                SinglePath.add(separation);

                                try
                                {
                                    Files.write(Execution, SinglePath, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
                                }
                                catch (IOException e)
                                {
                                    e.printStackTrace();
                                }
                            }
                            else if (! "stop".equals(pathInput))
                            {
                                System.out.println("Veuillez saisir '1', '2' ou 'stop' !");
                            }
                        }
                        catch (IOException e)
                        {
                            System.out.println(e.getMessage());
                        }
                    }
                    while (! "stop".equals(pathInput));
                }
                // Si le fichier n'existe pas
                else
                {
                    System.out.println("Veuillez saisir un graphe existant !");
                }
            }
        }
        while (! "exit".equals(input));
    }
}