package com.galagdev.userlist.di.module;

import com.galagdev.userlist.di.scope.ViewModelScope;
import com.galagdev.userlist.model.api.UserApiService;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@ViewModelScope
public class ApiServiceModule {

    private static final String BASE_URL = "https://reqres.in/api/";

    @Provides
    @ViewModelScope
    public Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
    }

    @Provides
    @ViewModelScope
    public UserApiService getApiService(Retrofit retrofit) {
        return retrofit.create(UserApiService.class);
    }
}
