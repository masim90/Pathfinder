package com.pathfinder.sm.pathfinder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.text.SimpleDateFormat;
import android.widget.Toast;

/**
 * Created by dell2 on 07.02.2017.
 */
public class DBManager extends SQLiteOpenHelper{

    private static final int dbVersion = 1;
    private static final String dbName = "DB_Pathfinder.db";
    private static final String dbTable = "User";
    public static final String classSelectRaw = "SELECT * FROM " + DBManager.dbTable;
    private SQLiteDatabase sqlDB;
    private String[] columns = {"username", "email", "password"};
    Cursor result  = sqlDB.query(dbTable, columns, null, null, null, null, null);

    public DBManager (Context activity){
        super(activity, dbName, null, dbVersion);
        sqlDB = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqlDB) {
        String sql = "CREATE TABLE " + dbTable + "(_id INTEGER PRIMARY KEY AUTOINCREMENT," + "username VARCHAR(50) NOT NULL,"
                + "email VARCHAR(50) NOT NULL," + "password VARCHAR(50) NOT NULL";
        sqlDB.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqlDB, int oldVersion, int newVersion) {

    }

    public void onClose(){
        sqlDB.close();
    }

    public long insertRecord(){
        ContentValues cv = new ContentValues();
        cv.put("username", "test");
        cv.put("email", "test@test.com");
        cv.put("password", "test");
        long rowID = sqlDB.insert(dbTable, null, cv);

        return rowID;
    }

    public String output(){
        sqlDB = getReadableDatabase();
        Cursor result = sqlDB.rawQuery("SELECT * FROM " + dbTable, null);
        String mContent = "";

        while(result.moveToNext()){
            mContent = result.getString(1) + result.getString(2) + result.getString(3) + "\n";
        }
        return mContent;
    }
}
