package com.example.sacco.activities;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sacco.helpers.SaccoRepository;
import com.example.sacco.models.Savings;
import com.example.sacco.models.User;

import java.util.List;

public class SavingsViewModel extends AndroidViewModel {

    private SaccoRepository mSaccoRepository;
    public LiveData<List<Savings>> allUserSavings;
    public LiveData<User> currentUser;
    public LiveData<List<Savings>> allSavings;

    public SavingsViewModel(@NonNull Application application) {
        super(application);
        mSaccoRepository = new SaccoRepository(application);
    }

    public void depositSavings(Savings savings){
        mSaccoRepository.makeDeposit(savings);
    }

    public void fetchAllUserSavings(String id){
        allUserSavings = mSaccoRepository.getUserSavings(id);
    }
    public void upDateUserSavings(String id, Integer amount){
        mSaccoRepository.upDateUserSavings(amount, id);
    }
    public void fetchUser(String userId){
        currentUser = mSaccoRepository.getSavedUser(userId);
    }

    public void fecthAllSavings(){
        allSavings = mSaccoRepository.getAllSavings();
    }
}
