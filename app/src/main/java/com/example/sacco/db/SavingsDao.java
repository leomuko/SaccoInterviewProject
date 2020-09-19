package com.example.sacco.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.sacco.models.Savings;

import java.util.List;


@Dao
public interface SavingsDao {
    @Insert()
    void savingTransaction(Savings savings);

    @Query("SELECT * From savings_table WHERE userId = :id")
    LiveData<List<Savings>> fetchUserSavings(String id);

}
