package com.times.ussd.db;

/**
 * Created by umesh on 24/11/15.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class UssdSqliteHelper extends SQLiteOpenHelper {

    public static final String TABLE_USSD = "table_ussd";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_MESSAGE = "message";
    public static final String COLUMN_TIME_STAMP = "timestamp";
    public static final String COLUMN_BALANCE = "balance";

    private static final String DATABASE_NAME = "ussd.db";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table " + TABLE_USSD
            + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_MESSAGE + " text not null, "
            + COLUMN_TIME_STAMP + " text, "
            + COLUMN_BALANCE + " text"
            + ");";

    public UssdSqliteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(UssdSqliteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USSD);
        onCreate(db);
    }

}