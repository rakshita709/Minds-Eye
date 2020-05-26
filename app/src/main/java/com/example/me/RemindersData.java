package com.example.me;


import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

public class RemindersData extends AppCompatActivity {
    EditText EventTitle = findViewById(R.id.Event_Name);
    TimePicker timePicker = findViewById(R.id.TimePicker);
    DatePicker datePicker = findViewById(R.id.datePicker);

    RadioGroup radioGroup = findViewById(R.id.RadioGroup);
    RadioButton RadioBtn;

    String title_event, time_event, date_event;
    String Everyday_Answer;

    public RemindersData()
    {
        title_event = "title";
        time_event = "Event";
        date_event = "Date";
        Everyday_Answer = "No";
        Setter();
    }

    void Setter()
    {
        title_event = EventTitle.getText().toString();

        int hour = timePicker.getHour(); hour-=12;
        int min = timePicker.getMinute();

        time_event = "At " + hour + ":" + min;

        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth() + 1;
        int year = datePicker.getYear();

        date_event = "On " + day + "." + month + "." + year;

        int selectedID = radioGroup.getCheckedRadioButtonId();
        RadioBtn = findViewById(selectedID);
        if(selectedID==-1)
        {
            Everyday_Answer = "No";
        }
        else
        {
            Everyday_Answer = RadioBtn.getText().toString();
        }

    }

    String getTitle_event()
    {
        return title_event;
    }

    String getDate_event()
    {
        return date_event;
    }

    String getTime_event()
    {
        return time_event;
    }

    String getEveryday_Answer()
    {
        return Everyday_Answer;
    }


}
