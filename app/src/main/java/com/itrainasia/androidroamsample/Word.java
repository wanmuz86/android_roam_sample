package com.itrainasia.androidroamsample;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "word_table",
        foreignKeys = {
        @ForeignKey(entity = Book.class,
                        parentColumns = "id",
                        childColumns = "book_id",
                        onDelete = CASCADE)
        })
public class Word {


    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "word")
    private String mWord;
    private String description;

    @ColumnInfo(name = "book_id")
    public long bookId;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public String getWord() {
        return mWord;
    }

    public void setWord(String mWord) {
        this.mWord = mWord;
    }


}
