package com.stopwatcholv.timer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView timeText;
    private TextView fakeTimeText;
    private Button startButton;
    private Button restartButton;
    public static Handler handler = new Handler();
    public static Runnable myRunnable ;

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
        startButton.setText("Start");
        restartButton = findViewById(R.id.btn_restart);
        restartButton.setVisibility(View.GONE);

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