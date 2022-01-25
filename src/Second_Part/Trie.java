package Second_Part;
import java.util.List;

public class Trie {
    TrieNode root;
    public Trie(List<String> words) {
        root = new TrieNode();
        for (int i = 0; i < words.size() ; i++) {
            root.insert(words.get(i));
        }
    }

    public boolean find(String prefix, boolean exact) {
        TrieNode lastNode = root;
        for (char c : prefix.toCharArray()) {
            lastNode = lastNode.children.get(c);
            if (lastNode == null)
                return false;
        }
        return !exact || lastNode.isWord;
    }

    public boolean find(String prefix) {
        return find(prefix, false);
    }

    public void handler(TrieNode root, String[] list, StringBuffer curr) {
        if(root.isWord) {
            for (int i = 0; i < 3; i++) {
                if (list[i] == null) {
                    list[i] = curr.toString();
                    break;
                }
            }
        }

        if (root.children == null || root.children.isEmpty())
            return;

        for (TrieNode child : root.children.values()) {
            handler(child, list, curr.append(child.c));
            curr.setLength(curr.length() - 1);
        }
    }

    public String autocomplete(char prefix,int number) {
        String[] list =new String[3];
        TrieNode lastNode = root;
        StringBuffer curr = new StringBuffer();
        lastNode = lastNode.children.get(prefix);
        if (lastNode == null)
            return toString(list);
        curr.append(prefix);
        handler(lastNode, list, curr);
        if(number>0 && list[number-1]!=null){
            return list[number-1];
        }
        return toString(list);
    }
    public String toString(String[] list){
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < list.length; i++) {
            sb.append(i+1+". "+ list[i]+ "\n");
        }
        String str = sb.toString();
        return str;
    }
}


