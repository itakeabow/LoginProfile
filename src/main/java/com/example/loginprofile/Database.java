package com.example.loginprofile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Database extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "messagesDatabase";
    private static final String TABLE_NAME = "messages_table";
    private static final String COL1 = "ID";
    private static final String COL2 = "MESSAGETEXT";

    public Database(Context context){
        super(context, TABLE_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + " MESSAGETEXT TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int j){
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME );
        onCreate(db);
    }

    //String message, boolean isSent or isRecieved, and a long id
    public boolean addData(String item){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, item);

        //Log.d(DATABASE_NAME, "addData: Adding " + item + " to "+ TABLE_NAME);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if(result==-1){
            return false;
        } else{ return true;}
    }

    public Cursor getListContent(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME,null);
        return cursor;
    }


    //cursor.getstring

    public void printCursor() {
        SQLiteDatabase db = this.getReadableDatabase();
        int version = db.getVersion();
        String versions = String.valueOf(version);
        //c = db.rawQuery()

        String countQuery = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();

        //HashMap<String, String> map = new HashMap<String,String>();
        Log.e("Database Version", versions);
        Log.e("Total rows ", String.valueOf(count));
        Log.e("Column name", cursor.getColumnName(1).toString());

        for (int i = 0; i < count; i++) {
            cursor.moveToFirst();
            cursor.move(i);
                Log.e("column value:", cursor.getString(cursor.getColumnIndex(COL2)));

        }

    }
}
