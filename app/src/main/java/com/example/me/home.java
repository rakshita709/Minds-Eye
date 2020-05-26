package com.example.me;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class home extends Fragment implements ItemAdapter.Callback {

    private View rootLayout;
    private FloatingActionButton add;
    private ListView list;

    DBhelper dBhelper;
    ItemAdapter mAdapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootLayout = inflater.inflate(R.layout.fragment_home, container, false);
        add = rootLayout.findViewById(R.id.FloatingBTN);
        list = rootLayout.findViewById(R.id.ListViewHome);


        dBhelper = new DBhelper(getActivity());

        addListener();

        return rootLayout;
    }

    private void addListener() {
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("FAB", "onClick: clicked!");

                createDialogBox();

                Log.i("FAB", "end!");
            }

        });
    }

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


    }

    private void loadTaskList() {
        ArrayList<String> taskList = dBhelper.getTaskList();
        if(mAdapter == null) {
            mAdapter = new ItemAdapter(getActivity(), taskList, this);
            list.setAdapter(mAdapter);
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

