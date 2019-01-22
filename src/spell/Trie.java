package spell;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Trie implements spell.ITrie {

    //  VARIABLES

    // Begins with root node, always
    private int nodeCount = 1;

    private int wordCount = 0;

    private TrieNode root = new TrieNode();


    //  METHODS

    public void add(String word) {

        word = word.toLowerCase();

        ArrayList letters = new ArrayList(Arrays.asList(word.split("")));

        this.root.add(letters, this);

    }

    public INode find(String word) {

        word = word.toLowerCase();

        ArrayList letters = new ArrayList(Arrays.asList(word.split("")));

        return root.find(letters);
    }

    public int getWordCount() {
        return this.wordCount;
    }

    public int getNodeCount() {
        return this.nodeCount;
    }


    @Override
    public String toString() {
        return this.root.toString();
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
        return this.getWordCount() * this.getNodeCount();
    }



    public void incWordCount(){
        this.wordCount += 1;
    }

    public void incNodeCount(){
        this.nodeCount += 1;
    }


}
