package com.galagdev.userlist.di.module;

import com.galagdev.userlist.adapters.UserAdapter;
import com.galagdev.userlist.di.scope.AppScope;
import com.galagdev.userlist.fragments.AppInfoFragment;
import com.galagdev.userlist.fragments.UserInfoFragment;
import com.galagdev.userlist.fragments.UserListFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    public UserAdapter provideUserAdapter() {
        return new UserAdapter();
    }

    @Provides
    @AppScope
    public UserListFragment provideUserListFragment() {
        return new UserListFragment();
    }

    @Provides
    @AppScope
    public UserInfoFragment provideUserInfoFragment() {
        return new UserInfoFragment();
    }

    @Provides
    @AppScope
    public AppInfoFragment provideAppInfoFragment() {
        return new AppInfoFragment();
    }
}
