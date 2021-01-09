package com.example.filmsrating.model;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.example.filmsrating.ui.MainActivity;

public class ResultDataSourceFactory extends DataSource.Factory {

    private MutableLiveData<ResultDataSource> mutableLiveData = new MutableLiveData<>();

    @NonNull
    @Override
    public DataSource create() {
        ResultDataSource resultDataSource = new ResultDataSource();
        mutableLiveData.postValue(resultDataSource);
        Log.d(MainActivity.TAG, "resultDataSource" + resultDataSource);
        return resultDataSource;
    }

    public MutableLiveData<ResultDataSource> getMutableLiveData() {
        return mutableLiveData;
    }
}

