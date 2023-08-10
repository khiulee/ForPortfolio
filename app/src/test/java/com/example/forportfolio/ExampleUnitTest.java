package com.example.forportfolio;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import static org.junit.Assert.*;

import com.example.forportfolio.usefulutils.BibleVerseGetter;
import com.example.forportfolio.usefulutils.CharacterCutter;

import java.io.IOException;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void webcrawling_isWorking() {
        BibleVerseGetter verseGetter = new BibleVerseGetter(0,BibleVerseGetter.FIRST_LINE,BibleVerseGetter.FIRST_LINE);
        System.out.println(verseGetter.getVerse());

    }
}