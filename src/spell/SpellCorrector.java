package spell;

import java.io.IOException;
import java.util.Scanner;

public class SpellCorrector implements ISpellCorrector {

    Trie dictionary;

    @Override
    public void useDictionary(String dictionaryFileName) throws IOException {

        //  Implement new Trie dictionary
        this.dictionary = new Trie();

        //  Read from dictionaryFileName
        Scanner scin = new Scanner(dictionaryFileName);

        //  Add each word to dictionary
        while(scin.hasNext()){
            this.dictionary.add(scin.next());
        }

        scin.close();

        return;
    }

    @Override
    public String suggestSimilarWord(String inputWord) {
        return null;
    }
}
