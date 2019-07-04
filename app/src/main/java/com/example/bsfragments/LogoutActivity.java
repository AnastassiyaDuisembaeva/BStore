package com.example.bsfragments;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class LogoutActivity extends AppCompatActivity implements View.OnClickListener, FirebaseAuth.AuthStateListener {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private Button mButtonYes;
    private Button mButtonNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);

        initializeViews();
        initializeFirebase();
        initializeListeners();
    }
    public void initializeViews(){
        mButtonYes =  (Button)findViewById(R.id.btn_yes);
        mButtonNo = (Button)findViewById(R.id.btn_no);
    }
    public void initializeListeners(){
        mAuth.addAuthStateListener(this);
        mButtonYes.setOnClickListener(this);
        mButtonNo.setOnClickListener(this);
    }
    public void initializeFirebase(){
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_yes:
                Toast.makeText(LogoutActivity.this, "Вы выбрали выход", Toast.LENGTH_SHORT).show();
                mAuth.signOut();
                break;
            case R.id.btn_no:
                Toast.makeText(LogoutActivity.this, "Вы отменили выход", Toast.LENGTH_SHORT).show();
                this.finish();
                break;
        }
    }

    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

    }
}
