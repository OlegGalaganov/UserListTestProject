package com.galagdev.userlist.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.galagdev.userlist.di.component.AppComponent;
import com.galagdev.userlist.di.component.DaggerAppComponent;
import com.galagdev.userlist.di.component.ViewModelComponent;
import com.galagdev.userlist.di.module.ApiServiceModule;
import com.galagdev.userlist.di.module.AppContextModule;
import com.galagdev.userlist.di.module.RoomModule;
import com.galagdev.userlist.di.module.ViewModelModule;
import com.galagdev.userlist.model.api.UserApiService;
import com.galagdev.userlist.model.data.UserDatabase;
import com.galagdev.userlist.model.pogo.User;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UserViewModel extends AndroidViewModel {

    @Inject CompositeDisposable compositeDisposable;
    @Inject UserDatabase database;
    @Inject UserApiService apiService;
    @Inject MutableLiveData<Throwable> errors;
    @Inject MutableLiveData<User> userById;
    private LiveData<List<User>> users;

    public LiveData<User> getUserById() {
        return userById;
    }

    public LiveData<List<User>> getUsers() {
        return users;
    }

    public LiveData<Throwable> getErrors() {
        return errors;
    }

    public UserViewModel(@NonNull Application application) {
        super(application);
        AppComponent appComponent = DaggerAppComponent.builder().appContextModule(new AppContextModule(application)).build();
        ViewModelComponent viewModelComponent = appComponent.viewModelComponent(new RoomModule(), new ApiServiceModule(), new ViewModelModule());
        viewModelComponent.inject(this);
        users = database.getUsersDao().getAllUsersFromDB();
    }

    public void loadUserById(int id) {
        Observable<Integer> observable = Observable.just(id);
        Disposable disposableLoadById = observable
                .subscribeOn(Schedulers.single())
                .flatMap(integer -> Observable.just(database.getUsersDao().getUserById(integer)))
                .subscribe(user ->{
                    userById.postValue(user);
                }, throwable -> errors.postValue(throwable));
        compositeDisposable.add(disposableLoadById);
    }

    public void loadUsers() {
        Disposable disposableLoad = apiService.getAllUsers()
                .subscribeOn(Schedulers.io())
                .subscribe(userResponse ->
                        database.getUsersDao().insertUsers(userResponse.getData())
                , throwable -> errors.postValue(throwable));
        compositeDisposable.add(disposableLoad);
    }

    public void clearErrors() {
        errors.setValue(null);
    }

    @Override
    protected void onCleared() {
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
        }
        super.onCleared();
    }
}
