package com.example.bsfragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class accaunt extends Fragment {
    private TextView user_hello;
    private FirebaseAuth mAuth;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        user_hello = (TextView) getView().findViewById(R.id.hello_user);
        user_hello.setText("Привет тебе, " +getArguments().getString("user_login") + "!\nНиже показаны твои действия в приложении");
        return inflater.inflate(R.layout.fragment_accaunt, null);
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}