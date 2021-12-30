package datastructures.trie;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {
    Map<Character, TrieNode> next;
    boolean isEnd;

    TrieNode() {
        next = new HashMap<>();
        isEnd = false;
    }
}
