package com.example.timer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private TextView timeText;
    private Button startButton;
    private Button restartButton;
    public static Handler handler = new Handler();
    public static Runnable myRunnable ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timeText = findViewById(R.id.text_time);
        startButton = findViewById(R.id.btn_start);
        startButton.setText("Start");
        restartButton = findViewById(R.id.btn_restart);
        restartButton.setVisibility(View.GONE);

        myRunnable = new Runnable() {
            @Override
            public void run() {
                TimerButtonsAction.timeTextUpdate(timeText);
                handler.postDelayed(this,100);
            }};

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimerButtonsAction.btnStartClick(startButton, restartButton);
            }
        });

        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimerButtonsAction.btnRestartClick(restartButton, timeText);
            }
        });
    }


}