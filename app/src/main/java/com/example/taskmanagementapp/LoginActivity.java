package com.example.taskmanagementapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    private EditText email;
    private EditText password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        auth = FirebaseAuth.getInstance();
        // Redirect user to task page if they have already logged in
        if(auth.getCurrentUser() != null) {
            openMainActivity();
            finish();
        }

        setContentView(R.layout.activity_login);

        Button login = (Button) findViewById(R.id.loginButton);
        Button register = (Button) findViewById(R.id.registerSignUpBtn);
        email = (EditText) findViewById(R.id.editTextTextEmailAddress);
        password = (EditText) findViewById(R.id.editTextTextPassword);
        
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isEmailFilled() && isPasswordFilled()) {
                    login();
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRegisterActivity();
            }
        });

    }

    private void login() {
        auth.signInWithEmailAndPassword(email.getText().toString(),password.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            String uid = auth.getCurrentUser().getUid();
                            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                            intent.putExtra("uid", uid);
                            startActivity(intent);
                            finish();
                        }
                        else {
                            Toast.makeText(LoginActivity.this,"Email or password are incorrect", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private boolean isPasswordFilled() {
        if(email.getText().toString().isEmpty()) {
            email.setError("Please enter your email");
            return false;
        }
        return true;
    }

    private boolean isEmailFilled() {
        if(password.getText().toString().isEmpty()) {
            password.setError("Please enter your password");
            return false;
        }
        return true;
    }

    private void openMainActivity() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    private void openRegisterActivity() {
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }
}
