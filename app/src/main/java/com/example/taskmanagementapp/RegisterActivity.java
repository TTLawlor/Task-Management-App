package com.example.taskmanagementapp;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class RegisterActivity extends AppCompatActivity {
    private EditText name;
    private EditText email;
    private EditText password;
    private EditText number;
    private Button register;
    private FirebaseAuth auth;
    private static final int REQUEST_PERMISSIONS = 1;
    private static final String[] permissions = {Manifest.permission.SEND_SMS, Manifest.permission.SET_ALARM};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        auth = FirebaseAuth.getInstance();

        register = (Button) findViewById(R.id.registerButton);
        name = (EditText) findViewById(R.id.editTextName);
        email = (EditText) findViewById(R.id.editTextEmail);
        password = (EditText) findViewById(R.id.editTextPassword);

        //requestPermissions();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validateName() && validateEmail() && validatePassword()) {
                    register();
                }
            }
        });
    }

    private void register() {
        auth.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            String uid = auth.getCurrentUser().getUid();
                            String formattedName = name.getText().toString();
                            formattedName = formattedName.substring(0,1).toUpperCase() + formattedName.substring(1);
                            finish();
                        }
                        else {
                            try {
                                throw task.getException();
                            }
                            catch (FirebaseAuthUserCollisionException e) {
                                Toast.makeText(RegisterActivity.this, "An account with this email already exists", Toast.LENGTH_LONG).show();
                            }
                            catch (FirebaseAuthWeakPasswordException e) {
                                Toast.makeText(RegisterActivity.this, "Your password is too weak", Toast.LENGTH_LONG).show();
                            }
                            catch (Exception e) {
                                Log.e("Error", e.getMessage());
                            }
                        }
                    }
                });
    }

    private boolean validateName() {
        if(name.getText().toString().isEmpty()) {
            name.setError("Please provide your name");
            return false;
        }
        return true;
    }

    private boolean validatePassword() {
        String pwd = password.getText().toString();
        if(pwd.isEmpty()) {
            password.setError("Please provide a password");
            return false;
        }
        else if(pwd.length() < 8) {
            password.setError("Password must be a least 8 characters");
            return false;
        }
        return true;
    }

    private boolean validateEmail() {
        String userEmail = email.getText().toString();

        if(userEmail.isEmpty()) {
            email.setError("Please provide your email");
            return false;
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()) {
            email.setError("Please enter a valid email address");
            return false;
        }
        else {
            return true;
        }
    }
}
