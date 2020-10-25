package com.galagdev.userlist.model.pogo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserResponse {

    @SerializedName("data")
    @Expose
    private List<User> data = null;

    public List<User> getData() {
        return data;
    }
}
