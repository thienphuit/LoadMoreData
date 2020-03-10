package com.example.loadmoredatarest.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.loadmoredatarest.R;
import com.example.loadmoredatarest.model.Article;

import java.io.Serializable;

public class ThreeActivity extends AppCompatActivity{
    Article bundle = new Article();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);
        //Bundle bundle = getIntent();
        bundle = (Article) getIntent().getSerializableExtra("object");
        if(bundle!=null){

            Log.d("kequa",bundle.getAuthor());
        }


    }
}
