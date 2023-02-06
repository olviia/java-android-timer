package com.stopwatcholv.timer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.stopwatcholv.timer.adapters.ItemSavedTimeAdapter;
import com.stopwatcholv.timer.adapters.SavedTimeList;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private TextView timeText;
    private TextView fakeTimeText;
    private ImageButton startButton;
    private ImageButton restartButton;
    private ImageButton addButton;
    public static Handler handler = new Handler();
    public static Runnable myRunnable ;
    private  RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;


    //Default handler works with 100 ms delay correctly, but 1ms or even 10 ms - it's too fast and handler
    //seen cannot go so fast. that's why I've created fake handler to imitate the last digit:(
    //all the numbers are selected randomly
    public static Handler fakeHandler = new Handler();
    public static Runnable fakeRunnable ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timeText = findViewById(R.id.text_time);
        fakeTimeText = findViewById(R.id.text_fake_millisecond);
        startButton = findViewById(R.id.btn_start);
        startButton.setImageResource(R.drawable.start_icon);
        restartButton = findViewById(R.id.btn_restart);
        restartButton.setVisibility(View.GONE);
        addButton = findViewById(R.id.btn_add);
        addButton.setVisibility(View.GONE);

        SavedTimeList.savedTimes.add("00:00:00:0");

        myRunnable = new Runnable() {
            @Override
            public void run() {
                TimerButtonsAction.timeTextUpdate(timeText, fakeTimeText);
                handler.postDelayed(this,100);
            }};
        fakeRunnable = new Runnable() {
            @Override
            public void run() {
                TimerButtonsAction.fakeTimeUpdate(fakeTimeText);
                fakeHandler.postDelayed(this,5);
            }};

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimerButtonsAction.btnStartClick(startButton, restartButton, addButton);
            }
        });

        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimerButtonsAction.btnRestartClick(restartButton, timeText);
            }
        });
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddTimeButtonAction.btnAddClick();
            }
        });

        recyclerView = findViewById(R.id.recycler_saved_time);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new ItemSavedTimeAdapter(SavedTimeList.savedTimes, MainActivity.this);
        recyclerView.setAdapter(mAdapter);

    }



}