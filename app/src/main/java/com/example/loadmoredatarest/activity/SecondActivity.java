package com.example.loadmoredatarest.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.loadmoredatarest.R;
import com.example.loadmoredatarest.adapter.MovieArticleAdapter;
import com.example.loadmoredatarest.model.Article;
import com.example.loadmoredatarest.view_model.ArticleViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SecondActivity extends AppCompatActivity {
    private static final String TAG = SecondActivity.class.getSimpleName();
    private ArticleViewModel articleViewModel;
    LinearLayoutManager layoutManager;
    private MovieArticleAdapter adapter;
    private ArrayList<Article> articleArrayList = new ArrayList<>();
    @BindView(R.id.progress_circular_movie_article)
    ProgressBar progress_circular_movie_article;
    @BindView(R.id.my_recycler_view)
    RecyclerView my_recycler_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);
        initialization();

        getMovieArticles();
    }

    private void getMovieArticles() {
        articleViewModel.getArticleResponseLiveData().observe(this, articleResponse -> {
            if (articleResponse != null) {

                progress_circular_movie_article.setVisibility(View.GONE);
                List<Article> articles = articleResponse.getArticles();
                articleArrayList.addAll(articles);
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void initialization() {
        //progress_circular_movie_article = (ProgressBar) findViewById(R.id.progress_circular_movie_article);
       // my_recycler_view = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(SecondActivity.this);
        my_recycler_view.setLayoutManager(layoutManager);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        my_recycler_view.setHasFixedSize(true);

        // adapter
        adapter = new MovieArticleAdapter(SecondActivity.this, articleArrayList);
        my_recycler_view.setAdapter(adapter);

        // View Model
        articleViewModel = ViewModelProviders.of(this).get(ArticleViewModel.class);
    }
}
