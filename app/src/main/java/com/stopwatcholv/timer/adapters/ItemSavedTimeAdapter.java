package com.stopwatcholv.timer.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.stopwatcholv.timer.AnnotationPopups;
import com.stopwatcholv.timer.MainActivity;
import com.stopwatcholv.timer.R;
import com.stopwatcholv.timer.SavedTimeAnnotation;
import com.stopwatcholv.timer.SavedTimeList;

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
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {


        holder.timeText.setText(savedTimes.get(position));
        ImageButton tempInfo = holder.infoButton;

        if(SavedTimeAnnotation.getAnnotation(position).equals("")){
            AnnotationPopups.changeAnnotationButton(tempInfo, "");
        }else{
            AnnotationPopups.changeAnnotationButton(tempInfo, "notempty");
        }
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    final int tempposition = holder.getAdapterPosition();
                    SavedTimeList.removeTimeFromList(tempposition);
                    SavedTimeAnnotation.removeAnnotation(tempposition);

                    AnnotationPopups.changeAnnotationButton(tempInfo, "");
                    notifyItemRemoved(tempposition);
                    SavedTimeList.updateRecyclerLayout();
                    if(SavedTimeList.savedTimes.size()==0){
                        MainActivity.deleteAllButton.setVisibility(View.GONE);
                        MainActivity.copyButton.setVisibility(View.GONE);
                    }

                } catch (ArrayIndexOutOfBoundsException e){
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        holder.infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((SavedTimeAnnotation.getAnnotation(holder.getAdapterPosition())).replaceAll(" ", "").equals("") ){
                    AnnotationPopups.annotationChangeText(view, holder);
                } else{
                    AnnotationPopups.annotationShowText(view, holder);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return savedTimes.size();
    }


    public static class myViewHolder extends RecyclerView.ViewHolder{
        public TextView timeText;
        public ImageButton deleteButton;
        public static ImageButton infoButton;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            timeText = itemView.findViewById(R.id.saved_time);
            deleteButton=itemView.findViewById(R.id.delete_btn);
            infoButton=itemView.findViewById(R.id.info_btn);
        }
    }
}
