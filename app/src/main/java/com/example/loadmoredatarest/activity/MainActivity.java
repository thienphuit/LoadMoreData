package com.example.loadmoredatarest.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.loadmoredatarest.view_model.ObjectViewModel;
import com.example.loadmoredatarest.R;
import com.example.loadmoredatarest.adapter.RecyclerviewAdapter;
import com.example.loadmoredatarest.model.Posts;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static androidx.navigation.Navigation.findNavController;

public class MainActivity extends AppCompatActivity {
    private RecyclerviewAdapter newsAdapter;
    private ArrayList<Posts> rowsArrayList = new ArrayList<>();
   // private RecyclerView recyclerView;
    private boolean isLoading = false;
    ObjectViewModel viewModel;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.btnNext)
    Button mBtnNext;
    @BindView(R.id.changeName)
    TextView mTextViewChangeName;
    @BindView(R.id.toolbar)
    Toolbar mToolBar;
    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;
    @BindView(R.id.navigationView)
    NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(mToolBar);
        setupNavigation();
        //Navigation navController = findViewController(R.id.mainNavFragment)
       // recyclerView = findViewById(R.id.recyclerView);
        viewModel = ViewModelProviders.of(this).get(ObjectViewModel.class);
      viewModel.init();
      /*  viewModel.getMutableLiveData().observe(this,posts -> {
            rowsArrayList.addAll(posts);
            newsAdapter.notifyDataSetChanged();
        });*/
        /*viewModel.getNewsRepository().observe(this, posts -> {
           // List<Posts> newsArticles = posts;
            rowsArrayList.addAll(posts);
            newsAdapter.notifyDataSetChanged();
        });*/
       // setupRecyclerView();
      /*  mBtnNext.setOnClickListener(view -> {
            Intent intent = new Intent(this, SecondActivity.class);
            startActivity(intent);
        });*/
       // populateData();
      //  initAdapter();
       // initScrollListener();
    }

    private void setupNavigation() {
       // setSupportActionBar();
        NavController navController =Navigation.findNavController(this,R.id.mainNavFragment);
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout);
        NavigationUI.setupWithNavController(navigationView,navController);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(
                Navigation.findNavController(this, R.id.mainNavFragment), drawerLayout);
    }

    private void setupRecyclerView() {
        if (newsAdapter == null) {
            newsAdapter = new RecyclerviewAdapter(MainActivity.this, rowsArrayList);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(newsAdapter);
           // rvHeadline.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setNestedScrollingEnabled(true);
            newsAdapter.setmOnItemClickListener(new RecyclerviewAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int rowPosition) {
                    String name = "i am developer";
                  //  viewModel.getNewsRepository().getValue()
                  //  ArrayList<Posts> vitri1 = new ArrayList<>();
                 //   Posts posts = new Posts();
                  //  posts.setId("001");
                  //  posts.setBody("hinh anh moi");
                   // posts.setTitle("Thien Phu");
                    //rowsArrayList.set(rowPosition,posts);

                   // viewModel.getMutableLiveData().setValue(rowsArrayList);
                    mTextViewChangeName.setText(rowsArrayList.get(rowPosition).getTitle());
                }
            });
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
