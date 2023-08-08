package com.example.forportfolio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.example.forportfolio.usefulutils.BibleVerseGetter;

public class MainActivity extends AppCompatActivity {
    public static final int FRAGMENT_MAIN=0;
    public static final int FRAGMENT_PURCHASE=1;
    public static final int FRAGMENT_SETTING=2;
    public static final int FRAGMENT_BOOKLIST=3;
    public static final int FRAGMENT_CHAPTERLIST=4;
    public static final int FRAGMENT_VERSELIST=5;
    public static final int FRAGMENT_INFOVERSE=6;
    public static final int FRAGMENT_FINISHED=7;
    Fragment main;
    FragmentManager.BackStackEntry backStackEntry;

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
//    protected void getChapterLength(int bookId){
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("스레드시작");
//                int cLength = 0;
//                BibleVerseGetter verseGetter = new BibleVerseGetter(bookId,cLength,BibleVerseGetter.FIRST_LINE);
//                //해당 장의 첫번째 절이 있는지 확인해서
//                while(verseGetter.getVerse()!=null){
//                    cLength++;
//                    verseGetter.setVerse(bookId,cLength,BibleVerseGetter.FIRST_LINE);
//                }
//                Message message = handler.obtainMessage();
//                Bundle bundle = new Bundle();
//                bundle.putInt("chapterLength",cLength);
//                message.setData(bundle);
//            }
//        });
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentTransaction transaction;
        main = new main_fragment();
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, main);
        transaction.addToBackStack(null);
        transaction.commit();



    }
    public void onFragmentChange (int index, Bundle bundle){
            Fragment fragment = null;
            FragmentTransaction tempTransaction;
            tempTransaction = getSupportFragmentManager().beginTransaction();
            switch(index) {
                case FRAGMENT_MAIN:
                    fragment = main;
                    break;
                case FRAGMENT_PURCHASE:
                    fragment = new purchase_fragment();
                    break;
                case FRAGMENT_SETTING:
                    fragment = new setting_fragment();
                    break;
                case FRAGMENT_BOOKLIST:
                    fragment = new bookList_fragment();
                    break;
                case FRAGMENT_CHAPTERLIST:
                    fragment = new chapterList_fragment();
                    break;
                case FRAGMENT_VERSELIST:
                    fragment = new verseList_fragment();
                    break;
                case FRAGMENT_INFOVERSE:
                    fragment = new infoverse_fragment();
                    break;
                case FRAGMENT_FINISHED:
                    fragment = new finished_fragment();
                    break;
            }
        if (fragment != null) {
            tempTransaction.replace(R.id.frame, fragment);
        }
        if(bundle !=null){
            fragment.setArguments(bundle);
        }
        tempTransaction.addToBackStack(null);
        tempTransaction.commit();


    }
}