package com.example.forportfolio.usefulutils;

import android.content.Context;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class BookDownloader {
    private Context context;

    public BookDownloader(Context context) {
        this.context = context;
    }

    public Boolean downloadBook(int bookId) {
        String fileName = BibleBookId.OLD_LIST[bookId] + ".csv";
        File file = new File(context.getFilesDir(), fileName);

        if (file.exists()) {
            return false; // File already exists
        }

        try {
            FileWriter writer = new FileWriter(file);
            writer.append("chapterNum, verseNum, text\n");

            BibleVerseGetter bibleVerseGetter = new BibleVerseGetter(bookId, 0, 0);
            int cur_1 = 0, cur_2 = 0;

            while (true) {
                String verse = bibleVerseGetter.set_n_getVerse(bookId, cur_1, cur_2);

                if (verse != null) {
                    writer.append(cur_1 + ", " + cur_2 + ", " + verse + "\n");
                    cur_2++;
                } else if (cur_2 == 0) {
                    break; // No more verses in this chapter
                } else {
                    cur_1++; // Move to the next chapter
                    cur_2 = 0; // Start from the first verse of the new chapter
                }
            }

            writer.flush();
            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void close() {
        // Implement any necessary cleanup here
    }
    public Boolean deleteFile(int bookId) {
        String fileName = BibleBookId.OLD_LIST[bookId] + ".csv";
        File file = new File(context.getFilesDir(), fileName);

        if (file.exists()) {
            if (file.delete()) {
                return true;
            } else {
                return false; //an error occured
            }
        } else {
            return false; //file doesn't exist
        }
    }

}
