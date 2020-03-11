package com.example.loadmoredatarest.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.loadmoredatarest.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BottomNavFragmentThree extends Fragment {


    public BottomNavFragmentThree() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bottom_nav_fragment_three, container, false);
        return view;
    }

}
