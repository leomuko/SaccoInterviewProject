package com.example.sacco.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.example.sacco.R;
import com.example.sacco.helpers.UsersViewModel;
import com.example.sacco.models.User;
import com.google.firebase.auth.FirebaseAuth;

public class SplashActivity extends AppCompatActivity {

    private UsersViewModel mUsersViewModel;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private static final String TAG = "SplashActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initialiseViewModel();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(mAuth.getCurrentUser() != null){
                    String uid = mAuth.getCurrentUser().getUid();
                    fetchUserFromDatabase(uid);
                }else{
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    finish();
                }

            }
        }, 3000);
    }
    private void initialiseViewModel() {
        mUsersViewModel = new ViewModelProvider(SplashActivity.this).get(UsersViewModel.class);
    }
    private void fetchUserFromDatabase(String uid) {
        mUsersViewModel.fetchNewUser(uid);
        mUsersViewModel.currentUser.observe(SplashActivity.this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                Toast.makeText(SplashActivity.this, "Logged in as "+ user.getFirstName() + " " + user.getLastName(),
                        Toast.LENGTH_SHORT).show();
                startActivity(intent);
                finish();
            }
        });
    }
}