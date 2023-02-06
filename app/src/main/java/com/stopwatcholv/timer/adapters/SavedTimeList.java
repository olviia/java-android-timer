package com.stopwatcholv.timer.adapters;

import java.util.ArrayList;

public class SavedTimeList {
    int number;
    String numberToString;
    String timeSaved;
    public static ArrayList<String> savedTimes = new ArrayList<>();

    public static void saveTimeToList(String time){
        savedTimes.add(time);
    }
    public static void removeTimeFromList(int position){
        savedTimes.remove(position);
    }
    public static String getTimeFromList(int position){
        return savedTimes.get(position);
    }

}
