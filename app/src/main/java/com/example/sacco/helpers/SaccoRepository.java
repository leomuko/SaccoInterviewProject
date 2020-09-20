package com.example.sacco.helpers;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.sacco.db.SaccoRoomDatabase;
import com.example.sacco.db.SavingsDao;
import com.example.sacco.db.UserDao;
import com.example.sacco.models.Savings;
import com.example.sacco.models.User;

import java.util.List;

public class SaccoRepository {
    private UserDao mUserDao;
    private LiveData<User> theUser;
    private SavingsDao mSavingsDao;
    private LiveData<List<Savings>> mUserSavings;
    private LiveData<List<Savings>> mAllSavings;


    public SaccoRepository(Application application){
        SaccoRoomDatabase db = SaccoRoomDatabase.getDatabase(application);
        mUserDao = db.userDao();
        mSavingsDao = db.savingsDao();
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

    public void makeDeposit(Savings savings){
        SaccoRoomDatabase.databaseWriteExecutor.execute(() ->{
            mSavingsDao.savingTransaction(savings);
        });
    }

    public LiveData<List<Savings>> getUserSavings(String userId){
        mUserSavings = mSavingsDao.fetchUserSavings(userId);
        return  mUserSavings;
    }

    public void upDateUserSavings(Integer amount, String userId){
        SaccoRoomDatabase.databaseWriteExecutor.execute(() ->{
            mUserDao.upDateSavings(userId, amount);
        });
    }

    public  LiveData<List<Savings>> getAllSavings(){
        mAllSavings = mSavingsDao.fetchAllSavings();
        return  mAllSavings;
    }


}
