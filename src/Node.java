public class Node {
    Node left, right;
    String data;
    int weight;

    public Node(String data) {
        this.data = data;
        left = null;
        right = null;
        weight = data.length();
    }

    public Node() {
        data = null;
        left = null;
        right = null;
        weight = 0;
    }
}
