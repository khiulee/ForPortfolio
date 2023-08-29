package com.example.forportfolio.usefulutils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StringCutter {
    private String originalString;
    private List<String> wordsList;
    private String cutString;
    private List<Integer> cutWordsLocs;
    public StringCutter(String originalString){
        this.originalString = originalString;
        String[] wordsArray = originalString.split("\\s+");
        wordsList = new ArrayList<>();
        for (String word : wordsArray) {
            wordsList.add(word);
        }

    }
    public void setCutString(int wordNumToHide){
        List<Integer> hiddenIndices = new ArrayList<>();
        Random random = new Random();

        while (hiddenIndices.size() < wordNumToHide) {
            int randomIndex = random.nextInt(wordsList.size());
            if (!hiddenIndices.contains(randomIndex)) {
                hiddenIndices.add(randomIndex);
                wordsList.set(randomIndex, "___");
            }
        }

        cutString = (String.join(" ", wordsList));
        cutWordsLocs = hiddenIndices;
    }


    public String getCutString() {
        return cutString;
    }

    public List<Integer> getCutWordsLocs() {
        return cutWordsLocs;
    }

    public String getOriginalString() {
        return originalString;
    }

    public void setOriginalString(String originalString) {
        this.originalString = originalString;
    }
}
