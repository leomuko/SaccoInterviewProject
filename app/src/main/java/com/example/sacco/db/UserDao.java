package com.example.sacco.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.sacco.models.User;

@Dao
public interface UserDao {
    @Insert()
    void saveUser(User user);

    @Query("SELECT * From user_table WHERE userId = :id")
    LiveData<User> fetchUser(String id);
}
