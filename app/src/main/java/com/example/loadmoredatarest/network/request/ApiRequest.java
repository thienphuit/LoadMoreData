package com.example.loadmoredatarest.network.request;

import com.example.loadmoredatarest.reponse.ArticleResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiRequest {
    @GET("v2/everything/")
    Call<ArticleResponse> getMovieArchiles(@Query("q") String query,
                                           @Query("apikey") String apiKey);
}