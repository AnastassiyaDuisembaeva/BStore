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

public class AccountFragment extends Fragment {
    private TextView user_hello;

    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_accaunt,container,false);

        initializeView();
        initializeData();

        return view;

    }

    public void initializeView(){
        user_hello = (TextView) view.findViewById(R.id.hello_user);
    }

    public void initializeData(){
        user_hello.setText("Привет тебе, " + getArguments().getString("user_login") + "!\nНиже будут размещены покупки пользователя в приложении");
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}