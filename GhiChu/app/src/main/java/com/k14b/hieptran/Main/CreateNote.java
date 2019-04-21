package com.k14b.hieptran.Main;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.k14b.hieptran.Database.Note.DatabaseNoteConnect;

import com.k14b.hieptran.Database.Note.Notes;
import com.k14b.hieptran.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class CreateNote extends AppCompatActivity implements View.OnClickListener {
    private Context context;
    private EditText edtTitle, edtContent;
    private ImageView btnBack, btnSave;
    private int idAccount;
    private DatabaseNoteConnect databaseNote;
    private LinearLayout toolbarCreateNote;
    private LinearLayout linearLayout;
    private int colorbackground = 0;
    private int[] arrColor = {R.color.bgBlue, R.color.bgCyanNoteItem, R.color.bgDeepOrange, R.color.bgDeepPurple, R.color.bgGreen, R.color.bgIndigo, R.color.bgLightGreen,
            R.color.bgRed, R.color.bgTeal, R.color.bgPurple};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);
        setTitle("Tạo ghi chú");
        context = this;
        getSupportActionBar().hide();
        mapping();

        Random random = new Random();
        int indexBgColor = random.nextInt(10);

        colorbackground = arrColor[indexBgColor];

        Window window = this.getWindow();
        toolbarCreateNote.setBackgroundColor(getResources().getColor(colorbackground));
        linearLayout.setBackgroundColor(getResources().getColor(colorbackground));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            window.setStatusBarColor(getResources().getColor(colorbackground));
        }
    }

    private void mapping() {
        edtTitle = findViewById(R.id.createNoteEdtTitle);
        edtContent = findViewById(R.id.createNoteEdtContent);
        btnBack = findViewById(R.id.menuCreateNoteImgBack);
        btnSave = findViewById(R.id.menuCreateNoteImgSave);
        toolbarCreateNote = findViewById(R.id.toolbarCreateNote);
        linearLayout = findViewById(R.id.layoutEditNote);


        idAccount = getIntent().getIntExtra("idAccount", -1);

        btnSave.setOnClickListener(this);
        btnBack.setOnClickListener(this);

        databaseNote = new DatabaseNoteConnect(context);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.menuCreateNoteImgBack: {
//                Toast.makeText(context, "back", Toast.LENGTH_SHORT).show();
//                super.onBackPressed();
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("idAccount", getIntent().getIntExtra("idAccount", -1));
                intent.putExtra("id", getIntent().getIntExtra("id", -1));
                startActivity(intent);

                finish();
                break;
            }
            case R.id.menuCreateNoteImgSave: {
                if (isEditexEmpty(edtTitle.getText().toString().trim(), edtContent.getText().toString().trim())) {
                    saveNote();
//                    super.onBackPressed();
                    Intent intent = new Intent(context, MainActivity.class);
                   // Log.d("debug1200", String.valueOf(getIntent().getIntExtra("idAccount", -1)));
                    intent.putExtra("idAccount", getIntent().getIntExtra("idAccount", -1));
                    intent.putExtra("id", getIntent().getIntExtra("id", -1));
                    startActivity(intent);
                    finish();
                }

                break;
            }
        }
    }

    private boolean isEditexEmpty(String title, String content) {
        if (title.length() <= 0) {
            edtTitle.setError(getString(R.string.require_edittext));
            return false;
        }

        if (content.length() <= 0) {
            edtContent.setError(getString(R.string.require_edittext));
            return false;
        }

        return true;
    }

    private void saveNote() {
        Notes note = new Notes();
        note.setIdAccount(idAccount);
        note.setId(-1);
        note.setTilte(edtTitle.getText().toString().trim());
        note.setContent(edtContent.getText().toString().trim());
        note.setColor(colorbackground);
        Date currentTime = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
        String date = dateFormat.format(currentTime);
        note.setTimeCreate(date);

        databaseNote.createNote(note);
        Toast.makeText(context, "Them bai viết thanh cong", Toast.LENGTH_SHORT).show();
    }
}
