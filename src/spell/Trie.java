package spell;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Trie implements spell.ITrie {

    //  VARIABLES

    private TrieNode root = new TrieNode();


    //  METHODS

    public void add(String word) {

        word = word.toLowerCase();

        List<String> letters = new ArrayList(Arrays.asList(word.split("")));

        this.root.add(letters);

        return;
    }

    public INode find(String word) {

        word = word.toLowerCase();

        List<String> letters = new ArrayList(Arrays.asList(word.split("")));

        return root.find(letters);
    }

    public int getWordCount() {
        return this.root.getNumberOfWords();
    }

    public int getNodeCount() {
        return this.root.getNumberOfChildren();
    }

    /**
     * The toString specification is as follows:
     * For each word, in alphabetical order:
     * <word>\n
     */
    @Override
    public String toString() {

        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trie trie = (Trie) o;
        return Objects.equals(root, trie.root);
    }

    @Override
    public int hashCode() {
        return Objects.hash(root);
    }
}
