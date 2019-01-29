package com.k14.hiep.calender.Content;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.k14.hiep.calender.DatabaseHandler.DatabaseConnect;
import com.k14.hiep.calender.R;

public class MainActivity extends AppCompatActivity {
    private Context context;
    private Button btnLogout;
    private DatabaseConnect model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        mapping();
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                model.logout();
            }
        });
    }

    private void mapping() {
        btnLogout = findViewById(R.id.btnLogout);
        model = new DatabaseConnect(context);
    }
}
