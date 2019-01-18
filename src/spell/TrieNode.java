package spell;


import java.util.List;

public class TrieNode implements spell.ITrie.INode {

    // FIELDS

    private TrieNode[] nodeArray = new TrieNode[26];

    private int value;



    // METHODS

    public int getValue() {
        return value;
    }

    public void add(List<String> letters){

        //  If we only get no letter, the word ends here.
        if ( letters.size() == 0 ){
            this.value += 1;
            return;
        }

        // Get chopped list
        List<String> chopLetters = letters.subList( 1, letters.size() );

        // Find the next node
        TrieNode nextNode = this.getNodeL(letters.get(0).charAt(0));

        // Pass the chopped list to the next node
        nextNode.add(chopLetters);

        return;
    }

    public TrieNode getNodeL(char l){

        int index = l - 'a';

        if( this.nodeArray[index] == null ) this.nodeArray[index] = new TrieNode();

        return this.nodeArray[index];
    }

    public int getNumberOfChildren(){

        int count = 1;

        for (TrieNode nodei : this.nodeArray){
            if(nodei != null){
                count += nodei.getNumberOfChildren();
            }
        }

        return count;
    }

    public int getNumberOfWords(){

        int count = this.getValue();

        for (TrieNode nodei : this.nodeArray){
            if(nodei != null){
                count += nodei.getNumberOfWords();
            }
        }
        return count;
    }

    public TrieNode find(List<String> letters){

        //  If we run out of letters, we have it or we don't
        if ( letters.size() == 0 ){


            if (this.getValue() > 0){

                return this;

            } else {

                return null;

            }
        }

        //  If we still have more, find next and keep going.

        List<String> chopLetters = letters.subList(1,letters.size());

        TrieNode nextNode = this.getNodeL(letters.get(0).charAt(0));

        return nextNode.find(chopLetters);
    }


    public void buildToString(StringBuilder outString, StringBuilder current){
        if ( this.value > 0 ){
            outString.append(current + "\n");
        }

        for (int i = 0; i < 26; i++){
            if (this.nodeArray[i] != null){
                //  Here, we append the next letter to current
                current.append( (char) (i + 'a') );

                this.nodeArray[i].buildToString(outString,current);

                // Before moving on, we take off the letter we just put on
                current.delete(current.length() - 1, current.length());
            }
        }
        return;
    }

    public String toString(){
        StringBuilder outString = new StringBuilder();
        StringBuilder current = new StringBuilder();
        this.buildToString(outString, current);
        return outString.toString();
    }
}
