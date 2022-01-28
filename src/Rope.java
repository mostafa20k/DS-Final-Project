public class Rope {
    private Node root;

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public Rope() {
        root = new Node("");
    }

    public void newRope(String input) {
        for (int i = 0; i <= input.length(); i++) {
            if(i==input.length()){
                construct(String.valueOf(input.charAt(i-1)));
            }
            else if (input.charAt(i) == ' ' || input.length()-1  == i) {
                StringBuilder stringBuilder = new StringBuilder(input);
                construct(stringBuilder.substring(0, i + 1));
                input = stringBuilder.substring(i + 1);
                i = 0;
            }
        }
    }

    public void construct(String data) {
        Node leaf = new Node(data);
        Node newRoot = new Node();
        newRoot.left = root;
        newRoot.right = leaf;
        newRoot.weight = newRoot.left.weight;
        if (newRoot.left.right != null)
            newRoot.weight += newRoot.left.right.weight;
        root = newRoot;
    }

    public void printInorder(Node node) {
        if (node == null)
            return;
        printInorder(node.left);
        if (node.data != null) {
            System.out.print(node.data);
        }
        printInorder(node.right);
    }
}
