package com.example.bsfragments;

import android.content.Intent;
import android.os.Parcel;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AdditionalUserInfo;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener,FirebaseAuth.AuthStateListener {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private EditText ETemail;
    private EditText ETpassword;

    private Button mButtonLogin;
    private Button mButtonRegistration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        initializeViews();
        initializeFirebase();
        initializeListeners();
    }

    public void initializeViews(){
        ETemail = (EditText) findViewById(R.id.et_email);
        ETpassword = (EditText) findViewById(R.id.et_password);
        mButtonLogin =  (Button)findViewById(R.id.btn_sign_in);
        mButtonRegistration = (Button)findViewById(R.id.btn_registration);
    }

    public void initializeListeners(){
        mAuth.addAuthStateListener(this);
        mButtonLogin.setOnClickListener(this);
        mButtonRegistration.setOnClickListener(this);
    }

    public void initializeFirebase(){
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_sign_in:

                signin(ETemail.getText().toString(),
                        ETpassword.getText().toString());

                break;
            case R.id.btn_registration:

                registration(ETemail.getText().toString(),
                        ETpassword.getText().toString());

                break;
        }

    }
    public void signin(String email , String password) {
        mAuth.signInWithEmailAndPassword(email,password);
    }

    public void registration (String email , String password){
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(LoginActivity.this, "Регистрация успешна, нажмите кнопку - авторизация", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(LoginActivity.this, "Регистрация провалена, попробуйте еще раз", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        Log.d(LoginActivity.class.getSimpleName(), "CALLED!");
        if (user != null) {
            Toast.makeText(LoginActivity.this, "Aвторизация успешна", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(LoginActivity.this, "Необходимо авторизоваться", Toast.LENGTH_SHORT).show();
        }
    }
}