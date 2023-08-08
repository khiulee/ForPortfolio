package com.example.forportfolio;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.forportfolio.adapter.BookListAdapter;
import com.example.forportfolio.usefulutils.BibleBookId;

public class bookList_fragment extends Fragment {
    RecyclerView recyclerView;
    BookListAdapter adapter;
    public bookList_fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_book_list_fragment, container, false);
        MainActivity mainActivity = (MainActivity)getActivity();
        // Inflate the layout for this fragment
        recyclerView = rootView.findViewById(R.id.rcv_bookList);
        recyclerView.setLayoutManager(new LinearLayoutManager(
                mainActivity, RecyclerView.VERTICAL, false));
        adapter = new BookListAdapter();
//        adapter.setArrayData("창세기","진도율:100%");
//        adapter.setArrayData("출애굽기","진도율:00%");//테스트용
        for(int i = 0; i< BibleBookId.OLD_LIST.length ; i++){
            adapter.setArrayData(BibleBookId.OLD_KOREAN_LIST[i], "진도율:0%");
            //todo: 진도율 추가
        }
        adapter.setOnItemClickListener(new BookListAdapter.OnItemClickListener(){
            @Override
            public void onItemClicked(View view, int position) {
                //System.out.println("여기는 프래그먼트, 포지션은"+position);
                Bundle bundle = new Bundle();
                bundle.putString("bookName", BibleBookId.OLD_KOREAN_LIST[position]);
                ((MainActivity)getActivity()).onFragmentChange(MainActivity.FRAGMENT_CHAPTERLIST, bundle);

            }
        });
        recyclerView.setAdapter(adapter);
        return rootView;
    }

}