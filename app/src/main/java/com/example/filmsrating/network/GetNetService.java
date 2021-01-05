package com.example.filmsrating.network;

import com.example.filmsrating.model.Page;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface GetNetService {

    @Headers("Content-Type: application/json")

    @GET("https://api.themoviedb.org/3/movie/popular?api_key=c95a75af7a2ca4dd32da047bac98a2fc&language=en-US&region=US")
    Call<Page> getPopular(@Query("page") int page);

}

