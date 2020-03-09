package com.example.loadmoredatarest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Posts {
    @SerializedName("userId")
    @Expose
    private String userId;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("body")
    @Expose
    private String body;
   /* public Posts (String userid){
        this.userId = userid;
    }*/

    public String getUserId() {
        return userId;
    }


    public String getId() {
        return id;
    }


    public String getTitle() {
        return title;
    }


    public String getBody() {
        return body;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
