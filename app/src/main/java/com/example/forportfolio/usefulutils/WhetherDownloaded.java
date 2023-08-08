package com.example.forportfolio.usefulutils;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.forportfolio.usefulutils.BibleBookId;

public class WhetherDownloaded {
    private final Context context;
    private File fileLoaded;
    private final int DOWNLOADED_TRUE = 1;
    private final int DOWNLOADED_FALSE = 0;
    /** 현재 액티비티나 프래그먼트의 컨텍스트를 그냥 넣어주시면 됩니다.**/
    public WhetherDownloaded(Context context) {

        this.context = context;
        try {
            File csvFile = new File(context.getFilesDir(), "ifDownLoaded.csv");
            if (!csvFile.exists()){
                FileWriter writer = new FileWriter(csvFile);
                // Write data to the CSV file
                String temp = "";
                writer.append("bookId, bookTitle, ifDownloaded\n");
                for( int i = 0; i< BibleBookId.OLD_KOREAN_LIST.length; i++){
                    temp = i+", "+BibleBookId.OLD_KOREAN_LIST[i]+", "+DOWNLOADED_FALSE;
                    writer.append(temp);
                }
                writer.flush();
                writer.close();
            }
            this.fileLoaded = csvFile;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public boolean bookDownload(int bookId) {
        // Read the CSV file and update ifDownloaded if necessary
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileLoaded));
            List<String> lines = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
            br.close();

            // Extract the bookId-th row
            String[] rowData = lines.get(bookId).split(", ");
            int ifDownloaded = Integer.parseInt(rowData[2]);

            if (ifDownloaded == DOWNLOADED_FALSE) {
                // Call the bookDownloader's downloadBook method
                BookDownloader bookDownloader = new BookDownloader(this.context);
                bookDownloader.downloadBook(bookId);
                bookDownloader.close();
                // Update ifDownloaded to DOWNLOADED_TRUE
                rowData[2] = String.valueOf(DOWNLOADED_TRUE);

                // Update the lines list with the modified row
                lines.set(bookId, String.join(", ", rowData));

                // Write the updated lines back to the CSV file
                FileWriter writer = new FileWriter(fileLoaded);
                for (String updatedLine : lines) {
                    writer.write(updatedLine + "\n");
                }
                writer.close();

                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

}
