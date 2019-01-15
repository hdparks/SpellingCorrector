import spell.ITrie;

import java.util.Arrays;
import java.util.Map;

public class TrieNode implements spell.ITrie.INode {

    public static String[] alphabet = "abcdefghijklmnopqrstuvwxyz".split("");

    private Map<String, ITrie.INode> nodes;

    private int value;

    public TrieNode(){

    }

    public int getValue() {
        return value;
    }

    public Map<String, ITrie.INode> getNodes() {
        return nodes;
    }

    public void addWord(String[] letters){
        this.value += 1;
        if (letters.length > 1){
            this.nodes[letters[0]].addWord(Arrays.copyOfRange(letters,1,letters.length));
        }
        return;
    }

}
