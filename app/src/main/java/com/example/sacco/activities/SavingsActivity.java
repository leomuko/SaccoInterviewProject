package com.example.sacco.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sacco.R;
import com.example.sacco.activities.SignIn.SignInActivity;
import com.example.sacco.models.Savings;
import com.example.sacco.models.User;
import com.google.firebase.auth.FirebaseAuth;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SavingsActivity extends AppCompatActivity {

    private SavingsViewModel mSavingsViewModel;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private String uid = mAuth.getCurrentUser().getUid();
    private TextView mSavedAmout;
    private TextView mCycles;
    private TextView mDeposits;
    private TextView mPayOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_savings);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mSavedAmout = findViewById(R.id.savedAmount);
        mCycles = findViewById(R.id.cycles);
        mDeposits = findViewById(R.id.deposits);
        mPayOut = findViewById(R.id.payout);
        Button depositButton = findViewById(R.id.depositButton);

        initialiseViewModel();
        try {
            intialiseUiViews();
        }catch (NullPointerException e){
            mSavedAmout.setText("0");
            mCycles.setText("0");
            mPayOut.setText("0");
            mDeposits.setText("0");
        }


        depositButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View dialogView = getLayoutInflater().inflate(R.layout.dialog_layout, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(SavingsActivity.this);
                builder.setMessage("Enter Amount to Save");
                builder.setView(dialogView);
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setPositiveButton("Deposit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText amount =  dialogView.findViewById(R.id.dialogInput);
                        Integer a = Integer.parseInt(amount.getText().toString());
                        saveToDatabase(a);
                    }
                });
                builder.show();
            }
        });

    }

    private void initialiseViewModel() {
        mSavingsViewModel = new ViewModelProvider(SavingsActivity.this).get(SavingsViewModel.class);
    }

    private void intialiseUiViews() {
        mSavingsViewModel.fetchUser(uid);
        mSavingsViewModel.currentUser.observe(SavingsActivity.this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                mSavedAmout.setText(String.valueOf(user.getSavings()));
                mCycles.setText("1");
                mPayOut.setText(String.valueOf(user.getSavings()));
            }
        });
        mSavingsViewModel.fetchAllUserSavings(uid);
        mSavingsViewModel.numberOfDeposits.observe(SavingsActivity.this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                mDeposits.setText(String.valueOf(integer));
            }
        });

    }



    private void saveToDatabase(Integer a) {
        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        Savings savings = new Savings();
        savings.setUserId(uid);
        savings.setAmountDeposited(a);
        savings.setCycle(1);
        savings.setDate(date);
        mSavingsViewModel.depositSavings(savings);
        mSavingsViewModel.upDateUserSavings(uid, a);

        Toast.makeText(this, "A total Amount of "+ a + " has been saved to your account", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(SavingsActivity.this, MainActivity.class));
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            startActivity(new Intent(SavingsActivity.this, MainActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}