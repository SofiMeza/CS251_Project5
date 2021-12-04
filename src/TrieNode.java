import java.util.LinkedList;
import java.util.List;

public class TrieNode {
    TrieNode[] children = new TrieNode[26];
    StringBuilder[] edgeLabel = new StringBuilder[26];
    boolean isEnd;

public TrieNode(boolean isEnd) {
    this.isEnd = isEnd;
}
}
