package com.example.loadmoredatarest.view_model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.loadmoredatarest.model.Posts;
import com.example.loadmoredatarest.reposiroty.newres.NewRepository;

import java.util.List;

public class ObjectViewModel extends ViewModel {
    private MutableLiveData<List<Posts>> mutableLiveData;
    private NewRepository newRepository;
    public  void init(){
        if(mutableLiveData!=null){
            return;
        }
        newRepository = NewRepository.getInstance();
        mutableLiveData = newRepository.getPosts();
    }
    public LiveData<List<Posts>> getNewsRepository(){
        return  mutableLiveData;
    }
    public MutableLiveData<List<Posts>> getMutableLiveData(){
        return  mutableLiveData;
    }
    private void setInput(List<Posts> input){
        mutableLiveData.setValue(input);
    }
}
