package com.example.me;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class calls extends Fragment {

    private View view;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View viewInflater = inflater.inflate(R.layout.fragment_call, container, false);

        /*Button button1 = view.findViewById(R.id.Call1);
        Button button2 = view.findViewById(R.id.Call2);
        Button button3 = view.findViewById(R.id.Call3);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNum1 = "8218098475";
                String phoneNumber = "tel:" + phoneNum1;

                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse(phoneNumber));

                startActivity(intent);
            }
        });
*/

        // Inflate the layout for this fragment
        return viewInflater;
    }

    /*public void Call1(View view)
    {
        String phoneNumber="8755555444";
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse(phoneNumber));
        startActivity(callIntent);

        startActivity(callIntent);
    }

    public void Call2(View view)
    {
        String phoneNumber="8218098475";

        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse(phoneNumber));
        startActivity(callIntent);

        startActivity(callIntent);
    }

    public void Call3(View view)
    {
        String phoneNumber="9811921380";

        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse(phoneNumber));
        startActivity(callIntent);

        startActivity(callIntent);
    }
*/
}

