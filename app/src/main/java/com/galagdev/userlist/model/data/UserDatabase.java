package com.galagdev.userlist.model.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.galagdev.userlist.model.pogo.User;

@Database(entities = {User.class}, version = 3, exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {

    public abstract UsersDao getUsersDao();
}
