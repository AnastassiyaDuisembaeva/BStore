package com.example.bsfragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class accaunt extends Fragment {
    private TextView user_hello;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        user_hello = (TextView) getView().findViewById(R.id.hello_user);
        mAuth = FirebaseAuth.getInstance();

        return inflater.inflate(R.layout.fragment_accaunt, null);
    }

    @Override
    public void onResume() {
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    user_hello.setText("Привет тебе, " +getArguments().getString("user_login") + "!\nНиже показаны твои действия в приложении");
                } else {
                    Toast.makeText(getActivity(), "Вам необходимо пройти авторизацию", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }

            }
        };
        super.onResume();
    }
}