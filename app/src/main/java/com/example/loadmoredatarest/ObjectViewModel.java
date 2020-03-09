package com.example.loadmoredatarest;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

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
}
