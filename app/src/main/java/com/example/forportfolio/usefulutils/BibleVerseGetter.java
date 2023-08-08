package com.example.forportfolio.usefulutils;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class BibleVerseGetter {
    public static final int FIRST_LINE=0;
    public static final int SPACE_BAR=1;
    private int bookId;
    private int chapterNum;
    private int verseNum;
    private String verse;
    private String verseLocation;
    private Document doc;
    public BibleVerseGetter(int bookId, int chapterNum, int verseNum){
        this.setVerse(bookId,chapterNum,verseNum);
    }
    public void setVerse(int bookId, int chapterNum, int verseNum){
        this.chapterNum=chapterNum;
        this.verseNum=verseNum;
        this.bookId=bookId;

        verseLocation=(chapterNum+1)+":"+(verseNum+1);
        try {
            doc= Jsoup.connect("https://ibibles.net/quote.php?kor-"+BibleBookId.OLD_LIST[(bookId)]+"/"+verseLocation).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        verse = doc.getElementsByTag("body").get(FIRST_LINE).text();
        verse = verse.substring(verseLocation.length()+SPACE_BAR);
    }
    public String getVerse(){
        if(verse.equals(" verse not found.")){
            return null;
        } else{
            return verse;
        }

    }
    public String set_n_getVerse(int bookId, int chapterNum, int verseNum){
        this.setVerse(bookId, chapterNum, verseNum);
        return getVerse();
    }

    public String getVerseLocation() {
        return verseLocation;
    }
}
