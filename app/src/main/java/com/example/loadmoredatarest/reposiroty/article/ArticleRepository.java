package com.example.loadmoredatarest.reposiroty.article;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.loadmoredatarest.network.request.ApiRequest;
import com.example.loadmoredatarest.network.request.RetrofitRequest;
import com.example.loadmoredatarest.reponse.ArticleResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleRepository {
    private static final String TAG = ArticleRepository.class.getSimpleName();
    private ApiRequest apiRequest;

    public ArticleRepository() {
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
    }

    public LiveData<ArticleResponse> getMoveArticles(String query, String key) {
        MutableLiveData<ArticleResponse> data = new MutableLiveData<>();
        apiRequest.getMovieArchiles(query,key).enqueue(new Callback<ArticleResponse>() {
            @Override
            public void onResponse(Call<ArticleResponse> call, Response<ArticleResponse> response) {
                Log.d(TAG, "onResponse response:: " + response);
                if (response.body() != null) {
                    data.setValue(response.body());
                         Log.d(TAG, "articles total result:: " + response.body().getTotalResults());
                        Log.d(TAG, "articles size:: " + response.body().getArticles().size());
                       Log.d(TAG, "articles title pos 0:: " + response.body().getArticles().get(0).getTitle());
                }
            }

            @Override
            public void onFailure(Call<ArticleResponse> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }
}
