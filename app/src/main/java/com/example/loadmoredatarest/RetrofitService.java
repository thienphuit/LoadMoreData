package com.example.loadmoredatarest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {
    //Create retrofit for service class
    private static Retrofit retrofit = new Retrofit.Builder()
                                                    .baseUrl("https://jsonplaceholder.typicode.com/")
                                                    .addConverterFactory(GsonConverterFactory.create()).build();
    public static <S> S createService(Class<S> serviceClass){
        return  retrofit.create(serviceClass);
    }
}
