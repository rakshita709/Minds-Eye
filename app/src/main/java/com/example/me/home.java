package com.example.me;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class home extends Fragment {

    private HomeCallback callback;
    private View rootLayout;
    private FloatingActionButton add;

    private DBhelper dBhelper;
    private ItemAdapter mAdapter;
    private ListView list;

    public home(HomeCallback callback) {
        this.callback = callback;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootLayout = inflater.inflate(R.layout.fragment_home, container, false);
        add = rootLayout.findViewById(R.id.FloatingBTN);
        list = rootLayout.findViewById(R.id.listView);

        addListener();
        loadTaskList();

        return rootLayout;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        dBhelper = new DBhelper(context);
    }

    private void addListener() {
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.addContent();
            }

        });
    }

    private void loadTaskList() {
        ArrayList<RemindersData> taskList = dBhelper.getTaskList();
        if(mAdapter == null) {
            mAdapter = new ItemAdapter(getActivity(), taskList);
            list.setAdapter(mAdapter);
        }
        else {
            mAdapter.clear();
            mAdapter.addAll(taskList);
            mAdapter.notifyDataSetChanged();
        }
    }

    public void refresh() {
        loadTaskList();
    }

    public interface HomeCallback {
        void addContent();
    }
}

