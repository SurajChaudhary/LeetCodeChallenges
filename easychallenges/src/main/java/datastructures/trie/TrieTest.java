package datastructures.trie;

public class TrieTest {
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("car");
        trie.insert("card");
        trie.insert("dog");

        System.out.println("card exists: "+trie.search("card"));
        System.out.println("cardamom exists: "+trie.search("cardamom"));
        System.out.println("bull exists: "+trie.search("bull"));
        System.out.println("dog exists: "+trie.search("dog"));
    }
}
