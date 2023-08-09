package com.example.forportfolio.usefulutils;

import com.google.android.gms.tasks.Tasks;
import com.google.firebase.database.*;

import java.util.concurrent.ExecutionException;

public class FirebaseBibleVerseManager {

    private final DatabaseReference databaseReference;

    public FirebaseBibleVerseManager(DatabaseReference databaseReference) {
        this.databaseReference = databaseReference;
    }

    private DatabaseReference getVerseReference(String userId, int bookId, int chapterNum, int verseNum) {
        return databaseReference.child("users").child(userId).child("readVerses")
                .child("book_" + bookId).child("chapter_" + chapterNum).child("verse_" + verseNum);
    }
    /** userId에 해당하는 사람이 특정 성경 구절을 읽었는지 안읽었는지 체크하는 메소드**/
    public boolean hasReadVerse(String userId, int bookId, int chapterNum, int verseNum) {
        DatabaseReference verseRef = getVerseReference(userId, bookId, chapterNum, verseNum);
        try {
            DataSnapshot verseSnapshot = Tasks.await(verseRef.get());
            return verseSnapshot.exists();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            // Handle the exception
            return false; // or throw an exception if needed
        }
    }
    /** userId에 해당하는 사람이 특정 성경 구절을 읽었다면 체크해제, 안읽었다면 다시 체크하는 메소드**/
    public boolean toggleVerseRead(String userId, int bookId, int chapterNum, int verseNum) {
        DatabaseReference verseRef = getVerseReference(userId, bookId, chapterNum, verseNum);
        boolean isRead = hasReadVerse(userId, bookId, chapterNum, verseNum);
        if (isRead) {
            verseRef.removeValue();
        } else {
            verseRef.setValue(true);
        }
        return !isRead;
    }
    /** userId에 해당하는 사람이 특정 성경 구절을 안읽었다면 체크한 뒤 true를 리턴, 그렇지 않으면 false를 리턴하는 메소드**/
    public boolean markVerseAsRead(String userId, int bookId, int chapterNum, int verseNum) {
        DatabaseReference verseRef = getVerseReference(userId, bookId, chapterNum, verseNum);
        boolean isRead = hasReadVerse(userId, bookId, chapterNum, verseNum);
        if (!isRead) {
            verseRef.setValue(true);
            return true;
        }
        return false;
    }
    /** userId에 해당하는 사람이 특정 성경 구절을 읽었다면 체크해제 뒤 true를 리턴, 그렇지 않으면 false를 리턴하는 메소드**/
    public boolean markVerseAsUnread(String userId, int bookId, int chapterNum, int verseNum) {
        DatabaseReference verseRef = getVerseReference(userId, bookId, chapterNum, verseNum);
        boolean isRead = hasReadVerse(userId, bookId, chapterNum, verseNum);
        if (isRead) {
            verseRef.removeValue();
            return true;
        }
        return false;
    }
    public void uncheckAllVersesInBook(String userId, int bookId) {
        DatabaseReference bookRef = databaseReference.child("users").child(userId).child("readVerses")
                .child("book_" + bookId);
        bookRef.removeValue();
    }

    /** Unchecks all verses in the given chapter for the user **/
    public void uncheckAllVersesInChapter(String userId, int bookId, int chapterNum) {
        DatabaseReference chapterRef = databaseReference.child("users").child(userId).child("readVerses")
                .child("book_" + bookId).child("chapter_" + chapterNum);
        chapterRef.removeValue();
    }

}