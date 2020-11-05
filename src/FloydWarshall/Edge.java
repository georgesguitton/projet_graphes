package FloydWarshall;

/**
 * The type Edge.
 */
public class Edge {
    private Node initialNode, finalNode;
    private int value;

    /**
     * Instantiates a new Edge.
     *
     * @param initialNode the initial node
     * @param finalNode   the final node
     * @param value       the value
     */
    public Edge(Node initialNode, Node finalNode, int value) {
        this.initialNode = initialNode;
        this.finalNode = finalNode;
        this.value = value;
    }

    /**
     * Instantiates a new Edge.
     *
     * @param initialNode the initial node
     * @param finalNode   the final node
     */
    public Edge(Node initialNode, Node finalNode) {
        this.initialNode = initialNode;
        this.finalNode = finalNode;
        this.value = 0;
    }

    /**
     * Gets initial node.
     *
     * @return the initial node
     */
    public Node getInitialNode() {
        return initialNode;
    }

    /**
     * Sets initial node.
     *
     * @param initialNode the initial node
     */
    public void setInitialNode(Node initialNode) {
        this.initialNode = initialNode;
    }

    /**
     * Gets final node.
     *
     * @return the final node
     */
    public Node getFinalNode() {
        return finalNode;
    }

    /**
     * Sets final node.
     *
     * @param finalNode the final node
     */
    public void setFinalNode(Node finalNode) {
        this.finalNode = finalNode;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    public int getValue() {
        return value;
    }

    /**
     * Sets value.
     *
     * @param value the value
     */
    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return initialNode.toString() + " " + finalNode.toString() + " " + value;
    }
}
