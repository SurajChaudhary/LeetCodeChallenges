package datastructures.trie;

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode curr = root;

        for (char c : word.toCharArray()) {
            if (curr.next.get(c) == null) {
                curr.next.put(c, new TrieNode());
            }
            curr = curr.next.get(c);
        }
        curr.isEnd = true;
    }

    public boolean search(String word) {
        TrieNode curr = root;
        for (char c : word.toCharArray()) {
            if (!curr.next.containsKey(c)) return false;
            curr = curr.next.get(c);
        }
        return curr.isEnd;
    }
}
