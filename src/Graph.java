import java.io.*;
import java.util.*;

public class Graph {
    private int nodesNumber, directedEdgesNumber;
    private List<DirectedEdge> directedEdgeList;

    public Graph(String path) {
        try {
            BufferedReader in = null;
            File file = new File(path);
            in = new BufferedReader(new FileReader(file));
            String line = "";

            int cpt = 0;
            Map<Integer, Node> nodeMap = new HashMap<>();

            while ((line = in.readLine()) != null) {
                if (cpt == 0) {
                    this.nodesNumber = Integer.parseInt(line);
                    cpt++;
                } else if (cpt == 1) {
                    this.directedEdgesNumber = Integer.parseInt(line);
                    cpt++;
                } else {
                    String[] values = line.split(" ");
                    if (nodeMap.containsKey(values[0])) {

                    }
                }
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
