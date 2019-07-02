package com.example.bsfragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener  {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()) {
            case R.id.navigation_news:
                fragment = new NewsFragment();
                break;

            case R.id.navigation_books:
                fragment = new BooksFragment();
                break;

            case R.id.navigation_accaunt:
                mAuth = FirebaseAuth.getInstance();

                if(mAuth.getCurrentUser() != null){
                    fragment = new AccountFragment();
                    Bundle data = new Bundle();
                    data.putString("user_login", mAuth.getCurrentUser().getEmail());
                    fragment.setArguments(data);
                   // SIGNED IN!
                }else{
                    Toast.makeText(MainActivity.this, "Вам необходимо пройти авторизацию", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
                break;

        }

        return loadFragment(fragment);
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        invalidateOptionsMenu();
//    }

    @Override
    protected void onRestart() {
        super.onRestart();
        invalidateOptionsMenu();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        loadFragment(new BooksFragment());

        BottomNavigationView navigation = findViewById(R.id.nav_view);
        navigation.setOnNavigationItemSelectedListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem itemAddNewBook = menu.findItem(R.id.action_addNewBook);
        mAuth = FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser() != null){
            String adminLogin = "admin@mail.ru";
            String userLogin = mAuth.getCurrentUser().getEmail();
            if(userLogin == adminLogin){
                itemAddNewBook.setVisible(true);
            }else{
                itemAddNewBook.setVisible(false);
            }
        }
        else{
            itemAddNewBook.setVisible(false);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_contacts) {
            Intent intent = new Intent(this, ContactsActivity.class);
            startActivity(intent);
        }
        if(item.getItemId() == R.id.action_login) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
        if(item.getItemId() == R.id.action_addNewBook) {
            Intent intent = new Intent(this, AddNewBookActivity.class);
            startActivity(intent);
        }
        return true;
    }

    public boolean loadFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container, fragment);
        ft.addToBackStack(null);
        ft.commit();
        return false;
    }

}
