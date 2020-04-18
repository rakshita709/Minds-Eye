package com.example.me;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigation = findViewById(R.id.bottom_navigation);
        
        loadFragment(new home());
        
        BottomNavigationView.OnNavigationItemSelectedListener navigation =
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        switch (item.getItemId())
                        {
                            case R.id.navigation_home:
                                selectedFragment = new home();
                                return true;
                            case R.id.navigation_contacts:
                                selectedFragment = new contacts();
                                return true;

                            case R.id.navigation_camera:
                                selectedFragment = new camera();
                                return true;

                            case R.id.navigation_calls:
                                selectedFragment = new calls();
                                return true;

                            case R.id.navigation_me_or_personal:
                                selectedFragment = new me_personal();
                                return true;
                        }
                        return false;
                    }
                };

        bottomNavigation.setOnNavigationItemSelectedListener(navigation);
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
