public class DirectedEdge {
    private Node initialNode, finalNode;
    private int value;

    public DirectedEdge(Node initialNode, Node finalNode, int value) {
        this.initialNode = initialNode;
        this.finalNode = finalNode;
        this.value = value;
    }

    public DirectedEdge(Node initialNode, Node finalNode) {
        this.initialNode = initialNode;
        this.finalNode = finalNode;
    }

    public Node getInitialNode() {
        return initialNode;
    }

    public void setInitialNode(Node initialNode) {
        this.initialNode = initialNode;
    }

    public Node getFinalNode() {
        return finalNode;
    }

    public void setFinalNode(Node finalNode) {
        this.finalNode = finalNode;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
