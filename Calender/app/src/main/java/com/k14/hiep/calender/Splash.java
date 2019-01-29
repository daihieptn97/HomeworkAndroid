package com.k14.hiep.calender;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

import com.k14.hiep.calender.DatabaseHandler.DatabaseConnect;

import java.util.Timer;
import java.util.TimerTask;

public class Splash extends AppCompatActivity {

    private SQLiteDatabase db;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_splash);
        context = this;

        new DatabaseConnect(context);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms
                Intent intent = new Intent(context, Login.class);
                startActivity(intent);
            }
        }, 3000);
    }


    private void initDataCalender() {

    }


}



