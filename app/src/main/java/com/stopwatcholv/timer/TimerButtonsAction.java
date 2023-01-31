package com.stopwatcholv.timer;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import java.text.DecimalFormat;
import java.text.NumberFormat;

public class TimerButtonsAction {

    private static String timeValue = "00:00:00:0";
    private static int miliseconds = 0;
    private static int seconds = 0;
    private static int minutes = 0;
    private static int hours = 0;
    private static double fakeMilliseconds = 0;
    public static boolean isRunning = true;



    public static void btnStartClick(Button startButton, Button restartButton){
            if(isRunning){
                startTimer();
                startButton.setText("Pause");
                isRunning = false;
                restartButton.setVisibility(View.GONE);
            }else{
                stopTimer();
                startButton.setText("Start");
                isRunning = true;
                restartButton.setVisibility(View.VISIBLE);
            }
        }

    public static void btnRestartClick(Button restartButton, TextView timeText) {
        timerToZero();
        isRunning = true;
        restartButton.setVisibility(View.GONE);
        timeText.setText(getCurrentTime());
    }

    private static void stopTimer(){
        MainActivity.handler.removeCallbacks(MainActivity.myRunnable);
        MainActivity.fakeHandler.removeCallbacks(MainActivity.fakeRunnable);
    }

    private static void startTimer() {
        MainActivity.handler.postAtFrontOfQueue(MainActivity.myRunnable);
        MainActivity.fakeHandler.post(MainActivity.fakeRunnable);
    }


    public static void  timeTextUpdate(TextView timeText, TextView fakeTimeText){
        fakeMilliseconds = 0.02;
        if(miliseconds<9){
            miliseconds++;
        } else{
            miliseconds = 0;
            if(seconds<59){
                seconds++;
            } else{
                seconds = 0;
                if (minutes<59) {
                    minutes++;
                } else{
                    minutes = 0;
                    if(hours<24){
                        hours++;}
                    else{
                        timerToZero();
                    }
                }
            }
        }
        timeText.setText(getCurrentTime());

    }

    public static void timerToZero(){
        seconds = 0;
        hours = 0;
        minutes = 0;
        miliseconds = 0;
        fakeMilliseconds = 0;
    }

    public static String getCurrentTime(){
            NumberFormat formatter = new DecimalFormat("00");
            String currentTime = formatter.format(hours) + ":" + formatter.format(minutes) + ":" +
                                    formatter.format(seconds) +":"+miliseconds;
            return currentTime;
    }
    public static void fakeTimeUpdate(TextView fakeTime){
        NumberFormat formatter1 = new DecimalFormat("0");
        //int temp = fakeMilliseconds.intValue();
        fakeTime.setText("" +formatter1.format(fakeMilliseconds));
        Log.d("olvinfo", "miliseconds = " + miliseconds +" fakemili =  " + fakeMilliseconds +
                " temp = " + formatter1.format(fakeMilliseconds));
        fakeMilliseconds+=1.47;
    }
}