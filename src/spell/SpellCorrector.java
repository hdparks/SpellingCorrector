package spell;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class SpellCorrector implements ISpellCorrector {

    private Trie dictionary = new Trie();

    @Override
    public void useDictionary(String dictionaryFileName) throws IOException {

        //  Implement new Trie dictionary

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
        inputWord = inputWord.toLowerCase();
        //  If the inputWord is found, return it
        if(dictionary.find(inputWord) != null) return inputWord;


        //  If the input word is not found, construct some new ones at 1 edit distance
        String[] oneEditDistance = SpellCorrector.getClosestWords(inputWord);

        String winner = "";
        int winCount = 0;

        for (String current : oneEditDistance){
            // Don't try empty strings
            if(current.isEmpty()) continue;

            ITrie.INode cNode = dictionary.find(current);
            if (cNode == null) continue;
            if (cNode.getValue() > winCount){
                winner = current;
                winCount = cNode.getValue();
            }
        }

        if(winner != ""){
            return winner;
        }

        //   If nothing was found at one edit distance, try two.
        for (String oneDist : oneEditDistance){
          if(oneDist.isEmpty()) continue;
            for (String current : SpellCorrector.getClosestWords(oneDist)){

                if (current.isEmpty()) continue;

                ITrie.INode cNode = dictionary.find(current);
                if (cNode == null) continue;
                if (cNode.getValue() > winCount ||(cNode.getValue() == winCount && current.compareTo(winner) < 0)){
                    winner = current;
                    winCount = cNode.getValue();
                }
            }
        }

        if (winner != "") return winner;
        else return null;

    }

    public static String[] getClosestWords(String word){

        int n = word.length();
        String[] words = new String[53*n + 25]; // = n + n - 1 + 25*n + 26*(n+1)

        //  Deletion: remove one letter. size = n
        for (int i = 0; i < n; i++){
            words[i] = word.substring(0,i)+word.substring(i+1);
        }

        //  Transposition: switch two adjacent letters. size = n-1
        for (int i = 0; i < n - 1; i++){
            words[i+n] = word.substring(0,i)+word.substring(i+1,i+2)+word.substring(i,i+1)+word.substring(i+2);
        }

        //  Alteration: switch a letter with another letter of the alphabet. size 25*n
        for (int i = 0; i < n; i++){
            char letter = word.charAt(i);
            int offset = 0;

            for(int j = 0; j < 26; j++){
                //  We skip the one we already have.
                char jletter = (char) ('a' + j);
                if (jletter == letter){
                    offset = -1;
                    j++;
                }

                words[2*n - 1 + i*25 + j + offset] = word.substring(0,i) + (char)('a' + j) + word.substring(i+1);
            }
        }
        //  Insertion: add a letter between all letters, at beginning and end. size 26*(n+1)
        for (int i = 0; i < n+1; i++){
            for (int j = 0; j < 26; j++){
                words[27*n - 1 + i*26 + j] = word.substring(0,i) + (char)('a' + j) + word.substring(i);
            }
        }
        return words;
    }

    public int getWordCount(){
        return this.dictionary.getWordCount();
    }

    public int getNodeCount(){
        return this.dictionary.getNodeCount();
    }

    public String toString(){ return this.dictionary.toString(); }

    @Override
    public boolean equals(Object other){
        if(this == other){
            return true;
        }
        if (other == null || this.getClass() != other.getClass()) {
            return false;
        }
        SpellCorrector o = (SpellCorrector) other;
        return this.dictionary.equals(o.dictionary);

    }

    public static void main(String[] args) throws IOException {

      SpellCorrector c = new SpellCorrector();
      c.useDictionary("words.txt");
      SpellCorrector b = new SpellCorrector();
      b.dictionary.add("yea");

      System.out.println("suggestion: " + c.suggestSimilarWord("zzz"));
      System.out.println("b suggestion : " + b.suggestSimilarWord("a"));
    }
}
