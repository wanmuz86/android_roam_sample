package com.itrainasia.androidroamsample;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface WordDao {

    @Insert
    void insert(Word word);

    @Query("DELETE FROM word_table")
    void deleteAll();

    @Query("SELECT * FROM word_table")
    List<Word> getAllWords();

//    @Query("SELECT * FROM  word_table WHERE word=:name")
//    Word getWordByName(String name);
}
