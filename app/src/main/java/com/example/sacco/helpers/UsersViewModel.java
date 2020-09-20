package com.example.sacco.helpers;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.sacco.helpers.SaccoRepository;
import com.example.sacco.models.User;

import java.util.List;

public class UsersViewModel extends AndroidViewModel {

    private SaccoRepository mSaccoRepository;

    public LiveData<User> currentUser;
    public LiveData<List<User>> mAllUsersList;

    public UsersViewModel(@NonNull Application application) {
        super(application);
        mSaccoRepository = new SaccoRepository(application);
    }

    public void saveTheNewUser( User user){
        mSaccoRepository.saveNewUser(user);
    }

    public void fetchNewUser(String userId){
        currentUser = mSaccoRepository.getSavedUser(userId);
    }

    public void fetchAllUsers(){
        mAllUsersList = mSaccoRepository.getAllUsers();
    }
}
