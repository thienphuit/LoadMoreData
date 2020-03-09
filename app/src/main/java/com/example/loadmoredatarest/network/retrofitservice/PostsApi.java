package com.example.loadmoredatarest.network.retrofitservice;

import com.example.loadmoredatarest.model.Posts;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PostsApi {
    @GET("posts")
    Call<List<Posts>> getListPosts();
}
