package com.example.me;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

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

        dBhelper = new DBhelper(getContext());

        addListener();
        return rootLayout;
    }

    private void addListener() {
        final EditText taskEditText = new EditText(getContext());
        AlertDialog dialog = new AlertDialog.Builder(getContext())
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
    }

    private void loadTaskList() {
        ArrayList<String> taskList = dBhelper.getTaskList();
        if(mAdapter == null) {
            mAdapter = new ItemAdapter(getContext(), taskList, this);
            list.setAdapter(mAdapter);
        }
        else {
            mAdapter.clear();
            mAdapter.addAll(taskList);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void deleteElement(String task) {
        dBhelper.deleteTask(task);
        loadTaskList();
    }
}

