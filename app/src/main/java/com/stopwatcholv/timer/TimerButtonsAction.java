package com.stopwatcholv.timer;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;


import java.text.SimpleDateFormat;

public class TimerButtonsAction {

    public static boolean isRunning = true;
    private static boolean onStart = true;

    public static long startTime = 0;
    private static long pauseTime = 0;



    public static void btnStartClick(ImageButton startButton, ImageButton restartButton, ImageButton addButton){
            if(isRunning){
                if(onStart){
                    startTime = System.currentTimeMillis();
                    onStart = false;
                } else{
                    startTime += (System.currentTimeMillis()-pauseTime);
                }
                startTimer();
                startButton.setImageResource(R.drawable.pause_icon);
                isRunning = false;
                addButton.setVisibility(View.VISIBLE);
                restartButton.setVisibility(View.GONE);
            }else{
                stopTimer();
                startButton.setImageResource(R.drawable.start_icon);
                isRunning = true;
                addButton.setVisibility(View.GONE);
                restartButton.setVisibility(View.VISIBLE);
                pauseTime = System.currentTimeMillis();
            }
        }

    public static void btnRestartClick(ImageButton restartButton, TextView timeText) {
        timerToZero();
        isRunning = true;
        onStart = true;
        restartButton.setVisibility(View.GONE);
        timeText.setText(getCurrentTime());
    }

    private static void stopTimer(){
        MainActivity.handler.removeCallbacks(MainActivity.myRunnable);
    }

    private static void startTimer() {
        MainActivity.handler.postDelayed(MainActivity.myRunnable, 0);
    }

    public static void  timeTextUpdate(TextView timeText){
        long time = System.currentTimeMillis() - TimerButtonsAction.startTime;
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss:SS");
        String temporaryString = sdf.format(time);
        timeText.setText(temporaryString);
    }

    public static void timerToZero(){
        MainActivity.timeText.setText("00:00:00:00");
    }

    public static String getCurrentTime(){
            String currentTime = MainActivity.timeText.getText().toString();
            return currentTime;
    }
}