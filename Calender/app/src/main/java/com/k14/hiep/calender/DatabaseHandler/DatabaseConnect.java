package com.k14.hiep.calender.DatabaseHandler;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.k14.hiep.calender.Account;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;

public class DatabaseConnect extends SQLiteOpenHelper {

    private String TABLE_NAME = "account";
    private Context context;
    private DBToken token;


    public DatabaseConnect(Context context) {
        super(context, "db.calender", null, 1);
        this.context = context;
        token = new DBToken(context);
//        Toast.makeText(context, "ok", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String codeCreateTableAccount = "CREATE TABLE IF NOT EXISTS account (id integer primary key autoincrement, email text, password text, name text )";
        db.execSQL(codeCreateTableAccount);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //    add
    public boolean createAccount(Account account) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("email", account.getEmail());
        values.put("password", account.getPassword());
        values.put("name", account.getName());
        long isSucces = db.insert(TABLE_NAME, null, values);

        if (isSucces != -1 && token.insertToken(md5(Calendar.getInstance().getTime().toString()))) {

            Toast.makeText(context, "craete account succes", Toast.LENGTH_SHORT).show();
        }

//        db.close();

        return true;
    }


    private String md5(final String s) { // Create MD5
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest
                    .getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }


}
