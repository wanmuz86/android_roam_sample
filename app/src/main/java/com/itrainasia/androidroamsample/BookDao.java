package com.itrainasia.androidroamsample;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface BookDao {

    @Insert
    void insert(Book book);

    @Query("DELETE FROM word_table")
    void deleteAll();

    @Query("SELECT * FROM book_table")
    LiveData<List<Book>> getAllBooks();

//    @Query("SELECT name,mDescription,id FROM  book_table WHERE id=:id")
//    Book getBookById(int id);
}
