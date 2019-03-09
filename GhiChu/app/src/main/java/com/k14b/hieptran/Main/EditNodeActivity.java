package com.k14b.hieptran.Main;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.k14b.hieptran.Database.Note.DatabaseNoteConnect;
import com.k14b.hieptran.Database.Note.Notes;
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

//        int a = getIntent().getIntExtra("id", -1);
//        String b = getIntent().getStringExtra("title");

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Notes note = databaseNote.getNoteById(getIntent().getIntExtra("id", -1));

                if (isChangeContent(edtContent.getText().toString().trim(), edtTitle.getText().toString().trim(), note)) {
                    Notes noteTemp = note;

                    noteTemp.setTilte(edtTitle.getText().toString().trim());
                    noteTemp.setContent(edtContent.getText().toString().trim());

                    databaseNote.updateNode(noteTemp);
                    Toast.makeText(context, "Thay đổi thành cong ", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "bạn chưa tahy đổi nội dung mới", Toast.LENGTH_SHORT).show();
                }
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

        databaseNote = new DatabaseNoteConnect(context);

        //set data old
        edtContent.setText(getIntent().getStringExtra("content"));
        edtTitle.setText(getIntent().getStringExtra("title"));
    }
}
