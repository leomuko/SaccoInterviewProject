package com.example.sacco.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.sacco.models.Savings;


import com.example.sacco.models.User;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Database(entities = {User.class, Savings.class}, version = 1, exportSchema = false)
public abstract class SaccoRoomDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract SavingsDao savingsDao();


    private static volatile SaccoRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static SaccoRoomDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (SaccoRoomDatabase.class) {
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            SaccoRoomDatabase.class,
                            "sacco_database")
                    .build();
                }
            }
        }
        return INSTANCE;
    }



}
