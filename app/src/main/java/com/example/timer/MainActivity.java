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
    private  String timeValue = "00:00:00:00";
    private  int miliseconds = 0;
    private  int seconds = 0;
    private  int minutes = 0;
    private  int hours = 0;
    private boolean isRunning = true;
    private Handler handler = new Handler();
    private Runnable myRunnable = new Runnable() {
        @Override
        public void run() {
            timeTextUpdate();
            handler.postDelayed(this,1);
        }};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timeText = findViewById(R.id.text_time);
        timeText.setText(timeValue);
        startButton = findViewById(R.id.btn_start);
        startButton.setText("Start");
        restartButton = findViewById(R.id.btn_restart);
        restartButton.setVisibility(View.INVISIBLE);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isRunning){
                    startTimer();
                    startButton.setText("Pause");
                    isRunning = false;
                    restartButton.setVisibility(View.INVISIBLE);
                }else{
                    stopTimer();
                    startButton.setText("Continue");
                    isRunning = true;
                    restartButton.setVisibility(View.VISIBLE);
                }
            }
        });

        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timeTextRestart();
                startTimer();
                isRunning = false;
                startButton.setText("Pause");
                restartButton.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void stopTimer(){
        handler.removeCallbacks(myRunnable);
    }

    public void startTimer() {
        handler.post(myRunnable);
    }


    public void timeTextUpdate(){
        if(miliseconds<99){
            miliseconds++;
        } else if(miliseconds == 99){
            if(seconds<59){
                miliseconds = 0;
                seconds++;
            } else if (seconds == 59){
                if (minutes<59) {
                    minutes++;
                    seconds = 0;
                } else if(minutes == 59){
                    if(hours<24){
                        hours++;
                        minutes = 0;}
                    else{
                        timerToZero();
                    }
                }
            }
        }
        setTimerText(hours, minutes, seconds,miliseconds);

    }
    public void timeTextRestart(){
        timerToZero();
        setTimerText(hours, minutes, seconds,miliseconds);
    }

    public void setTimerText(int hours, int minutes, int seconds, int miliseconds){
        NumberFormat formatter = new DecimalFormat("00");
        timeValue = formatter.format(hours) + ":" + formatter.format(minutes) + ":" + formatter.format(seconds) +":"+formatter.format(miliseconds);
        timeText.setText(timeValue);
    }
    public void timerToZero(){
        seconds = 0;
        hours = 0;
        minutes = 0;
        miliseconds = 0;
    }
}