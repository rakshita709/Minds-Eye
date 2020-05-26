package com.example.me;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigation;
    TextView Temp, Date, City, Description;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigation = findViewById(R.id.bottom_navigation);

        System.out.print("Hello");

        Temp = findViewById(R.id.Temperature_number);
        Date = findViewById(R.id.Date);
        City = findViewById(R.id.City);
        Description = findViewById(R.id.Temperature_Description);

        //loadFragment(new home());
        findWeather();

        BottomNavigationView.OnNavigationItemSelectedListener navigation =
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        switch (item.getItemId()) {
                            case R.id.navigation_home:
                                selectedFragment = new home();
                                loadFragment(selectedFragment);
                                return true;
                            case R.id.navigation_contacts:
                                selectedFragment = new contacts();
                                loadFragment(selectedFragment);
                                return true;

                            case R.id.navigation_camera:
                                selectedFragment = new Identify();
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
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void findWeather() {
        String url = "http://api.openweathermap.org/data/2.5/weather?q=delhi,In&appid=5fbef19c2ae09857e08b7f1e452f819c&units=imperial";

        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject main_object = response.getJSONObject("main");
                    JSONArray array = response.getJSONArray("Weather");
                    JSONObject object = array.getJSONObject(8);
                    String temp = String.valueOf(main_object.getDouble("temp"));
                    String description = array.getString(2);
                    String city = object.getString("name");

                    //Temp.setText(temp);
                    Description.setText(description);
                    City.setText(city);

                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE-MM-dd");
                    String formatted_date = dateFormat.format(calendar.getTime());

                    Date.setText(formatted_date);

                    double temp_int = Double.parseDouble(temp);
                    double centi = (temp_int - 32) / 1.8000;
                    centi = Math.round(centi);
                    int i = (int) centi;
                    Temp.setText(String.valueOf(i));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jor);

    }
}
