package com.stopwatcholv.timer;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class SavedTimeList {
    public static ArrayList<String> savedTimes = new ArrayList<>();

    public static void saveTimeToList(String time){
        savedTimes.add(time);
    }
    public static void removeTimeFromList(int position) {
        savedTimes.remove(position);
    }
    public static void onAddButtonClick (String currentTime){
        MainActivity.mAdapter.notifyDataSetChanged();
        saveTimeToList(currentTime);
        Collections.reverse(savedTimes);
        updateRecyclerLayout();
    }
    public static void updateRecyclerLayout(){
        ViewGroup.LayoutParams params = MainActivity.recyclerView.getLayoutParams();
        if(savedTimes.size()>0){
            params.height =(int) (MainActivity.displayMetrics.heightPixels/2.5);
            MainActivity.recyclerView.setLayoutParams(params);
        }else{
            params.height = 0;
            MainActivity.recyclerView.setLayoutParams(params);
        }
    }

}
