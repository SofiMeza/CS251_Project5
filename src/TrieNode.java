import java.util.LinkedList;
import java.util.List;

public class TrieNode {
    TrieNode[] children = new TrieNode[26];
    StringBuilder[] edgeLabel = new StringBuilder[26];
    boolean isEnd;
    private int depth;
    private String word;

public TrieNode(boolean isEnd, int depth) {
    this.isEnd = isEnd;
    this.depth = depth;
}

    public TrieNode(boolean isEnd) {
        this.isEnd = isEnd;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getDepth() {
        return depth;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
