package com.itrainasia.androidroamsample;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "book_table")
public class Book {

    @NonNull
    @PrimaryKey(autoGenerate = true)

    private long id;

    @ColumnInfo(name = "name")
    private String mName;
    private String mDescription;

    public String getName() {
        return mName;
    }

    public void name(String name) {
        this.mName = name;
    }

    public String getDescription() {
        return mDescription;
    }

    public void description(String desc) {
        this.mDescription = desc;
    }

    public long getId() {
        return id;
    }

    public void setId(@NonNull long id) {
        this.id = id;
    }
}
