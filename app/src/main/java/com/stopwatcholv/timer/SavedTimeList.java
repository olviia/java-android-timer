package com.stopwatcholv.timer;

import android.view.ViewGroup;

import java.util.ArrayList;

public class SavedTimeList {
    public static ArrayList<String> savedTimes = new ArrayList<>();

    public static void saveTimeToList(String time){
        savedTimes.add(0,time);
    }
    public static void removeTimeFromList(int position) {
        savedTimes.remove(position);
    }
    public static void onAddButtonClick (String currentTime){
        MainActivity.mAdapter.notifyDataSetChanged();
        saveTimeToList(currentTime);
        updateRecyclerLayout();
    }
    public static void updateRecyclerLayout(){
        ViewGroup.LayoutParams params = MainActivity.recyclerView.getLayoutParams();
        if(savedTimes.size()>0){
            if(MainActivity.displayMetrics.heightPixels>MainActivity.displayMetrics.widthPixels){
                params.height =(int) (MainActivity.displayMetrics.heightPixels/2.5);
                MainActivity.recyclerView.setLayoutParams(params);
            } else{
                params.height =(int) (MainActivity.displayMetrics.heightPixels/1.7);
                MainActivity.recyclerView.setLayoutParams(params);
            }
        }else{
            params.height = 0;
            MainActivity.recyclerView.setLayoutParams(params);
        }
    }
    public static void clearAll(){
        savedTimes.clear();
        SavedTimeAnnotation.clearAnnotation();
        updateRecyclerLayout();
    }
    public static String textToCopy(){
        String finalText = "";
        for (int i = 0; i<savedTimes.size();i++) {
            finalText = finalText + savedTimes.get(i) + "   -   " +  SavedTimeAnnotation.getAnnotation(i) + "\n";
        }
        return finalText;
    }

}
