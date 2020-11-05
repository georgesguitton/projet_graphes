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
     * Get adjency matrix double [ ] [ ].
     *
     * @return the double [ ] [ ]
     */
    public double[][] getAdjencyMatrix() {
        double[][] matrix = new double[this.nodesNumber][this.nodesNumber];
        for (int i = 0; i < this.nodesNumber; i++) {
            for (int j = 0; j < this.nodesNumber; j++) {
                matrix[i][j] = Double.POSITIVE_INFINITY;
            }
        }
        for (Edge de : this.EdgeList) {
            matrix[de.getInitialNode().getId()][de.getFinalNode().getId()] = de.getValue();
        }
        for (int n = 0; n < nodesNumber; n++) {
            if (matrix[n][n] == Double.POSITIVE_INFINITY) {
                matrix[n][n] = 0;
            }
        }
        return matrix;
    }

    /**
     * Gets matrix string.
     *
     * @param matrix the matrix
     * @return the matrix string
     */
    public String getMatrixString(double[][] matrix) {
        StringBuilder sb = new StringBuilder();
        sb.append("\t");
        for (int n = 0; n < this.nodesNumber; n++) {
            sb.append(n).append("\t\t");
        }
        sb.append("\n");
        for (int j = 0; j < this.nodesNumber; j++) {
            sb.append(j).append("\t");
            for (int i = 0; i < this.nodesNumber; i++) {
                double value = matrix[j][i];
                if (value == Double.POSITIVE_INFINITY) {
                    sb.append("Inf").append(" \t");
                } else {
                    sb.append(value).append(" \t");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Floyd warshall double [ ] [ ].
     *
     * @return the double [ ] [ ]
     */
    public double[][] floydWarshall() {
        double[][] matrix = this.getAdjencyMatrix();
        for (int k = 0; k < this.nodesNumber; k++) {
            for (int i = 0; i < this.nodesNumber; i++) {
                for (int j = 0; j < this.nodesNumber; j++) {
                    if (matrix[i][k] + matrix[k][j] < matrix[i][j]) {
                        matrix[i][j] = matrix[i][k] + matrix[k][j];
                    }
                }
            }
        }
        return matrix;
    }
}
