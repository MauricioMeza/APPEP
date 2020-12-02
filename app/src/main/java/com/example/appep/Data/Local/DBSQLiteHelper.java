package com.example.appep.Data.Local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBSQLiteHelper extends SQLiteOpenHelper {

    public DBSQLiteHelper(Context context, SQLiteDatabase.CursorFactory factory) {
        super(context, DBUtilities.DB_NOMBRE, factory, DBUtilities.DB_VERSION);
    }

    //Create new Database from SQL code
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DBUtilities.TABLE_CREATE_POZO_SQL);
        db.execSQL(DBUtilities.TABLE_CREATE_EVENTO_SQL);

    }

    //Delete current database from SQL Code and Execute onCreateDBUtilities
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DBUtilities.TABLE_DELETE_SQL);
        onCreate(db);
    }
}
