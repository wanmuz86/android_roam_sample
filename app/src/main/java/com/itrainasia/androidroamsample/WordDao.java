package com.itrainasia.androidroamsample;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface WordDao {

    @Insert
    void insert(Word word);

    @Query("DELETE FROM word_table")
    void deleteAll();

    @Query("SELECT * FROM word_table")
    List<Word> getAllWords();

    @Update
    void update(Word word);

//    @Query("SELECT * FROM  word_table WHERE word=:name")
//    Word getWordByName(String name);
}
