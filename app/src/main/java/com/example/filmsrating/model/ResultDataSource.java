package com.example.filmsrating.model;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.example.filmsrating.network.GetNetService;
import com.example.filmsrating.network.RetrofitInstance;
import com.example.filmsrating.ui.MainActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultDataSource extends PageKeyedDataSource<Integer, Result> {
    GetNetService netService;

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Result> callback) {
        netService = RetrofitInstance.getRetrofitInstance().create(GetNetService.class);
        Call<Page> data = netService.getPopular(1);
        data.enqueue(new Callback<Page>() {
            @Override
            public void onResponse(Call<Page> call, Response<Page> response) {
                if (response.isSuccessful()) {
                    List<Result> resultList = response.body().getResults();
                    Log.d(MainActivity.TAG, "filmListSize " + resultList.size());
                    callback.onResult(resultList, 1, 2);
                } else {
                    Log.e(MainActivity.TAG, "filmList" + null);
                }
            }

            @Override
            public void onFailure(Call<Page> call, Throwable t) {
                Log.e(MainActivity.TAG, "some error " + t);
            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Result> callback) {
        netService = RetrofitInstance.getRetrofitInstance().create(GetNetService.class);
        if (params.key > 1) {
            Call<Page> data = netService.getPopular(params.key-1);
            data.enqueue(new Callback<Page>() {
                @Override
                public void onResponse(Call<Page> call, Response<Page> response) {
                    List<Result> resultList = response.body().getResults();
                    callback.onResult(resultList, params.key - 1);
                }

                @Override
                public void onFailure(Call<Page> call, Throwable t) {
                    Log.d(MainActivity.TAG, "some error " + t);
                }
            });
        }
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Result> callback) {
        netService = RetrofitInstance.getRetrofitInstance().create(GetNetService.class);
        Call<Page> data = netService.getPopular(params.key+1);
        data.enqueue(new Callback<Page>() {
            @Override
            public void onResponse(Call<Page> call, Response<Page> response) {
                List<Result> resultList = response.body().getResults();
                callback.onResult(resultList, params.key + 1);
            }

            @Override
            public void onFailure(Call<Page> call, Throwable t) {
                Log.d(MainActivity.TAG, "some error " + t);
            }
        });
    }

}

