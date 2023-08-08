package com.example.forportfolio.adapter;

import android.content.Context;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.forportfolio.R;
import com.example.forportfolio.viewholder.ChapterListViewHolder;

import java.util.ArrayList;

public class ChapterListAdapter extends RecyclerView.Adapter<ChapterListViewHolder> {
    private ArrayList<Integer> chapterIds;
    Bundle bundleGiven;


    //===== For Click =====
    public interface OnItemClickListener{
        void onItemClicked(View view, int position);
    }
    private OnItemClickListener itemClickListener;
    public void setOnItemClickListener(OnItemClickListener listener){
        itemClickListener = listener;
    }
    //====================

    ChapterListViewHolder viewHolder;
    public ChapterListAdapter(Bundle bundleGiven){
        this.bundleGiven=bundleGiven;
        chapterIds = new ArrayList<>();
    }

    @NonNull
    @Override
    public ChapterListViewHolder onCreateViewHolder
            (@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.chapterlist_item,parent,false);
        viewHolder = new ChapterListViewHolder(context,view, itemClickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ChapterListViewHolder holder, int position) {
        String chapterIdText = bundleGiven.getString("bookName")
                + chapterIds.get(position).toString()
                +"장";
        holder.chapterId.setText(chapterIdText);
    }

    @Override
    public int getItemCount() {
        return chapterIds.size();
    }
    public void setArrayData(int chapterId){
        chapterIds.add(chapterId);
    }
}
