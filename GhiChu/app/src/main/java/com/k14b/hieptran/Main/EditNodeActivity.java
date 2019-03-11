package com.k14b.hieptran.Main;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.Toolbar;

import com.k14b.hieptran.Database.Note.DatabaseNoteConnect;
import com.k14b.hieptran.Database.Note.Notes;
import com.k14b.hieptran.R;

public class EditNodeActivity extends AppCompatActivity {
    private Context context;
    private EditText edtTitle, edtContent;
    private ImageView btnBack, btnSave;
    private int idAccount;
    private DatabaseNoteConnect databaseNote;
    private Notes note;
    private LinearLayout toolbarCreateNote;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        getSupportActionBar().hide();
        context = this;
        mappingView();
        note = databaseNote.getNoteById(getIntent().getIntExtra("id", -1));

        Window window = this.getWindow();

        toolbarCreateNote.setBackgroundColor(getResources().getColor(note.getColor()));
        linearLayout.setBackgroundColor(getResources().getColor(note.getColor()));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            window.setStatusBarColor(getResources().getColor(note.getColor()));
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (isChangeContent(edtContent.getText().toString().trim(), edtTitle.getText().toString().trim(), note)) {
                    Notes noteTemp = note;

                    noteTemp.setTilte(edtTitle.getText().toString().trim());
                    noteTemp.setContent(edtContent.getText().toString().trim());

                    databaseNote.updateNode(noteTemp);
                    Toast.makeText(context, "Thay đổi thành công ", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(context, MainActivity.class);
                    intent.putExtra("idAccount", getIntent().getIntExtra("idAccount", -1));
                    intent.putExtra("id", getIntent().getIntExtra("id", -1));
                    startActivity(intent);

                    finish();
                } else {
                    Toast.makeText(context, "bạn chưa thay đổi nội dung mới", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(context, MainActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putInt("idAccount", note.getIdAccount());
//                intent.putExtra("id", note.getId());
//
//                intent.putExtras(bundle);
//
//                startActivity(intent);
                finish();
            }
        });
    }

    private boolean isChangeContent(String content, String title, Notes note) {
//        boolean isChange = false;
        if (!content.equals(note.getContent())) {
//            isChange = false;
            return true;
        } else if (!title.equals(note.getTilte())) {
            return true;
        }

        return false;
    }


    private void mappingView() {
        edtTitle = findViewById(R.id.editNoteEdtTitle);
        edtContent = findViewById(R.id.editNoteEdtContent);
        btnBack = findViewById(R.id.menuEditNoteImgBack);
        btnSave = findViewById(R.id.menuEditNoteImgSave);
        toolbarCreateNote = findViewById(R.id.toolbarCreateNote);
        linearLayout = findViewById(R.id.layoutEditNote);

        databaseNote = new DatabaseNoteConnect(context);

        //set data old
        edtContent.setText(getIntent().getStringExtra("content"));
        edtTitle.setText(getIntent().getStringExtra("title"));
    }
}
