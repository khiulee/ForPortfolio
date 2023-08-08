package com.example.forportfolio.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.forportfolio.R;
import com.example.forportfolio.adapter.BookListAdapter;
import com.example.forportfolio.adapter.ChapterListAdapter;

public class ChapterListViewHolder extends RecyclerView.ViewHolder {
    public TextView chapterId;

    public ChapterListViewHolder(Context context, @NonNull View itemView, ChapterListAdapter.OnItemClickListener listener) {
        super(itemView);
        chapterId = itemView.findViewById(R.id.tv_chapterName);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = getAdapterPosition();
                if(position != RecyclerView.NO_POSITION){
                    if(listener != null){
                        listener.onItemClicked(v, position);
                    }
                }
            }
        });
    }
}
