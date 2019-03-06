package com.k14b.hieptran.Main;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.k14b.hieptran.Database.Note.DatabaseNoteConnect;

import com.k14b.hieptran.Database.Note.Notes;
import com.k14b.hieptran.Database.login.DatabaseLoginConnect;
import com.k14b.hieptran.Database.login.Login;
import com.k14b.hieptran.LoginActivity;
import com.k14b.hieptran.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView lvNote;
    private Context context;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ArrayList<Notes> arrayNote;
    private DatabaseNoteConnect databaseNote;
    private DatabaseLoginConnect databaseLogin;
    private Login userData;
    private AdapterListNote adapterListNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        mapping();
        loadDataUser();

        arrayNote = databaseNote.getDataNoteByAccountId(userData.getIdAccount());
        adapterListNote = new AdapterListNote(context, R.layout.adapter_list_note, arrayNote);
        lvNote.setAdapter(adapterListNote);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                arrayNote.clear();
                arrayNote = databaseNote.getDataNoteByAccountId(userData.getIdAccount());

                adapterListNote = new AdapterListNote(context, R.layout.adapter_list_note, arrayNote);
                lvNote.setAdapter(adapterListNote);

                swipeRefreshLayout.setRefreshing(false);
            }
        });

        lvNote.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(context, arrayNote.get(position).getTilte(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, EditNodeActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("title", arrayNote.get(position).getTilte());
                bundle.putString("content", arrayNote.get(position).getContent());
                bundle.putInt("id", arrayNote.get(position).getId());

                startActivity(intent);

            }
        });

        lvNote.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
//                Toast.makeText(context, "long " + arrayNote.get(position).getTilte(), Toast.LENGTH_SHORT).show();

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn có muốn xóa ghi chú " + arrayNote.get(position).getTilte() + " không ?");

                builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // code here
                        databaseNote.deleteNote(arrayNote.get(position).getId());
                        Toast.makeText(context, "xoa thanh cong " + arrayNote.get(position).getTilte(), Toast.LENGTH_SHORT).show();
                        arrayNote.remove(position);
                        adapterListNote.notifyDataSetChanged();
                    }
                });

                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();

                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    /*
     * load data pass from Login or splash screen
     *
     * @return object Login
     * */
    private void loadDataUser() {
        userData = new Login(
                getIntent().getIntExtra("id", -1),
                getIntent().getIntExtra("idAccount", -1),
                getIntent().getStringExtra("email"),
                getIntent().getStringExtra("name")
        );
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
            intent.putExtra("idAccount", userData.getIdAccount());
            context.startActivity(intent);
        }
        if (item.getItemId() == R.id.menuLogout) {
            databaseLogin.logout(userData.getIdAccount());
            Intent intent = new Intent(context, LoginActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void mapping() {
        arrayNote = new ArrayList<>();
        lvNote = findViewById(R.id.lvNote);
        swipeRefreshLayout = findViewById(R.id.swipeLayout);
        databaseNote = new DatabaseNoteConnect(context);
        databaseLogin = new DatabaseLoginConnect(context);

    }
}
