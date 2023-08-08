package com.example.forportfolio.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.forportfolio.R;
import com.example.forportfolio.adapter.BookListAdapter;

public class BookListViewHolder extends RecyclerView.ViewHolder {
    public TextView bookName;
    public TextView progressRate;

    public BookListViewHolder(Context context, @NonNull View itemView, BookListAdapter.OnItemClickListener listener) {
        super(itemView);
        bookName = itemView.findViewById(R.id.tv_bookName);
        progressRate = itemView.findViewById(R.id.tv_progress);
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
