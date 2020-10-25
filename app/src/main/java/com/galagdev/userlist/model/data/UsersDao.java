package com.galagdev.userlist.model.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.galagdev.userlist.model.pogo.User;

import java.util.List;

@Dao
public interface UsersDao {
    @Query("SELECT * FROM users")
    LiveData<List<User>> getAllUsersFromDB();

    @Query("SELECT * FROM users WHERE id == :userId")
    User getUserById(int userId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUsers(List<User> users);
}
