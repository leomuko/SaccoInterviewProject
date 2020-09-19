package com.example.sacco.activities.SignIn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sacco.R;
import com.example.sacco.activities.LoginActivity;
import com.example.sacco.activities.MainActivity;
import com.example.sacco.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity {


    private SignInViewModel mSignInViewModel;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private static final String TAG = "SignInActivity";
    ProgressDialog progressDialog ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait");

        EditText Email = findViewById(R.id.signIn_email_input);
        EditText Password = findViewById(R.id.siginIn_password_input);
        EditText FirstName = findViewById(R.id.first_name_input);
        EditText LastName = findViewById(R.id.last_name_input);
        Button signInButton = findViewById(R.id.create_accountButton);
        TextView loginText = findViewById(R.id.login_text);


        initialiseViewModel();
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = Email.getText().toString();
                String password = Password.getText().toString();
                String firstName = FirstName.getText().toString();
                String lastName = LastName.getText().toString();
                if(email.isEmpty()){
                    Toast.makeText(SignInActivity.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                }else if(password.isEmpty()){
                    Toast.makeText(SignInActivity.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                }else if(firstName.isEmpty()){
                    Toast.makeText(SignInActivity.this, "Please Enter FirstName", Toast.LENGTH_SHORT).show();
                }else if(lastName.isEmpty()){
                    Toast.makeText(SignInActivity.this, "Please Enter LastName", Toast.LENGTH_SHORT).show();
                }else {
                    progressDialog.show();
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(SignInActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        String uid = mAuth.getCurrentUser().getUid();
                                        User newUser = new User();
                                        newUser.setFirstName(firstName);
                                        newUser.setLastName(lastName);
                                        newUser.setRole("member");
                                        newUser.setUserId(uid);
                                        newUser.setSavings(0);
                                        saveUserToFirebase(newUser);
                                        Log.d(TAG, "createUserWithEmail:success");


                                    } else {
                                        progressDialog.dismiss();
                                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                        Toast.makeText(SignInActivity.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });
                }

            }
        });

        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignInActivity.this, LoginActivity.class));
            }
        });

    }

    private void saveUserToFirebase(User user) {
        mSignInViewModel.saveTheNewUser(user);
        mSignInViewModel.fetchNewUser(user.getUserId());
        mSignInViewModel.currentUser.observe(SignInActivity.this, new Observer<User>() {
            @Override
            public void onChanged(User user1) {
                    progressDialog.dismiss();
                    Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                    startActivity(intent);

            }
        });
    }

    private void initialiseViewModel() {
        mSignInViewModel = new ViewModelProvider(SignInActivity.this).get(SignInViewModel.class);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            startActivity(new Intent(SignInActivity.this, LoginActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}