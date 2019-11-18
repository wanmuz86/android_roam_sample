package com.itrainasia.androidroamsample;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class BookRoomRepository {
// All the methods..
    // How to implement
    // Function add,update -> backgrround (Asynctask)
    private WordDao wordDao;
    private BookDao bookDao;

    private LiveData<List<Book>> mAllBooks;

    BookRoomRepository(Application application) {
        BookRoomDatabase db = BookRoomDatabase.getDatabase(application);
        wordDao = db.wordDao();
        bookDao = db.bookDao();
        mAllBooks = bookDao.getAllBooks();
    }

    LiveData<List<Book>> getAllBooks(){
        return mAllBooks;
    }

    public void insert(Book book){
        new insertBookAsyncTask(bookDao).execute(book);
    }


    // 1) https://developer.android.com/reference/android/os/AsyncTask
    private static class insertBookAsyncTask extends AsyncTask<Book,Void,Void>{


        private BookDao mBookDao;

        public insertBookAsyncTask(BookDao bookDao){
            this.mBookDao = bookDao;
        }
        @Override
        protected Void doInBackground(Book... books) {
            this.mBookDao.insert(books[0]);
            return null;
        }
    }

}
