package com.itrainasia.androidroamsample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    // 1) Create an variable of ViewModel;
    private BookViewModel bookViewModel;
    private WordViewModel wordViewModel;
    TextView nameTextView, descTextView;
    Button button;
    Long bookId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        nameTextView = findViewById(R.id.detailNameTextView);
        descTextView = findViewById(R.id.detailDescTextView);
        button = findViewById(R.id.addButton);

        //2) Instatiate the ViewModel
        bookViewModel = ViewModelProviders.of(this).get(BookViewModel.class);
        wordViewModel = ViewModelProviders.of(this).get(WordViewModel.class);

        Intent intent = getIntent();
        //bookId = intent.getLongExtra("itemId",0);
        Book newBook = intent.getParcelableExtra("item");
        bookId = newBook.getId();
        Log.d("debug",bookId+"");

//3) Get the book by Id using LiveData
        bookViewModel.getBookById(bookId).observe(this, new Observer<Book>() {
            @Override
            public void onChanged(Book book) {
                nameTextView.setText(book.getName());
                descTextView.setText(book.getDescription());
            }
        });



        RecyclerView recyclerView = findViewById(R.id.wordRecyclerView);

        final WordListAdapter adapter = new WordListAdapter();
        recyclerView.setAdapter(adapter);
        bookViewModel.getWordByBooks(bookId).observe(this,new Observer<List<Word>>(){

            @Override
            public void onChanged(List<Word> words) {
                Log.d("debug from observer",words.toString());

                adapter.wordList = words;
                adapter.notifyDataSetChanged();

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DetailActivity.this);
                builder.setTitle("Title");


                final EditText input = new EditText(DetailActivity.this);

                input.setInputType(InputType.TYPE_CLASS_TEXT );
                builder.setView(input);


                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("debug",input.getText().toString());
                        Word word = new Word();
                        word.bookId = bookId;
                        word.setWord(input.getText().toString());
                        wordViewModel.insert(word);


                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }
        });
    }

    public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder>{

        public List<Word> wordList = new ArrayList<>();


        @NonNull
        @Override
        public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new WordViewHolder(LayoutInflater.from(parent.getContext()),parent);
        }


        @Override
        public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
            Log.d("debug from adapter",wordList.get(position).getWord());
            holder.wordTextView.setText(wordList.get(position).getWord());
        }

        @Override
        public int getItemCount() {

            Log.d("debug",wordList.size()+"");
            Log.d("debug",wordList.toString());
            return wordList.size();
        }

        public class WordViewHolder extends RecyclerView.ViewHolder{

            TextView wordTextView;

            public WordViewHolder(LayoutInflater inflater, ViewGroup parent) {
                super(inflater.inflate(R.layout.wordrow,  parent, false));
                wordTextView = itemView.findViewById(R.id.wordTextView);
            }
        }
    }
}
