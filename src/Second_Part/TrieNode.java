package Second_Part;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TrieNode {
    Map<Character, TrieNode> children;
    char c;
    boolean isWord;

    public TrieNode(char c) {
        this.c = c;
        children = new HashMap<>();
    }

    public TrieNode() {
        children = new HashMap<>();
    }

    public void insert(String word) {
        if (word == null || word.isEmpty())
            return;
        char firstChar = word.charAt(0);
        TrieNode child = children.get(firstChar);
        if (child == null) {
            child = new TrieNode(firstChar);
            children.put(firstChar, child);
        }
        if (word.length() > 1)
            child.insert(word.substring(1));
        else
            child.isWord = true;
    }

    public void fileHandler(String path) {
        File file = new File(path);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }else {
                    insert(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
