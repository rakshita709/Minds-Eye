package com.example.me;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Adapter_Calls extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    String[] items;
    public Adapter_Calls(Context context, String[] items){
        this.context=context;
        this.items=items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return items.length;
    }

    public class Item extends RecyclerView.ViewHolder {
        public Item(@NonNull View itemView) {
            super(itemView);
        }
    }
}
