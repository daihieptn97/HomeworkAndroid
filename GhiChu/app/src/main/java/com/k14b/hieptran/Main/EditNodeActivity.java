package com.k14b.hieptran.Main;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import com.k14b.hieptran.Database.Note.DatabaseNoteConnect;
import com.k14b.hieptran.R;

public class EditNodeActivity extends AppCompatActivity {
    private Context context;
    private EditText edtTitle, edtContent;
    private ImageView btnBack, btnSave;
    private int idAccount;
    private DatabaseNoteConnect databaseNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        getSupportActionBar().hide();
        context = this;
        mappingView();
    }

    private void mappingView() {
        edtTitle = findViewById(R.id.editNoteEdtTitle);
        edtContent = findViewById(R.id.editNoteEdtContent);
        btnBack = findViewById(R.id.menuEditNoteImgBack);
        btnSave = findViewById(R.id.menuEditNoteImgSave);
        
    }
}
