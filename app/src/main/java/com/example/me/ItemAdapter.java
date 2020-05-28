package com.example.me;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class ItemAdapter extends ArrayAdapter<RemindersData> {

    private Context mContext;

    private static class ViewHolder {
        TextView time;
        TextView date;
        TextView event_name;
        TextView everydayEvent;

        public ViewHolder(View convertView) {
            everydayEvent = convertView.findViewById(R.id.Radio_CardView);
            event_name = convertView.findViewById(R.id.EventName_CardView);
            date = convertView.findViewById(R.id.Date_CardView);
            time = convertView.findViewById(R.id.Time_CardView);
        }
    }

    public ItemAdapter(@NonNull Context context, ArrayList<RemindersData> objects) {
        super(context, R.layout.row, objects);
        mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final RemindersData data = getItem(position);
        ViewHolder viewHolder;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row, parent, false);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.event_name.setText(data.getTitle());
        viewHolder.date.setText(data.getDate());
        viewHolder.everydayEvent.setText(data.getEveryday());
        viewHolder.time.setText(data.getTime());

        return convertView;
    }
}
