package com.example.loadmoredatarest.my_inteface;

import com.example.loadmoredatarest.model.NoticeList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface getNoticeDataService {
    @GET("bins/1bsqcn/")
    Call<NoticeList> getNoticeData();
}
