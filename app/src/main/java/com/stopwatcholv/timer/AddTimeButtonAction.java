package com.stopwatcholv.timer;

import android.widget.TextView;

import com.stopwatcholv.timer.adapters.SavedTimeList;

public class AddTimeButtonAction extends  MainActivity {
    private TextView trial = findViewById(R.id.text_time);
    public static void btnAddClick(){
        SavedTimeList.savedTimes.add("wddd");
    }
}
