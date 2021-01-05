package com.example.filmsrating.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.filmsrating.model.Result;
import com.example.filmsrating.model.ResultDataSource;
import com.example.filmsrating.model.ResultDataSourceFactory;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainActivityViewModel extends AndroidViewModel {

    ResultDataSourceFactory resultDataSourceFactory;
    MutableLiveData<ResultDataSource> resultLiveData;
    Executor executor;
    LiveData<PagedList<Result>> pagedListLiveData;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        resultDataSourceFactory = new ResultDataSourceFactory();
        resultLiveData = resultDataSourceFactory.getMutableLiveData();

        PagedList.Config config = (new PagedList.Config.Builder())
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(10)
                .setPageSize(7)
                .build();
        executor = Executors.newFixedThreadPool(5);
        pagedListLiveData = (new LivePagedListBuilder<Long, Result>(resultDataSourceFactory, config))
                .setFetchExecutor(executor)
                .build();
    }

    public LiveData<PagedList<Result>> getPagedListLiveData() {
        return pagedListLiveData;
    }


}
