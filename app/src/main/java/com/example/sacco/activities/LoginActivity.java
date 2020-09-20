package com.example.sacco.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sacco.R;
import com.example.sacco.helpers.UsersViewModel;
import com.example.sacco.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private static final String TAG = "LoginActivity";
    private UsersViewModel mUsersViewModel;
    ProgressDialog progressDialog ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait");

        final EditText Email = findViewById(R.id.login_email_input);
        final EditText Password = findViewById(R.id.login_password_input);
        TextView createAccount = findViewById(R.id.newAccountText);
        Button loginButton = findViewById(R.id.login_button);

        initialiseViewModel();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = Email.getText().toString();
                String password = Password.getText().toString();
                if(email.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Please enter Email", Toast.LENGTH_SHORT).show();
                }else if(password.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Please enter Password", Toast.LENGTH_SHORT).show();
                }else{
                    progressDialog.show();
                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        Log.d(TAG, "onComplete: success");
                                        String uid = mAuth.getCurrentUser().getUid();
                                        fetchUserFromDatabase(uid);
                                        startActivity(new Intent(LoginActivity.this, MainActivity.class));

                                    }else{
                                        progressDialog.dismiss();
                                        Log.d(TAG, "onComplete: "+ task.getException().getMessage());
                                        Toast.makeText(LoginActivity.this, "Login failed, please ensure right password or check your internet connection",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }

            }
        });

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignInActivity.class));
                finish();
            }
        });
    }

    private void fetchUserFromDatabase(String uid) {
        mUsersViewModel.fetchNewUser(uid);
        mUsersViewModel.currentUser.observe(LoginActivity.this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                progressDialog.dismiss();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                Log.d(TAG, "onChanged: "+ user.getFirstName());
                Toast.makeText(LoginActivity.this, "Logged in as "+ user.getFirstName() + " " + user.getLastName(),
                        Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
    }


    private void initialiseViewModel() {
        mUsersViewModel = new ViewModelProvider(LoginActivity.this).get(UsersViewModel.class);
    }
}