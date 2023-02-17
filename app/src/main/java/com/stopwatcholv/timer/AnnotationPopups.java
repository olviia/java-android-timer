package com.stopwatcholv.timer;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.stopwatcholv.timer.adapters.ItemSavedTimeAdapter;

public class AnnotationPopups {

    public static void annotationChangeText(View view, RecyclerView.ViewHolder holder){
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setTitle("Add annotation");
        final EditText input = new EditText(view.getContext());
        input.setPadding(30, 0,0,30);
        input.setTextSize(24);
        builder.setView(input);

        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String annotation = input.getText().toString();
                SavedTimeAnnotation.setAnnotation(holder.getAdapterPosition(), annotation);
                changeAnnotationButton(view, annotation);

            }
        });
        builder.setNegativeButton("Discard", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();
    }
    public static void annotationShowText(View view, RecyclerView.ViewHolder holder){

        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        TextView info = new TextView(view.getContext());
        info.setPadding(30, 0,0,30);
        info.setTextSize(24);
        info.setTextColor(view.getResources().getColor(R.color.grey, null));
        info.setText(SavedTimeAnnotation.getAnnotation(holder.getAdapterPosition()));
        Log.d("olvinfo", info.getText().toString());
        builder.setView(info);

        builder.setPositiveButton("Change", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                annotationChangeText(view, holder);
            }
        });
        builder.setNegativeButton("Back", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.setNeutralButton("Clear", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                SavedTimeAnnotation.setAnnotation(holder.getAdapterPosition(), "");
                changeAnnotationButton(view, "");
            }
        });
        builder.show();
    }

    private static void changeAnnotationButton(View view, String text){

        if((text.replaceAll(" ", "").equals("") )){//дописати зміну кольору
            ((ImageButton)view).setColorFilter(((ImageButton)view).getResources().getColor(R.color.grey, null));
        }else{
            ((ImageButton)view).setColorFilter(((ImageButton)view).getResources().getColor(R.color.green, null));
        }
    }

}
