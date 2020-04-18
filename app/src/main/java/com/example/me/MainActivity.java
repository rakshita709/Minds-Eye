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
        loadFragment(new home());
        bottomNavigation = findViewById(R.id.bottom_navigation);
        
        BottomNavigationView.OnNavigationItemSelectedListener navigation =
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        switch (item.getItemId())
                        {
                            case R.id.navigation_home:
                                selectedFragment = new home();
                                loadFragment(selectedFragment);
                                return true;
                            case R.id.navigation_contacts:
                                selectedFragment = new contacts();
                                loadFragment(selectedFragment);
                                return true;

                            case R.id.navigation_camera:
                                selectedFragment = new camera();
                                loadFragment(selectedFragment);
                                return true;

                            case R.id.navigation_calls:
                                selectedFragment = new calls();
                                loadFragment(selectedFragment);
                                return true;

                            case R.id.navigation_me_or_personal:
                                selectedFragment = new me_personal();
                                loadFragment(selectedFragment);
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
