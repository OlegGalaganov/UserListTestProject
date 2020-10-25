package com.galagdev.userlist.di.module;

import androidx.lifecycle.MutableLiveData;

import com.galagdev.userlist.di.scope.ViewModelScope;
import com.galagdev.userlist.model.pogo.User;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class ViewModelModule {

    @Provides
    public CompositeDisposable compositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    @ViewModelScope
    public MutableLiveData<Throwable> provideErrors(){
        return new MutableLiveData<>();
    }

    @Provides
    @ViewModelScope
    public MutableLiveData<User> provideUser(){
        return new MutableLiveData<>();
    }
}
