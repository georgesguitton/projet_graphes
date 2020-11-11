package FloydWarshall;

import java.io.*;
import java.util.*;

/**
 * The type Graph.
 */
public class Graph {
    private int nodesNumber, EdgesNumber;
    private final List<Edge> EdgeList;

    /**
     * Instantiates a new Graph.
     *
     * @param path the path
     */
    public Graph(String path) {
        EdgeList = new ArrayList<>();
        try {
            File file = new File(path);
            BufferedReader in = new BufferedReader(new FileReader(file));
            String line;

            Map<Integer, Node> nodeMap = new HashMap<>();

            if ((line = in.readLine()) != null) {
                this.nodesNumber = Integer.parseInt(line);
            }
            if ((line = in.readLine()) != null) {
                this.EdgesNumber = Integer.parseInt(line);
            }
            while ((line = in.readLine()) != null) {
                String[] values = line.split(" ");
                int initialNodeId = Integer.parseInt(values[0]);
                int finalNodeId = Integer.parseInt(values[1]);
                if (! nodeMap.containsKey(initialNodeId)) {
                    nodeMap.put(initialNodeId, new Node(initialNodeId));
                }
                if (! nodeMap.containsKey(finalNodeId)) {
                    nodeMap.put(finalNodeId, new Node(finalNodeId));
                }
                if (values.length == 2) {
                    EdgeList.add(new Edge(nodeMap.get(initialNodeId), nodeMap.get(finalNodeId)));
                } else {
                    EdgeList.add(new Edge(nodeMap.get(initialNodeId), nodeMap.get(finalNodeId), Integer.parseInt(values[2])));
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Gets nodes number.
     *
     * @return the nodes number
     */
    public int getNodesNumber() {
        return nodesNumber;
    }

    /**
     * Gets edges number.
     *
     * @return the edges number
     */
    public int getEdgesNumber() {
        return EdgesNumber;
    }

    /**
     * Gets edge list.
     *
     * @return the edge list
     */
    public List<Edge> getEdgeList() {
        return EdgeList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.nodesNumber).append("\n").append(this.EdgesNumber).append("\n");
        for (Edge de : EdgeList) {
            sb.append(de.toString()).append("\n");
        }
        return sb.toString();
    }

    /**
     * Get weight adjency matrix int [ ] [ ].
     *
     * @return the int [ ] [ ]
     */
    public int[][] getWeightAdjencyMatrix() {
        int[][] matrix = new int[this.nodesNumber][this.nodesNumber];
        for (int i = 0; i < this.nodesNumber; i++) {
            for (int j = 0; j < this.nodesNumber; j++) {
                matrix[i][j] = 9999;
            }
        }
        for (Edge e : this.EdgeList) {
            matrix[e.getInitialNode().getId()][e.getFinalNode().getId()] = e.getValue();
        }
        for (int n = 0; n < nodesNumber; n++) {
            if (matrix[n][n] == 9999) {
                matrix[n][n] = 0;
            }
        }
        return matrix;
    }

    /**
     * Get next adjency matrix int [ ] [ ].
     *
     * @return the int [ ] [ ]
     */
    public int[][] getNextAdjencyMatrix() {
        int[][] matrix = new int[this.nodesNumber][this.nodesNumber];
        for (int i = 0; i < this.nodesNumber; i++) {
            for (int j = 0; j < this.nodesNumber; j++) {
                matrix[i][j] = -1;
            }
        }
        for (Edge e : this.EdgeList) {
            matrix[e.getInitialNode().getId()][e.getFinalNode().getId()] = e.getInitialNode().getId();
        }
        return matrix;
    }

    /**
     * Gets matrix string.
     *
     * @param matrix the matrix
     * @return the matrix string
     */
    public String getMatrixString(int[][] matrix) {
        StringBuilder sb = new StringBuilder();
        sb.append("\t");
        for (int n = 0; n < this.nodesNumber; n++) {
            sb.append(n).append(" \t");
        }
        sb.append("\n");
        for (int j = 0; j < this.nodesNumber; j++) {
            sb.append(j).append("\t");
            for (int i = 0; i < this.nodesNumber; i++) {
                int value = matrix[j][i];
                if (value > 9000) {
                    sb.append("Inf").append("\t");
                } else {
                    sb.append(value).append("\t");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Floyd warshall list.
     *
     * @return the list
     */
    public List<int[][]> floydWarshall() {
        int[][] weightAdjencyMatrix = this.getWeightAdjencyMatrix();
        int[][] nextAdjencyMatrix = this.getNextAdjencyMatrix();
        StringBuilder temporaryResults = new StringBuilder();
        for (int k = 0; k < this.nodesNumber; k++) {
            for (int i = 0; i < this.nodesNumber; i++) {
                for (int j = 0; j < this.nodesNumber; j++) {
                    if (weightAdjencyMatrix[i][k] + weightAdjencyMatrix[k][j] < weightAdjencyMatrix[i][j]) {
                        weightAdjencyMatrix[i][j] = weightAdjencyMatrix[i][k] + weightAdjencyMatrix[k][j];
                        nextAdjencyMatrix[i][j] = nextAdjencyMatrix[k][j];
                        temporaryResults.append("---------------------------\n");
                        temporaryResults.append("Pour k = ").append(k).append(", i = ").append(i).append(", j = ").append(j).append("\n");
                        temporaryResults.append("---------------------------\n");
                        temporaryResults.append("Matrice de poids des arcs :\n");
                        temporaryResults.append(getMatrixString(weightAdjencyMatrix));
                        temporaryResults.append("---------------------------\n");
                        temporaryResults.append("Matrice d'adjacence :\n");
                        temporaryResults.append(getMatrixString(weightAdjencyMatrix));
                    }
                }
            }
        }
        System.out.println(temporaryResults.toString());
        List<int[][]> matrixList = new ArrayList<>();
        matrixList.add(weightAdjencyMatrix);
        matrixList.add(nextAdjencyMatrix);
        return matrixList;
    }

    /**
     * Gets all paths.
     *
     * @param nextAdjencyMatrix the next adjency matrix
     * @return the all paths
     */
    public String getAllPaths(int[][] nextAdjencyMatrix) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nextAdjencyMatrix.length; i++) {
            for (int j = 0; j < nextAdjencyMatrix.length; j++) {
                StringBuilder temp = new StringBuilder();
                if (i != j) {
                    int value = nextAdjencyMatrix[i][j];
                    if (value == i) {
                        temp.append(i).append(" -> ").append(j).append("\n");
                    } else if (value != -1) {
                        temp.append(i).append(" -> ").append(value).append(" -> ");
                        while (value != nextAdjencyMatrix[value][j]) {
                            value = nextAdjencyMatrix[value][j];
                            temp.append(value).append(" -> ");
                        }
                        temp.append(j).append("\n");
                    }
                }
                sb.append(temp.toString());
            }
        }
        return sb.toString();
    }

    /**
     * Gets path.
     *
     * @param initialNodeId     the initial node id
     * @param finalNodeId       the final node id
     * @param nextAdjencyMatrix the next adjency matrix
     * @return the path
     */
    public String getPath(int initialNodeId, int finalNodeId, int[][] nextAdjencyMatrix) {
        StringBuilder sb = new StringBuilder();
        if (initialNodeId != finalNodeId) {
            int value = nextAdjencyMatrix[initialNodeId][finalNodeId];
            if (value == initialNodeId) {
                sb.append(initialNodeId).append(" -> ").append(finalNodeId);
            } else if (value != -1) {
                sb.append(initialNodeId).append(" -> ").append(value).append(" -> ");
                while (value != nextAdjencyMatrix[value][finalNodeId]) {
                    value = nextAdjencyMatrix[value][finalNodeId];
                    sb.append(value).append(" -> ");
                }
                sb.append(finalNodeId);
            } else {
                sb.append("Il n'existe pas de chemin reliant ").append(initialNodeId).append(" à ").append(finalNodeId);
            }
        }
        return sb.toString();
    }
}
