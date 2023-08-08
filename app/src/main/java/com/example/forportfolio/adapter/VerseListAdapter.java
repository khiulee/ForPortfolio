package com.example.forportfolio.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.forportfolio.R;
import com.example.forportfolio.usefulutils.BibleBookId;
import com.example.forportfolio.usefulutils.BibleVerseGetter;
import com.example.forportfolio.viewholder.VerseListViewHolder;

import java.util.ArrayList;

public class VerseListAdapter extends RecyclerView.Adapter<VerseListViewHolder> {
    private ArrayList<Integer> verseIds;
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
    VerseListViewHolder viewHolder;
    public VerseListAdapter(Bundle bundleGiven){
        this.bundleGiven=bundleGiven;
        verseIds=new ArrayList<>();
    }
    @NonNull
    @Override
    public VerseListViewHolder onCreateViewHolder
            (@NonNull ViewGroup parent, int viewType) {
        Context context= parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.verselist_item, parent, false);
        viewHolder = new VerseListViewHolder(context,view, itemClickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull VerseListViewHolder holder, int position) {
        String verseNameText = BibleBookId.OLD_KOREAN_LIST[bundleGiven.getInt("bookId")]
                +" "+(bundleGiven.getInt("chapterId")+1)+"장 "
                +verseIds.get(position).toString()+"절";
        holder.verseName.setText(verseNameText);
    }

    @Override
    public int getItemCount() {return verseIds.size();}
    public void setArrayData(int verseId) {verseIds.add(verseId);}
}
