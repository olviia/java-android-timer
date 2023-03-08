package com.stopwatcholv.timer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.stopwatcholv.timer.adapters.ItemSavedTimeAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends AppCompatActivity {
    public static TextView timeText;
    private ImageButton startButton;
    private ImageButton restartButton;
    private ImageButton addButton;
    public static ImageButton copyButton;
    public static ImageButton deleteAllButton;
    public static Handler handler = new Handler();
    public static Runnable myRunnable ;
    public static RecyclerView recyclerView;
    public static RecyclerView.Adapter mAdapter;
    private LinearLayoutManager layoutManager;

    public static DisplayMetrics displayMetrics = new DisplayMetrics();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timeText = findViewById(R.id.text_time);
        startButton = findViewById(R.id.btn_start);
        startButton.setImageResource(R.drawable.start_icon);
        restartButton = findViewById(R.id.btn_restart);
        deleteAllButton = findViewById(R.id.btn_delete_all_saved_time);
        deleteAllButton.setVisibility(View.GONE);
        restartButton.setVisibility(View.GONE);
        addButton = findViewById(R.id.btn_add);
        addButton.setVisibility(View.GONE);
        copyButton = findViewById(R.id.btn_copy);
        copyButton.setVisibility(View.GONE);

        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        myRunnable = new Runnable() {
            @Override
            public void run() {
                TimerButtonsAction.timeTextUpdate(timeText);
                handler.postDelayed(this,0);

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
                SavedTimeList.onAddButtonClick(TimerButtonsAction.getCurrentTime());
                SavedTimeAnnotation.addAnnotation("");
                deleteAllButton.setVisibility(View.VISIBLE);
                copyButton.setVisibility(View.VISIBLE);
            }
        });
        deleteAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SavedTimeList.clearAll();
                deleteAllButton.setVisibility(View.GONE);
                copyButton.setVisibility(View.GONE);
            }
        });
        copyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("copied", SavedTimeList.textToCopy());
                clipboard.setPrimaryClip(clip);
                Toast.makeText(MainActivity.this, "Copied", Toast.LENGTH_SHORT).show();
            }
        });

        recyclerView = findViewById(R.id.recycler_saved_time);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new ItemSavedTimeAdapter(SavedTimeList.savedTimes, MainActivity.this);
        recyclerView.setAdapter(mAdapter);
        recyclerView.getRecycledViewPool().setMaxRecycledViews(0,0);

    }



}