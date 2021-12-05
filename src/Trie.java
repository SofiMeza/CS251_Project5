import java.io.*;
import java.util.LinkedList;

public class Trie {
    private final TrieNode root = new TrieNode(false, 0);
    private final int CASE = 97; // assume always lowercase

    /**
     *
     * This function is used to build tree.
     * Inputfile should be read in this function in order to use testcases.
     *
     * @param inputfile
     * @throws Exception
     */
    public void buildTrie (String inputfile) throws Exception {
        // read file
        File inputFile = new File(inputfile);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile));
        String word;
        boolean isFirst = true;
        String searching = null;
        while ((word = bufferedReader.readLine()) != null) {
            if (isFirst) {
                searching = word;
                isFirst = false;
            } else {
                insert(word);
            }
        }

        setDepth();
        search(searching);
    }

    public void insert (String word) {
        TrieNode current = root;
        int wordIndex = 0;

        // while:
        // CASE: add prefix of existing word
        // CASE: add word with partial match of existing word
        while (wordIndex < word.length() && current.edgeLabel[word.charAt(wordIndex) - CASE] != null) {
            int labelIndex = 0;
            int letterIndex = word.charAt(wordIndex) - CASE; // index of word in alphabet
            StringBuilder label = current.edgeLabel[letterIndex];

            // while label and word don't reach end AND match
            while (wordIndex < word.length() && labelIndex < label.length() && label.charAt(labelIndex) == word.charAt(wordIndex)) {
                wordIndex++;
                labelIndex++;
            }

            if (labelIndex == label.length()) { // if entire label matches
                current = current.children[letterIndex];
            } else { // if partial match between word and label
                StringBuilder cutLabel = createSubstring(label, labelIndex);
                label.setLength(labelIndex);

                if (wordIndex == word.length()) { // CASE: Add prefix of existing word
                    addPrefix(current, letterIndex, cutLabel);
                } else { // CASE: add word with partial match of existing word
                    partialMatch(current, word, wordIndex, letterIndex, cutLabel);
                }
                return;
            } //end if
        } //end outer while

        // CASE: add new node for new word
        addNewNode(current, word, wordIndex);
    }

    private void addPrefix(TrieNode current, int letterIndex, StringBuilder cutLabel) {
        TrieNode exists = current.children[letterIndex];
        TrieNode toInsert = new TrieNode(true);
        current.children[letterIndex] = toInsert;
        toInsert.children[cutLabel.charAt(0) - CASE] = exists;
        toInsert.edgeLabel[cutLabel.charAt(0) - CASE] = cutLabel;
    }

    private void partialMatch(TrieNode current, String word, int wordIndex, int letterIndex, StringBuilder cutLabel) {
        TrieNode toInsert = new TrieNode(false);
        StringBuilder cutWord = createSubstring(word, wordIndex);
        TrieNode x = current.children[letterIndex];
        current.children[letterIndex] = toInsert;
        toInsert.edgeLabel[cutLabel.charAt(0) - CASE] = cutLabel;
        toInsert.children[cutLabel.charAt(0) - CASE] = x;
        toInsert.edgeLabel[cutWord.charAt(0) - CASE] = cutWord;
        toInsert.children[cutWord.charAt(0) - CASE] = new TrieNode(true);
    }

    private void addNewNode(TrieNode current, String word, int wordIndex) {
        if (wordIndex < word.length()) {
            current.edgeLabel[word.charAt(wordIndex) - CASE] = createSubstring(word, wordIndex);
            current.children[word.charAt(wordIndex) - CASE] = new TrieNode(true);
        } else {
            current.isEnd = true;
        }
    }

    private StringBuilder createSubstring(CharSequence word, int i) {
        StringBuilder outputString = new StringBuilder();
        while (i != word.length()) {
            outputString.append(word.charAt(i));
            i++;
        }
        return outputString;
    }

    public void setDepth() {
        LinkedList<TrieNode> queue = new LinkedList<>();
        queue.add(root);
        root.setWord("");
        while(!queue.isEmpty()) {
            TrieNode current = queue.poll();
            for (int i = 0; i < current.children.length; i++) {
                if (current.children[i] != null) {
                    String toWrite = current.getWord() + (current.edgeLabel[i]);
                    current.children[i].setWord(toWrite);
                    current.children[i].setDepth(current.getDepth() + 1);
                    queue.add(current.children[i]);
                }
            }
        }
    }

    public void search (String word) {
        TrieNode current = root;
        int wordIndex = 0;

        while (wordIndex < word.length() && current.edgeLabel[word.charAt(wordIndex) - CASE] != null) {
            int letterIndex = word.charAt(wordIndex) - CASE; // index of word in alphabet
            StringBuilder label = current.edgeLabel[letterIndex];
            int labelIndex = 0;

            while (labelIndex < label.length() && wordIndex < word.length()) {
                if(label.charAt(labelIndex) != word.charAt(wordIndex)) {
                    // if mismatch happens then there is no match
                    // print nothing to output file
                    return;
                }
                labelIndex++;
                wordIndex++;
            }

            // if reach here, then there is something to print to output file
            if (labelIndex == label.length() && wordIndex <= word.length()) {
                BFS(current);
            }

        }
    }

    private void BFS(TrieNode root) {

    }

    /**
     *
     * This function is used to traverse the trie and compute the autocomplete wordlist.
     * Output file should be produced here.
     *
     * @param outputfile
     * @throws Exception
     */
    public void autocomplete (String outputfile) throws Exception{


    }

    public static void main(String[] args) throws Exception {
        Trie trie = new Trie();
        trie.buildTrie("files/input/input1.txt");
    }
}
