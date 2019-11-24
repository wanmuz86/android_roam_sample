package com.itrainasia.androidroamsample;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class WordViewModel  extends AndroidViewModel {

    private BookRoomRepository mRoomRepository;

    private LiveData<List<Word>> mAllWords;


    public WordViewModel(@NonNull Application application) {
        super(application);
        mRoomRepository = new BookRoomRepository(application);
       // mAllWords = mRoomRepository.getA();
    }


    public void insert(Word word) { mRoomRepository.insertWord(word);}


}
