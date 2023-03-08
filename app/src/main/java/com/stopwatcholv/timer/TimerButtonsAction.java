package com.stopwatcholv.timer;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;


import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Locale;

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
        long time = System.currentTimeMillis() - startTime;
        Duration timeDuration = Duration.ofMillis(time);
        long hours = timeDuration.toHours();
        int minutes = (int)timeDuration.minusHours(hours).toMinutes();
        int seconds = (int)timeDuration.minusHours(hours).minusMinutes(minutes).getSeconds();
        int millis = (int)timeDuration.minusHours(hours).minusMinutes(minutes).minusSeconds(seconds).toMillis();

        NumberFormat numformat = new DecimalFormat("00");

         String temporaryString = numformat.format(hours) + ":"+
                                numformat.format(minutes) + ":" +
                                numformat.format(seconds) + ":" +
                                numformat.format(millis).substring(0,2);

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