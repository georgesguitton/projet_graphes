package FloydWarshall;

/**
 * The type Node.
 */
public class Node {
    private int id;

    /**
     * Instantiates a new Node.
     *
     * @param id the id
     */
    public Node(int id) {
        this.id = id;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "" + id;
    }
}
