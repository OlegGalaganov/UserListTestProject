package com.galagdev.userlist.di.module;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class AppContextModule {

    Context context;

    public AppContextModule(Context context) {
        this.context = context;
    }

    @Provides
    public Context context() {
        return context.getApplicationContext();
    }
}
