package com.example.sacco.helpers;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.sacco.db.SaccoRoomDatabase;
import com.example.sacco.db.UserDao;
import com.example.sacco.models.User;

import java.util.List;

public class SaccoRepository {
    private UserDao mUserDao;
    private LiveData<User> theUser;

    public SaccoRepository(Application application){
        SaccoRoomDatabase db = SaccoRoomDatabase.getDatabase(application);
        mUserDao = db.userDao();
    }
    public void saveNewUser(User user){
        SaccoRoomDatabase.databaseWriteExecutor.execute(() ->{
            mUserDao.saveUser(user);
        });
    }
    public LiveData<User> getSavedUser(String id){
       theUser =  mUserDao.fetchUser(id);
       return theUser;
    }

}
