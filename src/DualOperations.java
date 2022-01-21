import java.util.LinkedList;
import java.util.Stack;

public class DualOperations {
    private LinkedList<Rope> ropes = new LinkedList<>();

    public LinkedList<Rope> getRopes() {
        return ropes;
    }

    public void setRopes(LinkedList<Rope> ropes) {
        this.ropes = ropes;
    }

    public void status() {
        for (int i = 0; i < ropes.size(); i++) {
            System.out.print((i + 1) + ".");
            ropes.get(i).printInorder(ropes.get(i).getRoot());
            System.out.println("\n");
        }
    }

    public char index(int ropeNum, int character) {
        Node node = ropes.get(ropeNum-1).getRoot();
        return inorder(node,character);
    }

    public char inorder(Node root, int character) {
        int count=-1;
        if (root == null)
            return ' ';
        Stack<Node> s = new Stack<Node>();
        Node curr = root;
        while (curr != null || s.size() > 0) {
            while (curr != null) {
                s.push(curr);
                curr = curr.left;
            }
            curr = s.pop();
            if(curr.data != null){
                for (int i = 0; i < curr.data.length(); i++) {
                    count++;
                    if(count==character){
                        return curr.data.charAt(i);
                    }
                }
            }
            curr = curr.right;
        }
        return ' ';
    }

    public void concat(int first,int second){
        Node newRoot = new Node();
        newRoot.left=ropes.get(first-1).getRoot();
        newRoot.right=ropes.get(second-1).getRoot();
        newRoot.weight=newRoot.left.weight;
        if (newRoot.left.right != null)
            newRoot.weight += newRoot.left.right.weight;
        System.out.println(newRoot.weight);
        ropes.get(first-1).setRoot(newRoot);
        ropes.remove(second-1);
    }
}

