package FloydWarshall;

import java.io.*;
import java.util.*;

public class Graph {
    private int nodesNumber, EdgesNumber;
    private final List<Edge> EdgeList;

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

    public int getNodesNumber() {
        return nodesNumber;
    }

    public int getEdgesNumber() {
        return EdgesNumber;
    }

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

    public double[][] getWeightAdjencyMatrix() {
        double[][] matrix = new double[this.nodesNumber][this.nodesNumber];
        for (int i = 0; i < this.nodesNumber; i++) {
            for (int j = 0; j < this.nodesNumber; j++) {
                matrix[i][j] = Double.POSITIVE_INFINITY;
            }
        }
        for (Edge e : this.EdgeList) {
            matrix[e.getInitialNode().getId()][e.getFinalNode().getId()] = e.getValue();
        }
        for (int n = 0; n < nodesNumber; n++) {
            if (matrix[n][n] == Double.POSITIVE_INFINITY) {
                matrix[n][n] = 0;
            }
        }
        return matrix;
    }

    public double[][] getNextAdjencyMatrix() {
        double[][] matrix = new double[this.nodesNumber][this.nodesNumber];
        for (int i = 0; i < this.nodesNumber; i++) {
            for (int j = 0; j < this.nodesNumber; j++) {
                matrix[i][j] = 0;
            }
        }
        for (Edge e : this.EdgeList) {
            matrix[e.getInitialNode().getId()][e.getFinalNode().getId()] = e.getInitialNode().getId();
        }
        return matrix;
    }

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

    public List<double[][]> floydWarshall() {
        double[][] weightAdjencyMatrix = this.getWeightAdjencyMatrix();
        double[][] nextAdjencyMatrix = this.getNextAdjencyMatrix();
        for (int k = 0; k < this.nodesNumber; k++) {
            for (int i = 0; i < this.nodesNumber; i++) {
                for (int j = 0; j < this.nodesNumber; j++) {
                    if (weightAdjencyMatrix[i][k] + weightAdjencyMatrix[k][j] < weightAdjencyMatrix[i][j]) {
                        weightAdjencyMatrix[i][j] = weightAdjencyMatrix[i][k] + weightAdjencyMatrix[k][j];
                        nextAdjencyMatrix[i][j] = nextAdjencyMatrix[k][j];
                    }
                }
            }
        }
        List<double[][]> matrixList = new ArrayList<>();
        matrixList.add(weightAdjencyMatrix);
        matrixList.add(nextAdjencyMatrix);
        return matrixList;
    }

    public String getAllPaths(double[][] nextAdjencyMatrix) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nextAdjencyMatrix.length; i++) {
            //System.out.println(i);
            for (int j = 0; j < nextAdjencyMatrix.length; j++) {
                //System.out.println(j);
                if (i != j) {
                    int u = i + 1;
                    int v = j + 1;
                    //System.out.println(u + " " + v);
                    sb.append(u);
                    do {
                        if (nextAdjencyMatrix[u - 1][v - 1] == Double.POSITIVE_INFINITY) {
                            u = (int) (nextAdjencyMatrix[u - 1][v - 1]);
                            sb.append(" -> ").append(u);
                        } else {
                            break;
                        }
                    } while (u != v);
                    sb.append("\n");
                }
            }
        }
        return sb.toString();
    }
}
