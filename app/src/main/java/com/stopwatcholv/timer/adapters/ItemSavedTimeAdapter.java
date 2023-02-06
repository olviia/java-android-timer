package com.stopwatcholv.timer.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stopwatcholv.timer.R;

import java.util.ArrayList;

public class ItemSavedTimeAdapter extends  RecyclerView.Adapter<ItemSavedTimeAdapter.myViewHolder>{
    ArrayList<String> savedTimes;
    Context context;

    public ItemSavedTimeAdapter(ArrayList<String> savedTimes, Context context) {
        this.savedTimes = savedTimes;
        this.context = context;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_saved_time,parent,false);
        myViewHolder holder = new myViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        holder.numberID.setText(String.valueOf(position+1));
        holder.timeText.setText(savedTimes.get(position));
    }

    @Override
    public int getItemCount() {
        return savedTimes.size();
    }


    public static class myViewHolder extends RecyclerView.ViewHolder{
        public TextView numberID;
        public TextView timeText;
        public ImageButton deleteButton;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            numberID = itemView.findViewById(R.id.saved_time_number);
            timeText = itemView.findViewById(R.id.saved_time);
            deleteButton=itemView.findViewById(R.id.delete_btn);

        }
    }
}
