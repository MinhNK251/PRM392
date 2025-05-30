package com.example.lab5_2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab5_2.R;
import com.example.lab5_2.model.Book;
import java.util.ArrayList;

public class BookAdapter extends  RecyclerView.Adapter<BookAdapter.ViewHolder>{

    private ArrayList<Book> bookList;

    public BookAdapter(ArrayList<Book> bookList){
        this.bookList = bookList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_booklist, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BookAdapter.ViewHolder holder, int position) {
        Book book = bookList.get(position);
        holder.ivCover.setImageResource(book.getCover());
        holder.tvTitle.setText(book.getTitle());
        holder.tvDescription.setText(book.getDescription());
        holder.tvCategory.setText(book.getCategory());
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle, tvDescription, tvCategory;
        ImageView ivCover;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            ivCover = itemView.findViewById(R.id.iv_cover);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDescription = itemView.findViewById(R.id.tv_description);
            tvCategory = itemView.findViewById(R.id.tv_category);
        }
    }
}
