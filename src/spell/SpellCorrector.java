package spell;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class SpellCorrector implements ISpellCorrector {

    private Trie dictionary;

    @Override
    public void useDictionary(String dictionaryFileName) throws IOException {

        //  Implement new Trie dictionary
        this.dictionary = new Trie();

        //  Read from dictionaryFileName
        Scanner scin = new Scanner(new File(dictionaryFileName));

        scin.useDelimiter("[^A-Za-z]+");

        //  Add each word to dictionary
        while(scin.hasNext()){
            this.dictionary.add(scin.next());
        }

        scin.close();

        return;
    }

    @Override
    public String suggestSimilarWord(String inputWord) {
        ITrie.INode x = this.dictionary.find(inputWord);
        if (x == null){
            return null;
        } else {
            return inputWord;
        }
    }

    public int getWordCount(){
        return this.dictionary.getWordCount();
    }

    public int getNodeCount(){
        return this.dictionary.getNodeCount();
    }

    public String toString(){ return this.dictionary.toString(); }

    public static void main(String[] args) throws IOException {
        SpellCorrector corrector = new SpellCorrector();
        corrector.useDictionary(args[0]);
        System.out.println(corrector.toString());
    }
}
