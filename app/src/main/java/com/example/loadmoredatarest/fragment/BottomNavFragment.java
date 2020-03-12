package com.example.loadmoredatarest.fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.loadmoredatarest.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;

import static androidx.navigation.Navigation.findNavController;

/**
 * A simple {@link Fragment} subclass.
 */
public class BottomNavFragment extends Fragment {
    @BindView(R.id.bottomNavigation)
    BottomNavigationView bottomNavigationView;
    public BottomNavFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bottom_nav, container, false);
          ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       // Navigation.findNavController(bottomNavigationView).navigate(R.id.bottomNavFragment);
       NavController navController = Navigation.findNavController(requireActivity(),R.id.bottomNavFragment);
       NavigationUI.setupWithNavController(bottomNavigationView,navController);
     // bottomNavigationView.setupWithNavController(navController);
    }
}
