package com.example.forportfolio;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.forportfolio.adapter.VerseListAdapter;
import com.example.forportfolio.usefulutils.BibleVerseGetter;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class infoverse_fragment extends Fragment {
    TextView verseContent;
    TextView verseLocation;
    ImageButton verseShare;
    ImageButton verseCopy;
    ImageButton verseMark;
    LinearLayout secondPhase;
    Button word1;
    Button word2;
    Button word3;
    LinearLayout firstPhase;
    Button nextVerse;
    Button previousVerse;
    int bookId;
    int chapterId;
    int verseId;
    Bundle bundleGiven;

    public infoverse_fragment() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            bundleGiven = getArguments();
            bookId = bundleGiven.getInt("bookId");
            chapterId = bundleGiven.getInt("chapterId");
            verseId = bundleGiven.getInt("verseId");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_infoverse_fragment, container, false);
        verseContent = rootView.findViewById(R.id.tv_verseContent);
        verseLocation = rootView.findViewById(R.id.tv_verseLocation);
        verseShare = rootView.findViewById(R.id.btn_verseShare);
        verseShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                String sendMessage = verseContent.getText().toString()
                        +" "+ verseLocation.getText().toString();
                intent.putExtra(Intent.EXTRA_TEXT,sendMessage);
                Intent sharedIntent = Intent.createChooser(intent,"share");
                startActivity(sharedIntent);
            }
        });
        verseCopy = rootView.findViewById(R.id.btn_verseCopy);
        verseCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sendMessage = verseContent.getText().toString()
                        +" "+ verseLocation.getText().toString();
                ClipboardManager clipboardManager =
                        (ClipboardManager) getActivity()
                                .getApplicationContext()
                                .getSystemService(getActivity().getApplicationContext().CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("message",sendMessage);
                clipboardManager.setPrimaryClip(clipData);
                Toast.makeText(getActivity(), verseLocation.getText().toString()+" 복사 완료", Toast.LENGTH_LONG).show();
            }
        });
        verseMark = rootView.findViewById(R.id.btn_verseMark);
        verseMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Firebase에다가 북마크 리스트에 저장.
            }
        });
        firstPhase = rootView.findViewById(R.id.ll_firstPhase);
        secondPhase = rootView.findViewById(R.id.ll_secondPhase);
        word1 =rootView.findViewById(R.id.btn_word1);
        word2 =rootView.findViewById(R.id.btn_word2);
        word3 =rootView.findViewById(R.id.btn_word3);
        nextVerse = rootView.findViewById(R.id.btn_nextVerse);
        nextVerse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstPhase.setVisibility(View.GONE);
                secondPhase.setVisibility(View.VISIBLE);

            }
        });
        previousVerse = rootView.findViewById(R.id.btn_previousVerse);
        MainActivity mainActivity = (MainActivity) getActivity();
        Disposable backgroundTask = null;
        HashMap<String, String> map = new HashMap<>();
        backgroundTask = Observable.fromCallable(() -> {
                    BibleVerseGetter verseGetter = new BibleVerseGetter(bookId, chapterId, verseId);
                    String verse = verseGetter.getVerse();
                    map.put("verse",verse);
                    return map;
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<HashMap<String, String>>() {
                    @Override
                    public void accept(HashMap<String, String> stringStringHashMap) throws Exception {
                        verseContent.setText(stringStringHashMap.get("verse"));

                    }

                });
        return rootView;
    }

}