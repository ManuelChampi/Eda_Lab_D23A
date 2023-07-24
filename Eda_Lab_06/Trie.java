import java.util.HashMap;
import java.util.Map;

public class Trie {
    private TrieNode root;
    private StringBuilder text;

    public Trie() {
        root = new TrieNode();
        text = new StringBuilder();
    }

    public void insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            node.children.putIfAbsent(c, new TrieNode());
            node = node.children.get(c);
        }
        node.isEndOfWord = true;
        text.append(word).append(" ");
    }

    public int search(String word) {
        int count = 0;
        int index = text.indexOf(word + " ");
        while (index != -1) {
            count++;
            index = text.indexOf(word + " ", index + 1);
        }
        return count;
    }

    public int replace(String word, String replacement) {
        int count = 0;
        int index = text.indexOf(word + " ");
        while (index != -1) {
            text.replace(index, index + word.length(), replacement);
            count++;
            index = text.indexOf(word + " ", index + replacement.length());
        }
        return count;
    }

    public String getText() {
        return text.toString();
    }

    private static class TrieNode {
        private Map<Character, TrieNode> children;
        private boolean isEndOfWord;

        public TrieNode() {
            children = new HashMap<>();
            isEndOfWord = false;
        }
    }
}
