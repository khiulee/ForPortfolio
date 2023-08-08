package com.example.forportfolio.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.forportfolio.R;
import com.example.forportfolio.adapter.BookListAdapter;
import com.example.forportfolio.adapter.VerseListAdapter;

public class VerseListViewHolder extends RecyclerView.ViewHolder {
    public TextView verseName;
    public TextView verseContent;

    public VerseListViewHolder(Context context, @NonNull View itemView, VerseListAdapter.OnItemClickListener listener) {
        super(itemView);
        verseName = itemView.findViewById(R.id.tv_verseName);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = getAdapterPosition();
                if(position != RecyclerView.NO_POSITION){
                    if(listener != null){
                        listener.onItemClicked(v, position);
                    }
                }
//                System.out.println("여기는 뷰홀더, 포지션은"+position);

            }
        });
    }

}