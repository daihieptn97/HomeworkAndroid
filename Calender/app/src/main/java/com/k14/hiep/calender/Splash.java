package com.k14.hiep.calender;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

import java.util.Timer;
import java.util.TimerTask;

public class Splash extends AppCompatActivity {

    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_splash);
          initDataCalender();

        Intent intent = new Intent(this, Login.class);
        this.startActivity(intent);
    }

//    AsyncTask<String, Integer, String>

    private void initDataCalender() {

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms

            }
        }, 3000);

//        db = openOrCreateDatabase("db.calender", MODE_PRIVATE, null);
//        String codeCreateTableAccount = "CREATE TABLE IF NOT EXISTS account (id integer primary key autoincrement, email text, password text, name text )";
//        db.execSQL(codeCreateTableAccount);

//        return null;
    }


}



