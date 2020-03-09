package com.example.loadmoredatarest;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewRepository {
    //This class provides Singleton network request for hitting API and using LiveData for observing API response
    private static NewRepository newRepository;
    public  static NewRepository getInstance(){
        if(newRepository == null){
            newRepository = new NewRepository();
        }
        return newRepository;
    }
    private PostsApi postsApi;
    public NewRepository(){
        postsApi = RetrofitService.createService(PostsApi.class);
    }
    public MutableLiveData<List<Posts>> getPosts(){
        final MutableLiveData<List<Posts>> newData = new MutableLiveData<>();
        postsApi.getListPosts().enqueue(new Callback<List<Posts>>() {
            @Override
            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {
                newData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {

            }
        });
        return newData;
    }


}
