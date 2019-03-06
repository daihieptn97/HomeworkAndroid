package com.k14b.hieptran.Database.Note;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseNoteConnect extends SQLiteOpenHelper {
    private Context context;
    private String TABLE_NAME = "note";
    /*
     * database note
     *
     * @return null
     */

    public DatabaseNoteConnect(Context context) {
        super(context, "db.note", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String codeCreateTableNote = "CREATE TABLE IF NOT EXISTS note (id integer primary key autoincrement, idaccount int, tilte text, content text, timeCreate text )";
        db.execSQL(codeCreateTableNote);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public int updateNode(Notes notes) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("idaccount", notes.getIdAccount());
        values.put("tilte", notes.getTilte());
        values.put("content", notes.getContent());

        String[] arrayWhere = {String.valueOf(notes.getId())};

        return db.update(TABLE_NAME, values, "id=?", arrayWhere);
    }

    /**
     * create account
     *
     * @return true if create success else if not success
     */
    public boolean createNote(Notes note) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("idaccount", note.getIdAccount());
        values.put("tilte", note.getTilte());
        values.put("content", note.getContent());
        values.put("timeCreate", note.getTimeCreate());

        long isSuccess = db.insert(TABLE_NAME, null, values);

        if (isSuccess != -1) {
//            Toast.makeText(context, "craete account success", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    public void deleteNote(int idNote) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + TABLE_NAME + " where id = " + idNote);
    }

//    public Note(int id, int idAccount, String tilte, String content, String timeCreate)

    public ArrayList<Notes> getDataNoteByAccountId(int accountId) {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Notes> noteArrayList = new ArrayList<>();

        String sql = "select * from " + TABLE_NAME + " where idaccount = " + accountId;
        Cursor cursor = db.rawQuery(sql, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Notes note = new Notes(
                    cursor.getInt(0),
                    cursor.getInt(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4)
            );
            noteArrayList.add(note);
            cursor.moveToNext();
        }
        return noteArrayList;
    }
}
