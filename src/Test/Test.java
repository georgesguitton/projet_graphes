package Test;

import FloydWarshall.Graph;

public class Test {
    public static void main(String[] args) {
        Graph g = new Graph("./graphes/Graphe1.txt");
        System.out.println(g.toString());
        System.out.println(g.getMatrixString(g.getAdjencyMatrix()));
        System.out.println(g.getMatrixString(g.floydWarshall()));
    }
}