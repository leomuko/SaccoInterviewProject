package com.example.sacco.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sacco.R;
import com.example.sacco.activities.SignIn.SignInViewModel;
import com.example.sacco.models.User;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private SignInViewModel mSignInViewModel;
    private TextView mAmountSaved;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CardView savingsCard = findViewById(R.id.savingsCardView);
        mAmountSaved = findViewById(R.id.amountSaved);

        initialiseViewModel();
        String id = mAuth.getCurrentUser().getUid();
        fetchUserFromDatabase(id);


        savingsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SavingsActivity.class));
            }
        });
    }
    private void initialiseViewModel() {
        mSignInViewModel = new ViewModelProvider(MainActivity.this).get(SignInViewModel.class);
    }
    private void fetchUserFromDatabase(String uid) {
        mSignInViewModel.fetchNewUser(uid);
        mSignInViewModel.currentUser.observe(MainActivity.this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                upDateUi(user);

            }
        });
    }

    private void upDateUi(User user) {
        mAmountSaved.setText(user.getSavings().toString());
        getSupportActionBar().setTitle("Hello there "+ user.getFirstName());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_logout:
                mAuth.signOut();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}