package com.itrainasia.androidroamsample;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class BookRoomRepository {
// All the methods..
    // How to implement
    // Function add,update -> backgrround (Asynctask)
    private WordDao wordDao;
    private BookDao bookDao;

    private LiveData<List<Book>> mAllBooks;
    private LiveData<List<Word>> mAllWords;
//    private MutableLiveData<List<Book>> searchResults =
//            new MutableLiveData<>();

    BookRoomRepository(Application application) {
        BookRoomDatabase db = BookRoomDatabase.getDatabase(application);
        wordDao = db.wordDao();
        bookDao = db.bookDao();
        mAllBooks = bookDao.getAllBooks();
//        searchResults =
    }

    LiveData<List<Book>> getAllBooks(){
        return mAllBooks;
    }

    public LiveData<Book> getBookById(long id) {

        return bookDao.getBookFromId(id);

    }

    public void insert(Book book){

        new insertBookAsyncTask(bookDao).execute(book);
    }

    public void deleteAll(){
        new deleteAllAsyncTask(this.bookDao).execute();
    }

    public void deleteBook(Book book){
        new deleteBookAsyncTask(this.bookDao).execute(book);

    }

    public LiveData<List<Word>> getAllWordsByBook(long id){
        mAllWords = bookDao.getWordForBook(id);
        return mAllWords;

    }

    public void insertWord(Word word){
        new insertWordAsyncTask(this.wordDao).execute(word);
    }
//    public void asyncFinished(List<Book> results){
//        searchResults.setValue(results);
//    }
//
//    public MutableLiveData<List<Book>> getSearchResults() {
//        return searchResults;
//    }



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
    // 1) https://developer.android.com/reference/android/os/AsyncTask
    private static class deleteAllAsyncTask extends AsyncTask<Void,Void,Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        private BookDao mBookDao;
        public deleteAllAsyncTask(BookDao bookDao){
            this.mBookDao = bookDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            this.mBookDao.deleteAll();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }

    private static class deleteBookAsyncTask extends AsyncTask<Book,Void,Void>{

        private BookDao mBookDao;
        public deleteBookAsyncTask(BookDao bookDao){
            this.mBookDao = bookDao;
        }


        @Override
        protected Void doInBackground(Book... books) {
            this.mBookDao.delete(books[0]);
            return null;
        }
    }

//    private static class getBookAsycTask extends AsyncTask<Long,Void,Book>{
//
//        private BookDao mBookDao;
//        public BookRoomRepository delegate = null;
//        public getBookAsycTask(BookDao bookDao){
//            this.mBookDao = bookDao;
//        }
//
//
//        @Override
//        protected Book doInBackground(Long... ids) {
//            this.mBookDao.getBookFromId(ids[0]);
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Book book) {
//            Log.d("debug after search",book.toString());
//            super.onPostExecute(book);
//            delegate.asyncFinished(book);
//        }
//    }

    private static class insertWordAsyncTask extends AsyncTask<Word,Void,Void>{


        private WordDao mWordDao;

        public insertWordAsyncTask(WordDao wordDao){
            this.mWordDao = wordDao;
        }
        @Override
        protected Void doInBackground(Word... words) {
            this.mWordDao.insert(words[0]);
            return null;
        }
    }


}
