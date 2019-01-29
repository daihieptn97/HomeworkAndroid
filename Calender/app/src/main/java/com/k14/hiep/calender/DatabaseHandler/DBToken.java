package com.k14.hiep.calender.DatabaseHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DBToken extends SQLiteOpenHelper {

    private Context context;

    public DBToken(Context context) {
        super(context, "db.calender", null, 1);
        this.context = context;
    }

    public boolean logout(String token) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("tokens", "token = ?", new String[]{token});
        db.close();
        return true;
    }

    public boolean insertToken(String token) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("token", token);
        long isSucces = db.insert("tokens", null, contentValues);
        db.close();

        return isSucces != 1;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String codeCreateTableToken = "CREATE TABLE IF NOT EXISTS tokens (id integer primary key autoincrement, token text )";
        db.execSQL(codeCreateTableToken);
        Toast.makeText(context, "CREATE TABLE IF NOT EXISTS tokens", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
