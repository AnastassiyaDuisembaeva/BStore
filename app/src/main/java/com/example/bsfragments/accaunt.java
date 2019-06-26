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

public class accaunt extends Fragment {
    private TextView user_hello;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        user_hello = (TextView) getView().findViewById(R.id.hello_user);
        return inflater.inflate(R.layout.fragment_accaunt, null);
    }

    @Override
    public void onResume() {
        user_hello.setText("Привет тебе, " +getArguments().getString("user_login") + "!\nНиже показаны твои действия в приложении");
        super.onResume();
    }
}