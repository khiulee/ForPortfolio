package com.example.forportfolio.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.forportfolio.R;
import com.example.forportfolio.viewholder.BookListViewHolder;

import java.util.ArrayList;
import java.util.List;

public class BookListAdapter extends RecyclerView.Adapter<BookListViewHolder> {
    private ArrayList<String> bookNames;
    private ArrayList<String> progresses;
    //===== For Click =====
    public interface OnItemClickListener{
        void onItemClicked(View view ,int position);
    }
    private OnItemClickListener itemClickListener;

    public void setOnItemClickListener (OnItemClickListener listener){
        itemClickListener=listener;
    }
    //=====================

    BookListViewHolder viewHolder;
    public BookListAdapter() {
        bookNames = new ArrayList<>();
        progresses = new ArrayList<>();
    }

    @NonNull
    @Override
    public BookListViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.booklist_item, parent, false);
        viewHolder = new BookListViewHolder(context,view,itemClickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BookListViewHolder holder, int position) {
        String bookNameText = bookNames.get(position);
        String progressText = progresses.get(position);
        holder.bookName.setText(bookNameText);
        holder.progressRate.setText(progressText);
    }

    @Override
    public int getItemCount() {
        return bookNames.size();
    }
    public void setArrayData(String bookName, String progressRate){
        bookNames.add(bookName);
        progresses.add(progressRate);

    }
}
