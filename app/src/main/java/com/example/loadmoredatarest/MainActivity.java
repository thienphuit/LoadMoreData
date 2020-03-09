package com.example.loadmoredatarest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private RecyclerviewAdapter newsAdapter;
    private ArrayList<Posts> rowsArrayList = new ArrayList<>();
   // private RecyclerView recyclerView;
    private boolean isLoading = false;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.btnNext)
    Button mBtnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
       // recyclerView = findViewById(R.id.recyclerView);
        ObjectViewModel viewModel = ViewModelProviders.of(this).get(ObjectViewModel.class);
        viewModel.init();
        viewModel.getNewsRepository().observe(this, posts -> {
           // List<Posts> newsArticles = posts;
            rowsArrayList.addAll(posts);
            newsAdapter.notifyDataSetChanged();
        });
        setupRecyclerView();
        mBtnNext.setOnClickListener(view -> {
            Intent intent = new Intent(this,SecondActivity.class);
            startActivity(intent);
        });
       // populateData();
      //  initAdapter();
       // initScrollListener();
    }
    private void setupRecyclerView() {
        if (newsAdapter == null) {
            newsAdapter = new RecyclerviewAdapter(MainActivity.this, rowsArrayList);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(newsAdapter);
           // rvHeadline.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setNestedScrollingEnabled(true);
        } else {
            newsAdapter.notifyDataSetChanged();
        }
    }

    private void initScrollListener() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                if (!isLoading) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == rowsArrayList.size() - 1) {
                        //bottom of list!
                        loadMore();
                        isLoading = true;
                    }
                }
            }
        });
    }
    private void loadMore(){
        rowsArrayList.add(null);
      //  recyclerviewAdapter.notifyItemInserted(rowsArrayList.size() - 1);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                rowsArrayList.remove(rowsArrayList.size() - 1);
                int scrollPosition = rowsArrayList.size();
              //  recyclerviewAdapter.notifyItemRemoved(scrollPosition);
                int currentSize = scrollPosition;
                int nextLimit = currentSize + 10;

                while (currentSize - 1 < nextLimit) {
                   // rowsArrayList.add(""+ currentSize);
                    currentSize++;
                }

              //  recyclerviewAdapter.notifyDataSetChanged();
                isLoading = false;
            }
        }, 2000);
    }

    private void initAdapter() {
      //  recyclerviewAdapter = new RecyclerviewAdapter(rowsArrayList);
       // recyclerView.setAdapter(recyclerviewAdapter);
    }

    private void populateData() {
       /* int i = 0;
        while (i<10){
            rowsArrayList.add("Item"+ i);
            i++;
        }*/

    }

}
