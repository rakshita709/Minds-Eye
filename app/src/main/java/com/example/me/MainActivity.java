package com.example.me;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigation;
    TextView Temp, Date, City, Description;

    DBhelper dBhelper;
    ArrayAdapter<String> mAdapter;
    ListView listTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigation = findViewById(R.id.bottom_navigation);

        Temp = findViewById(R.id.Temperature_number);
        Date = findViewById(R.id.Date);
        City = findViewById(R.id.City);
        Description = findViewById(R.id.Temperature_Description);

        dBhelper = new DBhelper(this);
        listTask = findViewById(R.id.ListViewHome);


        /*button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNum1 = "8218098475";
                String phoneNumber = "tel:" + phoneNum1;

                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse(phoneNumber));
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    //Toast.makeText(MainActivity.this,"Calls something I guess!", Toast.LENGTH_LONG).show();
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(intent);
            }
        });*/

        loadFragment(new home());


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

    private void loadTaskList() {
        ArrayList<String> taskList = dBhelper.getTaskList();
        if(mAdapter == null) {
            mAdapter = new ArrayAdapter<String>(this,R.layout.row,R.id.taskTitle,taskList);
            listTask.setAdapter(mAdapter);
        }
        else {
            mAdapter.clear();
            mAdapter.addAll(taskList);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_to_do_list,menu);

        Drawable icon = menu.getItem(0).getIcon();
        icon.mutate();
        icon.setColorFilter(getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_IN);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_task:
                final EditText taskEditText = new EditText(this);
                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setTitle("Add New Task")
                        .setMessage("What is the task?")
                        .setView(taskEditText)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String task = String.valueOf(taskEditText.getText());
                                dBhelper.insertNewTask(task);
                                loadTaskList();

                            }
                        })
                        .setNegativeButton("Cancel",null)
                        .create();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void deleteTask(View view) {
        View parent = (View) view.getParent();
        TextView taskTextViews = findViewById(R.id.taskTitle);
        String task = String.valueOf(taskTextViews.getText());
        dBhelper.deleteTask(task);
        loadTaskList();
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


    public void FloatingBTN(View view) {
        FloatingActionButton fab = findViewById(R.id.FloatingBTN);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(MainActivity.this, "FAB Clicked!", Toast.LENGTH_LONG).show();
            }
        });
    }

    /*public void Call1(View view)
    {
        String phoneNumber="8755555444";
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse(phoneNumber));

        startActivity(callIntent);
    }

    public void Call2(View view)
    {
        String phoneNumber="8218098475";

        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse(phoneNumber));

        startActivity(callIntent);
    }

    public void Call3(View view)
    {
        String phoneNumber="9811921380";

        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse(phoneNumber));

        startActivity(callIntent);
    }*/


}
