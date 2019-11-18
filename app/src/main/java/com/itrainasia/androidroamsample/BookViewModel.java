package com.itrainasia.androidroamsample;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class BookViewModel extends AndroidViewModel {

    private BookRoomRepository mRoomRepository;

    private LiveData<List<Book>> mAllBooks;


    public BookViewModel(@NonNull Application application) {
        super(application);
        mRoomRepository = new BookRoomRepository(application);
        mAllBooks = mRoomRepository.getAllBooks();
    }

    LiveData<List<Book>> getAllBooks(){ return mAllBooks;};

    public void insert(Book book) { mRoomRepository.insert(book);}

}
