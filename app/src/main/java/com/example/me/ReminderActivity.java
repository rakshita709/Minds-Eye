package com.example.me;

//This class is called when FAB is clicked on home.class, its layout is reminder.xml, it had a DBHandler - DBHandler2

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TimePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ReminderActivity extends AppCompatActivity {

    Button saveButton;
    DatePicker date;
    TimePicker time;
    RadioButton everyday;
    EditText title;

    private static final int ADD_RESULT = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reminders);

        saveButton = findViewById(R.id.save_Reminder);

        date = findViewById(R.id.datePicker);
        time = findViewById(R.id.TimePicker);
        title = findViewById(R.id.Event_Name);
        everyday = findViewById(R.id.action_Yes);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd:MM:yyyy hh:mm");
                String dateString = date.getDayOfMonth() + ":" +
                        date.getMonth() + ":" +
                        date.getYear() + " " +
                        time.getHour() + ":" +
                        time.getMinute();
                Long dateLong = null;
                try {
                    dateLong = simpleDateFormat.parse(dateString).getTime();
                } catch (ParseException e) {
                    dateLong = 0L;
                    e.printStackTrace();
                }
                RemindersData obj = new RemindersData(title.getText().toString(), dateLong, everyday.isChecked());

                DBhelper dBhelper = new DBhelper(ReminderActivity.this);
                dBhelper.insertNewTask(obj);

                setResult(ADD_RESULT);
                finish();
            }
        });
    }


}
