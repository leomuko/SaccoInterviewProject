package com.example.sacco.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.sacco.models.User;

import java.util.List;

@Dao
public interface UserDao {
    @Insert()
    void saveUser(User user);

    @Query("SELECT * From user_table WHERE userId = :id")
    LiveData<User> fetchUser(String id);

    @Query("UPDATE user_table SET savings = :amount WHERE userId = :id")
    void upDateSavings(String id, Integer amount);

    @Query("SELECT * From user_table")
    LiveData<List<User>> allUsers();
}
