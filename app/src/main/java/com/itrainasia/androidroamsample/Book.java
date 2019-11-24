package com.itrainasia.androidroamsample;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "book_table")
public class Book implements Parcelable {

    @NonNull
    @PrimaryKey(autoGenerate = true)

    private long id;

    @ColumnInfo(name = "name")
    private String mName;
    private String mDescription;

    public Book(){}


    protected Book(Parcel in) {
        id = in.readLong();
        mName = in.readString();
        mDescription = in.readString();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeString(mName);
        parcel.writeString(mDescription);
    }
}
