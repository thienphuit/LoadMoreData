package com.example.loadmoredatarest.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.loadmoredatarest.reposiroty.article.ArticleRepository;
import com.example.loadmoredatarest.reponse.ArticleResponse;

public class ArticleViewModel extends AndroidViewModel {
    private ArticleRepository articleRepository;
    private LiveData<ArticleResponse> articleResponseLiveData;
    public ArticleViewModel(@NonNull Application application) {
        super(application);
        articleRepository = new ArticleRepository();
        this.articleResponseLiveData = articleRepository.getMoveArticles("movies","84a7decf3110498ea372bd16dd0601e8");
    }
    public LiveData<ArticleResponse> getArticleResponseLiveData() {
        return articleResponseLiveData;
    }
}
