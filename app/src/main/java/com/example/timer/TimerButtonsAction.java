package com.example.timer;

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
    public static boolean isRunning = true;

        public static void btnStartClick(Button startButton, Button restartButton){
                if(isRunning){
                    startTimer();
                    startButton.setText("Pause");
                    isRunning = false;
                    restartButton.setVisibility(View.INVISIBLE);
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
            restartButton.setVisibility(View.INVISIBLE);
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
        } else if(miliseconds == 9){
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