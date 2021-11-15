package com.example.sharedpreferencewithcountdown;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    Long hours_in_millies=3600000L;
    Long minutes_in_millies=60000L;
    Long seconds_in_millies=1000L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.text_view);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        Long xtime = System.currentTimeMillis()-sharedPreferences.getLong("TIME",System.currentTimeMillis());

        long timer=86400000-(xtime+sharedPreferences.getLong("TIME2",0));

        final SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong("TIME2",xtime+sharedPreferences.getLong("TIME2",0));
        editor.commit();


        new CountDownTimer(timer, 1000) {

            public void onTick(long elapsed) {

                Log.d(TAG, "TIMER" + System.currentTimeMillis());
                long timer2=elapsed;
                long hours = timer2 / hours_in_millies;
                timer2 %= hours_in_millies;
                long minutes = timer2 / minutes_in_millies;
                timer2 %= minutes_in_millies;
                long seconds = timer2 / seconds_in_millies;
                textView.setText(hours + ":" + minutes + ":" + seconds);


            }

            public void onFinish()
            {
                Toast.makeText(MainActivity.this, "Geri sayim bitti", Toast.LENGTH_SHORT).show();
            }

        }
                .start();
        xtime=System.currentTimeMillis();
        SharedPreferences.Editor editor2 = sharedPreferences.edit();
        editor2.putLong("TIME",xtime);
        editor2.commit();
    }
}