package com.itrainasia.androidroamsample;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface BookDao {

    @Insert
    void insert(Book book);

    @Query("DELETE FROM book_table")
    void deleteAll();

    @Query("SELECT * FROM book_table")
    LiveData<List<Book>> getAllBooks();

    @Delete
    void delete(Book book);

    @Query("SELECT * FROM word_table WHERE book_id=:bookId")
    LiveData<List<Word>> getWordForBook(long bookId);

    @Query("SELECT * FROM book_table WHERE id=:bookId LIMIT 1")
    LiveData<Book> getBookFromId(long bookId);

//    @Query("SELECT name,mDescription,id FROM  book_table WHERE id=:id")
//    Book getBookById(int id);
}
