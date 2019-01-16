package spell;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Trie implements spell.ITrie {

    //  VARIABLES

    private TrieNode root = new TrieNode();


    //  METHODS

    public void add(String word) {

        List<String> letters = new ArrayList(Arrays.asList(word.split("")));

        this.root.add(letters);

        return;
    }

    public INode find(String word) {
        List<String> letters = new ArrayList(Arrays.asList(word.split("")));
        return root.find(letters);
    }

    public int getWordCount() {
        return 0;
    }

    public int getNodeCount() {
        return 0;
    }


}
