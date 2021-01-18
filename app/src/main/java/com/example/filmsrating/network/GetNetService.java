package com.example.filmsrating.network;

import com.example.filmsrating.model.Page;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface GetNetService {
    final String QUERY_PARAM_API_KEY = "api_key";
    final String QUERY_LANGUAGE = "language";
    final String QUERY_PARAM_PAGE = "page";
    final String QUERY_PARAM_REGION = "region";


    final String API_KEY = "c95a75af7a2ca4dd32da047bac98a2fc";
    final String LANGUAGE = "en-US";
    final String REGION = "US";


    @GET("movie/popular")
    Call<Page> getPopular(@Query(QUERY_PARAM_API_KEY) String API_KEY,
                          @Query(QUERY_LANGUAGE) String LANGUAGE,
                          @Query(QUERY_PARAM_PAGE) int page,
                          @Query(QUERY_PARAM_REGION) String region);


}

