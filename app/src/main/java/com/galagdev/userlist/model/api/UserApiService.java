package com.galagdev.userlist.model.api;

import com.galagdev.userlist.model.pogo.UserResponse;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface UserApiService {
    @GET("users")
    Single<UserResponse> getAllUsers();
}
