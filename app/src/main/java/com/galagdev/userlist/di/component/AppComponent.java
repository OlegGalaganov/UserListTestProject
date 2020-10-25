package com.galagdev.userlist.di.component;

import com.galagdev.userlist.activities.App;
import com.galagdev.userlist.di.module.ApiServiceModule;
import com.galagdev.userlist.di.module.AppContextModule;
import com.galagdev.userlist.di.module.AppModule;
import com.galagdev.userlist.di.module.RoomModule;
import com.galagdev.userlist.di.module.ViewModelModule;
import com.galagdev.userlist.di.scope.AppScope;
import com.galagdev.userlist.fragments.UserListFragment;

import dagger.Component;

@AppScope
@Component(modules = {AppContextModule.class, AppModule.class})
public interface AppComponent {

    ViewModelComponent viewModelComponent (RoomModule roomModule, ApiServiceModule apiServiceModule, ViewModelModule viewModelModule);

    void inject(App app);
    void inject(UserListFragment fragment);

}
