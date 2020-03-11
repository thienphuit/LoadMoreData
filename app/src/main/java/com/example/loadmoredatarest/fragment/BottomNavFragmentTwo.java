package com.example.loadmoredatarest.fragment;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.loadmoredatarest.R;
import com.example.loadmoredatarest.adapter.MovieArticleAdapter;
import com.example.loadmoredatarest.model.Article;
import com.example.loadmoredatarest.view_model.ArticleViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class BottomNavFragmentTwo extends Fragment {
    private ArticleViewModel articleViewModel;
    LinearLayoutManager layoutManager;
    private MovieArticleAdapter adapter;
    private ArrayList<Article> articleArrayList = new ArrayList<>();
    @BindView(R.id.progress_circular_movie_article)
    ProgressBar progress_circular_movie_article;
    @BindView(R.id.my_recycler_view)
    RecyclerView my_recycler_view;
    public BottomNavFragmentTwo() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getMovieArticles();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bottom_nav_fragment_two, container, false);
                ButterKnife.bind(this,view);
        initialization();
        return view;
    }
    private void getMovieArticles() {
        articleViewModel.getArticleResponseLiveData().observe(getViewLifecycleOwner(), articleResponse -> {
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
        layoutManager = new LinearLayoutManager(getContext());
        my_recycler_view.setLayoutManager(layoutManager);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        my_recycler_view.setHasFixedSize(true);

        // adapter
        adapter = new MovieArticleAdapter(getContext(), articleArrayList, article -> {
            //handle click item recycler view
        });
        my_recycler_view.setAdapter(adapter);

        // View Model
        articleViewModel = ViewModelProviders.of(this).get(ArticleViewModel.class);
    }

}
