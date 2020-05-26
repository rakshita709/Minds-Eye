package com.example.me;

//This class is called when FAB is clicked on home.class, its layout is reminder.xml, it had a DBHandler - DBHandler2

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class ReminderHandler extends AppCompatActivity {

    Button saveButton = findViewById(R.id.save_Reminder);

    String everydayEvent;
    String event_name;
    String date;

    public String getEverydayEvent() {
        return everydayEvent;
    }

    public String getEvent_name() {
        return event_name;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    String time;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reminders);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RemindersData obj = new RemindersData();
                date = obj.getDate_event();
                time = obj.getTime_event();
                event_name = obj.getTitle_event();
                everydayEvent = obj.getEveryday_Answer();

            }
        });
        

    }


}
