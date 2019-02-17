package com.k14b.hieptran.Main;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.k14b.hieptran.Database.DatabaseNoteConnect;

import com.k14b.hieptran.Database.Notes;
import com.k14b.hieptran.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView lvNote;
    private Context context;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ArrayList<Notes> arrayNote;
    private DatabaseNoteConnect databaseNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        mapping();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
            }
        });


        arrayNote = databaseNote.getDataNoteByAccountId(1);
        AdapterListNote adapterListNote = new AdapterListNote(context, R.layout.adapter_list_note, arrayNote);
        lvNote.setAdapter(adapterListNote);

        lvNote.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(context, arrayNote.get(position).getTilte(), Toast.LENGTH_SHORT).show();

            }
        });

        lvNote.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(context, "long " + arrayNote.get(position).getTilte(), Toast.LENGTH_SHORT).show();
                return false;
            }
        });
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
        lvNote = findViewById(R.id.lvNote);
        swipeRefreshLayout = findViewById(R.id.swipeLayout);
        databaseNote = new DatabaseNoteConnect(context);

    }
}
