package com.example.sacco.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.sacco.R;
import com.example.sacco.helpers.AllUsersAdapter;
import com.example.sacco.helpers.SavingsAdapter;
import com.example.sacco.helpers.UsersViewModel;
import com.example.sacco.models.Savings;
import com.example.sacco.models.User;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class ContributionsActivity extends AppCompatActivity {
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private UsersViewModel mUsersViewModel;
    private RecyclerView mRecyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contributions);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mRecyclerView = findViewById(R.id.user_recycler);
        initialiseViewModel();

    }
    private void initialiseViewModel() {
        mUsersViewModel = new ViewModelProvider(ContributionsActivity.this).get(UsersViewModel.class);
        mUsersViewModel.fetchAllUsers();
        mUsersViewModel.mAllUsersList.observe(ContributionsActivity.this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                initialiseRecyclerView(users);
            }
        });
    }


    private void initialiseRecyclerView(List<User> users){
        mRecyclerView.setLayoutManager(new LinearLayoutManager(ContributionsActivity.this));
        AllUsersAdapter allUsersAdapter = new AllUsersAdapter(ContributionsActivity.this, users);
        mRecyclerView.setAdapter(allUsersAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            startActivity(new Intent(ContributionsActivity.this, MainActivity.class));
        }
        return super.onOptionsItemSelected(item);

    }

}