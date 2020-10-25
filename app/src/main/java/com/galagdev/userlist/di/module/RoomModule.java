package com.galagdev.userlist.di.module;

import android.content.Context;

import androidx.room.Room;

import com.galagdev.userlist.di.scope.ViewModelScope;
import com.galagdev.userlist.model.data.UserDatabase;

import dagger.Module;
import dagger.Provides;

@Module(includes = AppContextModule.class)
public class RoomModule {

    private static final String DB_NAME = "users_db.db";

    @Provides
    @ViewModelScope
    public UserDatabase getDatabase(Context context){
        return Room.databaseBuilder(context, UserDatabase.class, DB_NAME)
                .fallbackToDestructiveMigration()
                .build();
    }

}
