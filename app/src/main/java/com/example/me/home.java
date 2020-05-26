package com.example.me;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class home extends Fragment implements ItemAdapter.Callback {

    private View rootLayout;
    private FloatingActionButton add;
    private CardView card;
    TextView everydayEvent,event_name,date,time;

    ReminderHandler obj;

    DBhelper dBhelper;
    ItemAdapter mAdapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootLayout = inflater.inflate(R.layout.fragment_home, container, false);
        add = rootLayout.findViewById(R.id.FloatingBTN);


        card = rootLayout.findViewById(R.id.CardViewHome);

        card.setCardBackgroundColor(Color.parseColor("#E6E6E6"));
        card.setMaxCardElevation(0);
        card.setRadius(5);

        event_name.setText(obj.getEvent_name());
        date.setText(obj.getDate());
        time.setText(obj.getTime());
        everydayEvent.setText(obj.getEverydayEvent());

        /* ALERT DIALOG BOX - The origins
        dBhelper = new DBhelper(getActivity());*/
        addListener();

        setDataToTV();

        return rootLayout;
    }

    private void setDataToTV() {
        everydayEvent = rootLayout.findViewById(R.id.Radio_CardView);
        event_name = rootLayout.findViewById(R.id.EventName_CardView);
        date = rootLayout.findViewById(R.id.Date_CardView);
        time = rootLayout.findViewById(R.id.Time_CardView);
    }

    private void addListener() {
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("FAB", "onClick: clicked!");

                /* ALERT DIALOG BOX - The origins
                createDialogBox();*/

                //New Code for Reminder
                Intent intent = new Intent(rootLayout.getContext(),ReminderHandler.class);
                startActivity(intent);

                //Toast.makeText(rootLayout.getContext(),"FAB clicked", Toast.LENGTH_LONG).show();

                Log.i("FAB", "end!");
            }

        });
    }

    /* ALERT DIALOG BOX - The origins
        private void createDialogBox() {
        final EditText taskEditText = new EditText(rootLayout.getContext());
        AlertDialog.Builder dialog = new AlertDialog.Builder(rootLayout.getContext());
        dialog.setTitle("Add New Task")
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
                .show();


    }*/

    private void loadTaskList() {
        ArrayList<String> taskList = dBhelper.getTaskList();
        if(mAdapter == null) {
            mAdapter = new ItemAdapter(getActivity(), taskList, this);
            //list.setAdapter(mAdapter);
        }
        else {
            mAdapter.clear();
            mAdapter.addAll(taskList);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void deleteElement(final String task) {

        dBhelper.deleteTask(task);
        loadTaskList();
    }
}

