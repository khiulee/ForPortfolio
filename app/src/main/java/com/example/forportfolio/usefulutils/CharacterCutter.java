package com.example.forportfolio.usefulutils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CharacterCutter {
    private String originalString;
    private String stringWithBlanks;
    private String[] wordsWithBlanks;
    private Set<Integer> cutWordsIndex;
    private String[] words;

    public CharacterCutter(String originalString) {
        this.originalString = originalString;
        cutWordsIndex = new HashSet<>();
        stringWithBlanks = "";
        words =originalString.split(" ");
        wordsWithBlanks = words;
    }

    public void setOriginalString(String originalString) {
        this.originalString = originalString;
    }
    public void setBlanks() {
        //몇번째 숫자를 blank화할건지
        int random1 = (int)(Math.random()*(words.length+1)-1);
        int random2 = (int)(Math.random()*(words.length)-1);
        if(random1==random2){
            random2++;
        }
        cutWordsIndex.add(random1);
        cutWordsIndex.add(random2);


        for(int i = 0; i< words.length ; i++){
            if(i==random1||i==random2){
                wordsWithBlanks[i] = "_____";
            }
        }
        stringWithBlanks = String.join(" ", wordsWithBlanks);
    }
    
    public String getOriginalString() {
        return originalString;
    }

    public String getStringWithBlanks() {
        return stringWithBlanks;
    }

    public String[] getWordsWithBlanks() {
        return wordsWithBlanks;
    }

    public Set<Integer> getCutWordsIndex() {
        return cutWordsIndex;
    }

    public String[] getWords() {
        return words;
    }
}
