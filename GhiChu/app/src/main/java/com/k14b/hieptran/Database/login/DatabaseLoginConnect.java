package com.k14b.hieptran.Database.login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.k14b.hieptran.Database.Account.Account;

public class DatabaseLoginConnect extends SQLiteOpenHelper {
    private String TABLE_NAME = "login";
    private SQLiteDatabase dbWrite;

    public DatabaseLoginConnect(Context context) {
        super(context, "db.note", null, 1);
        dbWrite = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String codeCreateTableLogin = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(id integer primary key autoincrement, idaccount integer, email text, name text )";
        db.execSQL(codeCreateTableLogin);
        String codeCreateTableAccount = "CREATE TABLE IF NOT EXISTS account (id integer primary key autoincrement, email text, password text, name text )";
        db.execSQL(codeCreateTableAccount);
        String codeCreateTableNote = "CREATE TABLE IF NOT EXISTS note (id integer primary key autoincrement, idaccount int, tilte text, content text, timeCreate text )";
        db.execSQL(codeCreateTableNote);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean doLogin(Account account) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("idaccount", account.getId());
        contentValues.put("email", account.getEmail());
        contentValues.put("name", account.getName());

        long res = database.insert(TABLE_NAME, null, contentValues);
        if (res != -1) {
            return true;
        }
        return false;
    }

    public void logout(int idAccount) {
        String sql = "delete  from " + TABLE_NAME + " where idaccount = " + idAccount;
        dbWrite.execSQL(sql);
    }

    public Login isLogin() {
        SQLiteDatabase database = this.getReadableDatabase();
        String sql = "select * from " + TABLE_NAME;
        Cursor cursor = database.rawQuery(sql, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            Login login = new Login(
                    cursor.getInt(0),
                    cursor.getInt(1),
                    cursor.getString(2),
                    cursor.getString(3)
            );
            return login;
        }
        return null;
    }
}
