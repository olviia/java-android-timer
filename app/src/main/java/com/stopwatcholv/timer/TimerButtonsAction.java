package com.stopwatcholv.timer;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


import java.text.DecimalFormat;
import java.text.NumberFormat;

public class TimerButtonsAction {

    private static int miliseconds = 0;
    private static int seconds = 0;
    private static int minutes = 0;
    private static int hours = 0;
    public static boolean isRunning = true;



    public static void btnStartClick(ImageButton startButton, ImageButton restartButton, ImageButton addButton){
            if(isRunning){
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
            }
        }

    public static void btnRestartClick(ImageButton restartButton, TextView timeText) {
        timerToZero();
        isRunning = true;
        restartButton.setVisibility(View.GONE);
        timeText.setText(getCurrentTime());
    }

    private static void stopTimer(){
        MainActivity.handler.removeCallbacks(MainActivity.myRunnable);
    }

    private static void startTimer() {
        MainActivity.handler.postAtFrontOfQueue(MainActivity.myRunnable);
    }


    public static void  timeTextUpdate(TextView timeText){
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
    }

    public static String getCurrentTime(){
            NumberFormat formatter = new DecimalFormat("00");
            String currentTime = formatter.format(hours) + ":" + formatter.format(minutes) + ":" +
                                    formatter.format(seconds) +":"+miliseconds;
            return currentTime;
    }
}