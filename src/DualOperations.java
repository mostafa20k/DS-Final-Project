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
        ropes.get(first-1).setRoot(newRoot);
        ropes.remove(second-1);
    }

    public void insert(int first,int position,int second){
        split(first,position);
        if(second==first+1){
            second=second+1;
        }
        concat(first,second);
        concat(first,first+1);
    }

    public void split(int stringNum, int position){
        int count=-1;
        StringBuilder first=new StringBuilder();
        StringBuilder second=new StringBuilder();
        Node root = ropes.get(stringNum-1).getRoot();
        if (root == null)
            return;
        Stack<Node> s = new Stack<Node>();
        Node curr = root;
        while (curr != null || s.size() > 0) {
            while (curr !=  null)
            {
                s.push(curr);
                curr = curr.left;
            }
            curr = s.pop();

            if(curr.data != null){
                for (int i = 0; i < curr.data.length(); i++) {
                    count++;
                    if(count<=position){
                        first.append(curr.data.charAt(i));
                    }else {
                        second.append(curr.data.charAt(i));
                    }
                }
            }
            curr = curr.right;
        }
        Rope rope = new Rope();
        rope.newRope(String.valueOf(first));
        Rope rope1 = new Rope();
        rope1.newRope(String.valueOf(second));
        ropes.remove(stringNum-1);
        ropes.add(stringNum-1,rope);
        ropes.add(stringNum,rope1);
    }
    public void delete(int stringNum, int i, int j){
        split(stringNum,i);
        split(stringNum+1,j-i-2);
        ropes.remove(stringNum);
        concat(stringNum,stringNum+1);
    }
}

