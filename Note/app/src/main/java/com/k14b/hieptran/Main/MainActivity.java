package com.k14b.hieptran.Main;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.k14b.hieptran.Database.DatabaseNoteConnect;
import com.k14b.hieptran.Database.Note;
import com.k14b.hieptran.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView lvNote;
    private Context context;
    private String[] number = {"0988222999", "0988933333", "0182882883", "0935727882", "0928873882", "01667887333"};
    private ArrayList<Note> arrayNote;
    private DatabaseNoteConnect databaseNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        mapping();


//        Note note1 = new Note(1, 1, "ngay mai", "ngay mai di choi", "12/09");
//        Note note2 = new Note(1, 1, "ngay mai", "ngay mai di choi", "12/09");
//        Note note3 = new Note(1, 1, "ngay mai", "ngay mai di choi", "12/09");
//        Note note4 = new Note(1, 1, "ngay mai", "ngay mai di choi", "12/09");
//
//
//        noteArrayList.add(note1);
//        noteArrayList.add(note2);
//        noteArrayList.add(note3);
//        noteArrayList.add(note4);

        arrayNote = databaseNote.getDataNoteByAccountId(1);
        AdapterListNote adapterListNote = new AdapterListNote(context, R.layout.adapter_list_note, arrayNote);
        lvNote.setAdapter(adapterListNote);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menuAddNode) {
            Intent intent = new Intent(context, CreateNote.class);
            context.startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void mapping() {
        arrayNote = new ArrayList<>();
        lvNote = findViewById(R.id.mainLvNote);
        databaseNote = new DatabaseNoteConnect(context);
    }
}
