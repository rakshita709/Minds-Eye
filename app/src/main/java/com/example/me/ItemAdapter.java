package com.example.me;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends ArrayAdapter<String> {

    public interface Callback{
        void deleteElement(String task);
    }

    private Context mContext;
    private Callback mCallback;

    private static class ViewHolder {
        TextView text;
        Button delete;
    }

    public ItemAdapter(@NonNull Context context, ArrayList<String> objects, Callback callback) {
        super(context, R.layout.row, objects);
        mContext = context;
        mCallback = callback;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final String data = getItem(position);
        ViewHolder viewHolder;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row, parent, false);
            viewHolder.text = convertView.findViewById(R.id.taskTitle);
            viewHolder.delete = convertView.findViewById(R.id.BtnDelete);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.text.setText(data);
        viewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.deleteElement(data);
            }
        });

        return convertView;
    }
}
