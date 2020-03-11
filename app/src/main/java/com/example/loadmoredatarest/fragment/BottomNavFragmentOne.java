package com.example.loadmoredatarest.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.loadmoredatarest.R;
import com.example.loadmoredatarest.adapter.RecyclerviewAdapter;
import com.example.loadmoredatarest.model.Posts;
import com.example.loadmoredatarest.view_model.ObjectViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BottomNavFragmentOne extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerviewAdapter newsAdapter;
    private ArrayList<Posts> rowsArrayList = new ArrayList<>();
    // private RecyclerView recyclerView;
    private boolean isLoading = false;
    LinearLayoutManager layoutManager;
    ObjectViewModel viewModel;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;


    public BottomNavFragmentOne() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static BottomNavFragmentOne newInstance(String param1, String param2) {
        BottomNavFragmentOne fragment = new BottomNavFragmentOne();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(ObjectViewModel.class);
         viewModel.init();
        viewModel.getNewsRepository().observe(getViewLifecycleOwner(), new Observer<List<Posts>>() {
            @Override
            public void onChanged(List<Posts> posts) {
                rowsArrayList.addAll(posts);
                newsAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bottom_nav_fragment_one, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init() {
        if (newsAdapter == null) {
            // use a linear layout manager
            layoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(layoutManager);

            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            recyclerView.setHasFixedSize(true);

            newsAdapter = new RecyclerviewAdapter(getContext(), rowsArrayList);
           // recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(newsAdapter);
            // rvHeadline.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setNestedScrollingEnabled(true);
            newsAdapter.setmOnItemClickListener(new RecyclerviewAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int rowPosition) {
                    String name = "i am developer";
                    //  viewModel.getNewsRepository().getValue()
                      ArrayList<Posts> vitri1 = new ArrayList<>();
                      Posts posts = new Posts();
                    //  posts.setId("001");
                    //  posts.setBody("hinh anh moi");
                     posts.setTitle("Thien Phu");
                    rowsArrayList.set(rowPosition,posts);

                     viewModel.getMutableLiveData().setValue(rowsArrayList);
                   // mTextViewChangeName.setText(rowsArrayList.get(rowPosition).getTitle());
                }
            });
        } else {
            newsAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
       // mListener = null;
    }

}
