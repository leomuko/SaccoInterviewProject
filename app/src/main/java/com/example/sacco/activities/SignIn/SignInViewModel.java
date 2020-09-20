package com.example.sacco.activities.SignIn;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.sacco.helpers.SaccoRepository;
import com.example.sacco.models.User;

public class SignInViewModel extends AndroidViewModel {

    private SaccoRepository mSaccoRepository;

    public LiveData<User> currentUser;

    public SignInViewModel(@NonNull Application application) {
        super(application);
        mSaccoRepository = new SaccoRepository(application);
    }

    public void saveTheNewUser( User user){
        mSaccoRepository.saveNewUser(user);
    }

    public void fetchNewUser(String userId){
        currentUser = mSaccoRepository.getSavedUser(userId);
    }
}
