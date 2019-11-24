package com.itrainasia.androidroamsample;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    // 1) Define the interface in Adapter

    public interface OnItemClickListener{
        void onItemClick(Book book);
    }

    public List<Book> books = new ArrayList<>();
    //2 ) Create variable instance

    private OnItemClickListener listener;


    //3 ) Retrive the listener through constructor
    public CustomAdapter(OnItemClickListener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomViewHolder(LayoutInflater.from(parent.getContext()),parent);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.nameTextView.setText(books.get(position).getName());
        holder.descTextView.setText(books.get(position).getDescription());

        //5 Call the onBindViewHolder

        holder.bind(books.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder{
        TextView nameTextView, descTextView;

        public CustomViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.custom_row,  parent, false));
            nameTextView = itemView.findViewById(R.id.nameTextView);
            descTextView = itemView.findViewById(R.id.descTextView);
        }

        // 4) Create the bind function
        public void bind(final Book obj, final OnItemClickListener listener){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(obj);
                }
            });
        }

    }

    public Book getBookAtPosition(int position){
        return books.get(position);
    }
}
