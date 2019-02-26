package com.k14b.hieptran;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.k14b.hieptran.Database.Account.DatabaseAccountConnect;
import com.k14b.hieptran.Database.login.DatabaseLoginConnect;
import com.k14b.hieptran.Database.login.Login;
import com.k14b.hieptran.Main.MainActivity;

public class Splash extends AppCompatActivity {
    private DatabaseLoginConnect db;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        context = this;

        new DatabaseAccountConnect(context);
        db = new DatabaseLoginConnect(context);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms
                Login dataUSer = db.isLogin();
                if (dataUSer != null) {
                    Intent intent = new Intent(context, MainActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("id", dataUSer.getId());
                    bundle.putInt("idAccount", dataUSer.getIdAccount());
                    bundle.putString("email", dataUSer.getEmail());
                    bundle.putString("name", dataUSer.getName());
                    intent.putExtras(bundle);

                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(context, LoginActivity.class);
                    startActivity(intent);
                }


            }
        }, 1000);
    }
}
