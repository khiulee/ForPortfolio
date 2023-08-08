package com.example.forportfolio;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.forportfolio.adapter.ChapterListAdapter;
import com.example.forportfolio.usefulutils.BibleVerseGetter;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class chapterList_fragment extends Fragment{
    private RecyclerView recyclerView;
    private ChapterListAdapter adapter;
    private Bundle bundleGiven;
    private int bookId;
    private Integer bookLength;
    public chapterList_fragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //이전 프래그먼트에서 bookId를 받아온다.
        if (getArguments() != null) {
            bundleGiven = getArguments();
            bookId = bundleGiven.getInt("bookId");
        }
        System.out.println("다음 프래그먼트");


    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_chapter_list_fragment, container, false);
        MainActivity mainActivity = (MainActivity) getActivity();
        recyclerView = rootView.findViewById(R.id.rcv_chapterList);
        recyclerView.setLayoutManager(new LinearLayoutManager(
                mainActivity,RecyclerView.VERTICAL, false));
        adapter = new ChapterListAdapter(bundleGiven);
        //adapter.setArrayData(1);//테스트용
        Disposable backgroundTask;
        HashMap<String, Integer> map = new HashMap<>();
        backgroundTask = Observable.fromCallable(() -> {
            //doInBackground(task에서 실행할 코드 여기에 작성)
            System.out.println("스레드시작");
            int bLength = 0;
            BibleVerseGetter verseGetter = new BibleVerseGetter(bookId,bLength,BibleVerseGetter.FIRST_LINE);
            //bookId=int 자료형으로 0부터 창세기,출애굽기 순으로 진행
            //BibleVerseGetter의 생성자는, bookId를 통해 책의 이름을 찾아, bLength번째의 장의 첫번째 절을 찾는 것을 의미
            //예를들어 bookId가 0(창세기), bLength가 0이라면 창세기 1장(0번째장) 1절을 참조하게 됨)
            //성경에서 해당 장의 첫번째 절이 있는지 확인하는 함수
            System.out.println("스레드 중간");
            while(verseGetter.getVerse()!=null){
                bLength++;
                verseGetter.setVerse(bookId,bLength,BibleVerseGetter.FIRST_LINE);
            }
            //예를 들어 창세기에서 51장 1절이 null값이 뜨면, chapterLength는 50이 된다.
            map.put("bookLength",bLength);
            System.out.println("스레드 끝");
            return map;

        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<HashMap<String, Integer>>() {
            @Override
            public void accept(HashMap<String, Integer> stringIntegerHashMap) throws Exception {
                bookLength= stringIntegerHashMap.get("bookLength");
                for(int i = 1; i<=bookLength; i++){//여기서 nullpointerException발생, chapterLength가 비어있다고 뜸.
                    adapter.setArrayData(i);
                }

                adapter.setOnItemClickListener(new ChapterListAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClicked(View view, int position) {
                        Bundle bundle = new Bundle();
                        bundle.putInt("bookId",bundleGiven.getInt("bookId"));
                        bundle.putInt("chapterId",position);
                        ((MainActivity)getActivity()).onFragmentChange(MainActivity.FRAGMENT_VERSELIST, bundle);
                    }
                });
                recyclerView.setAdapter(adapter);

            }
        });
        System.out.println("onCreateView함수종료");
        return rootView;

    }
}