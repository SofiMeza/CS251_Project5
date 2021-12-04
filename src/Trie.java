import java.io.*;
import java.util.LinkedList;

public class Trie {
    private final TrieNode root = new TrieNode(false);
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
        LinkedList<String> fileWords = new LinkedList<>();
        String word;
        while ((word = bufferedReader.readLine()) != null) {
            fileWords.add(word);
            insert(word);
        }
    }

    public void insert (String word) {
        TrieNode current = root;
        int wordIndex = 0;
        int letterIndex = word.charAt(wordIndex) - CASE;

        // while:
        // CASE: add prefix of existing word
        // CASE: add word with partial match of existing word
        while (wordIndex < word.length() && current.edgeLabel[letterIndex] != null) {
            letterIndex = word.charAt(wordIndex) - CASE; // index of word in alphabet
            int labelIndex = 0;
            StringBuilder label = current.edgeLabel[letterIndex];

            // while label and word don't reach end AND match
            while (labelIndex < label.length() && wordIndex < word.length() && label.charAt(labelIndex) == word.charAt(wordIndex)) {
                wordIndex++;
                labelIndex++;
            }

            if (labelIndex == label.length()) { // if entire label matches
                current = current.children[letterIndex];
            } else { // if partial match between word and label
                if (wordIndex == word.length()) { // CASE: Add prefix of existing work

                } else { // CASE: add word with partial match of existing word

                }
                return;
            } //end if
        } //end outer while

        // CASE: add new node for new word
        if (wordIndex < word.length()) {
            current.edgeLabel[letterIndex] = createSubstring(word, wordIndex);
            current.children[letterIndex] = new TrieNode(true);
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
