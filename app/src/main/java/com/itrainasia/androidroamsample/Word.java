package com.itrainasia.androidroamsample;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "word_table",
        foreignKeys = {
        @ForeignKey(entity = Book.class,
                        parentColumns = "id",
                        childColumns = "book_id")
        })
public class Word {


    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "word")
    private String mWord;
    private String description;

    @ColumnInfo(name = "book_id")
    public int bookId;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getWord() {
        return mWord;
    }

    public void setWord(String mWord) {
        this.mWord = mWord;
    }


}
