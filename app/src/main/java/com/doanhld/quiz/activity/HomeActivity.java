package com.doanhld.quiz.activity;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.CardView;
import android.view.View;

import com.doanhld.quiz.NService;
import com.doanhld.quiz.R;
import com.doanhld.quiz.adapter.CategoryAdapter;

import java.util.Calendar;

public class HomeActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        CardView play = findViewById(R.id.cardview1);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent playIntent = new Intent(HomeActivity.this, CategoryActivity.class);
                startActivity(playIntent);
            }
        });
//        NService ns = new NService();
        Intent intent = new Intent(HomeActivity.this, NService.class);
        PendingIntent pintent = PendingIntent.getService(HomeActivity.this, 0, intent, 0);
        AlarmManager alarm = (AlarmManager)getSystemService(this.ALARM_SERVICE);
        alarm.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 6000, pintent);



    }



}
