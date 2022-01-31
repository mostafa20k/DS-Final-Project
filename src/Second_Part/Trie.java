package Second_Part;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Trie {
    private String[] list = new String[3];
    private TrieNode root;
    private PriorityQueue suggestion = new PriorityQueue();

    public Trie(String path) {
        root = new TrieNode();
        List<String> words = fileHandler(path);
        for (String word : words) {
            root.insert(word);
        }
    }

    public String getList(int number) throws InvalidException {
        if (number > 0 && list[number - 1] != null) {
            int priority = find(list[number - 1]);

            suggestion.deleteNode(list[number - 1]);
            suggestion.push(list[number - 1], priority);

            return list[number - 1];
        }
        throw new InvalidException();
    }

    public int find(String prefix) {
        TrieNode lastNode = root;
        for (char c : prefix.toCharArray()) {
            lastNode = lastNode.children.get(c);
            if (lastNode == null)
                return -1;
        }
        if (lastNode.isWord) {
            return lastNode.priority++;
        }
        return -1;
    }

    public void printSuggestion() {
        suggestion.printPQ();
    }

    public List fileHandler(String path) {
        List<String> words = new ArrayList<>();
        File file = new File(path);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                } else {
                    words.add(line);
                    suggestion.push(line, 0);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return words;
    }

    public void handler(TrieNode root, String[] list, StringBuffer curr) {
        if (root.isWord) {
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

    public String autocomplete(char prefix) {
        TrieNode lastNode = root;
        StringBuffer curr = new StringBuffer();
        lastNode = lastNode.children.get(prefix);
        if (lastNode == null)
            return toString(list);
        curr.append(prefix);
        handler(lastNode, list, curr);
        return toString(list);
    }

    public String toString(String[] list) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < list.length; i++) {
            sb.append(i + 1 + ". " + list[i] + "\n");
        }
        return sb.toString();
    }
}


