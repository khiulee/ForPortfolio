package com.example.forportfolio;

import static android.os.Build.VERSION_CODES.M;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.forportfolio.adapter.VerseListAdapter;
import com.example.forportfolio.usefulutils.BibleVerseGetter;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class verseList_fragment extends Fragment {
    private RecyclerView recyclerView;
    private VerseListAdapter adapter;
    private int bookId;
    private int chapterId;
    private Bundle bundleGiven;
    private Integer chapterLength;
    public verseList_fragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            bundleGiven = getArguments();
            bookId = bundleGiven.getInt("bookId");
            chapterId = bundleGiven.getInt("chapterId");

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_verse_list_fragment, container, false);
        MainActivity mainActivity = (MainActivity) getActivity();
        recyclerView = rootView.findViewById(R.id.rcv_verseList);
        recyclerView.setLayoutManager((new LinearLayoutManager(
                mainActivity,RecyclerView.VERTICAL, false)));
        adapter = new VerseListAdapter(bundleGiven);
        Disposable backgroundTask = null;
        HashMap<String, Integer> map = new HashMap<>();
        backgroundTask = Observable.fromCallable(() -> {
            int cLength = 0;
            BibleVerseGetter verseGetter = new BibleVerseGetter(bookId, chapterId, cLength);
            while(verseGetter.getVerse()!=null){
                cLength++;
                verseGetter.setVerse(bookId,chapterId,cLength);
            }
            map.put("chapterLength",cLength);
            return map;
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<HashMap<String, Integer>>() {
                    @Override
                    public void accept(HashMap<String, Integer> stringIntegerHashMap) throws Exception {
                        chapterLength = stringIntegerHashMap.get("chapterLength");
                        for (int i = 1; i <= chapterLength; i++) {
                            adapter.setArrayData(i);
                        }
                        adapter.setOnItemClickListener(new VerseListAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClicked(View view, int position) {
                                Bundle bundle = new Bundle();
                                bundle.putInt("bookId", bookId);
                                bundle.putInt("chapterId", chapterId);
                                bundle.putInt("verseId", position);
                                ((MainActivity) getActivity()).onFragmentChange(MainActivity.FRAGMENT_INFOVERSE, bundle);
                            }
                        });
                        recyclerView.setAdapter(adapter);
                    }
                });

            return rootView;

    }
}