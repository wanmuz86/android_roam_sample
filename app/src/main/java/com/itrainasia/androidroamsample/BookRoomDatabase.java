package com.itrainasia.androidroamsample;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Word.class, Book.class}, version = 4, exportSchema = false)
public abstract class BookRoomDatabase extends RoomDatabase {

    // List of DAOS

    public abstract WordDao wordDao();
    public abstract BookDao bookDao();
    private static BookRoomDatabase INSTANCE;

    static BookRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (BookRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            BookRoomDatabase.class, "bookroom_db")
                            .fallbackToDestructiveMigration()
                            .build();

                }
            }
        }
        return INSTANCE;
    }
    static final Migration FROM_1_TO_2 = new Migration(1, 2) {
        @Override
        public void migrate(final SupportSQLiteDatabase database) {
        //    database.execSQL("ALTER TABLE Rep ADD COLUMN createdAt TEXT");
        }
    };
}
