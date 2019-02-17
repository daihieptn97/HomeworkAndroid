package com.k14b.hieptran.Database.Account;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.k14b.hieptran.Database.MD5;

public class DatabaseAccountConnect extends SQLiteOpenHelper {

    private String TABLE_NAME = "account";
    private Context context;

    public DatabaseAccountConnect(Context context) {
        super(context, "db.note", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String codeCreateTableAccount = "CREATE TABLE IF NOT EXISTS account (id integer primary key autoincrement, email text, password text, name text )";
        db.execSQL(codeCreateTableAccount);
        String codeCreateTableNote = "CREATE TABLE IF NOT EXISTS note (id integer primary key autoincrement, idaccount int, tilte text, content text, timeCreate text )";
        db.execSQL(codeCreateTableNote);
        String codeCreateTableLogin = "CREATE TABLE IF NOT EXISTS login(id integer primary key autoincrement, idaccount integer, email text, name text )";
        db.execSQL(codeCreateTableLogin);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /**
     * create account
     *
     * @return true if create success else if not success
     */
    public boolean createAccount(Account account) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("email", account.getEmail());
        values.put("password", MD5.md5(account.getPassword()));
        values.put("name", account.getName());
        long isSucces = db.insert(TABLE_NAME, null, values);

        if (isSucces != -1) {
            Toast.makeText(context, "craete account succes", Toast.LENGTH_SHORT).show();
        }


        return true;
    }


    /**
     * @return object Account of user.
     */
    public Account login(String email, String pass) {
        SQLiteDatabase db = this.getReadableDatabase();
        Account user;
        String sql = "select * from " + TABLE_NAME + " where email = '" + email + "' AND password = '" + MD5.md5(pass) + "' ";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            user = new Account();

            user.setId(cursor.getInt(0));
            user.setEmail(cursor.getString(1));
            user.setPassword("-1");
            user.setName(cursor.getString(3));

            return user;
        }

        return null;

    }


}
