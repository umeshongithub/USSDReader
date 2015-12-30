package com.times.ussd.db;

/**
 * Created by umesh on 24/11/15.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.times.ussd.dto.Ussd;

import java.util.ArrayList;
import java.util.List;

public class UssdDataSource {

    // Database fields
    private SQLiteDatabase database;
    private UssdSqliteHelper dbHelper;
    private String[] allColumns = {UssdSqliteHelper.COLUMN_ID, UssdSqliteHelper.COLUMN_MESSAGE,
            UssdSqliteHelper.COLUMN_TIME_STAMP, UssdSqliteHelper.COLUMN_BALANCE};

    public UssdDataSource(Context context) {
        dbHelper = new UssdSqliteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Ussd createUssd(Ussd ussd) {
        ContentValues values = new ContentValues();
        values.put(UssdSqliteHelper.COLUMN_MESSAGE, ussd.getMessage());
        values.put(UssdSqliteHelper.COLUMN_TIME_STAMP, ussd.getTimeStamp());
        values.put(UssdSqliteHelper.COLUMN_BALANCE, ussd.getBalance());
        long insertId = database.insert(UssdSqliteHelper.TABLE_USSD, null,
                values);
        Cursor cursor = database.query(UssdSqliteHelper.TABLE_USSD,
                allColumns, UssdSqliteHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Ussd newUssd = cursorToUssd(cursor);
        cursor.close();
        return newUssd;
    }

    public void deleteUssd(Ussd ussd) {
        long id = ussd.getId();
        System.out.println("Ussd deleted with id: " + id);
        database.delete(UssdSqliteHelper.TABLE_USSD, UssdSqliteHelper.COLUMN_ID
                + " = " + id, null);
    }

    public List<Ussd> getAllUssds() {
        List<Ussd> ussds = new ArrayList<Ussd>();

        Cursor cursor = database.query(UssdSqliteHelper.TABLE_USSD,
                allColumns, null, null, null, null, null);

        cursor.moveToLast();
        while (!cursor.isBeforeFirst()) {
            Ussd ussd = cursorToUssd(cursor);
            ussds.add(ussd);
            cursor.moveToPrevious();
        }
        // make sure to close the cursor
        cursor.close();
        return ussds;
    }

    public Ussd getFirstRecord() {
        Cursor cursor = database.query(UssdSqliteHelper.TABLE_USSD,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        Ussd ussd = cursorToUssd(cursor);
        // make sure to close the cursor
        cursor.close();
        return ussd;
    }

    public Ussd getLastRecord() {
        Cursor cursor = database.query(UssdSqliteHelper.TABLE_USSD,
                allColumns, null, null, null, null, null);

        cursor.moveToLast();
        Ussd ussd = cursorToUssd(cursor);
        // make sure to close the cursor
        cursor.close();
        return ussd;
    }


    private Ussd cursorToUssd(Cursor cursor) {
        if(cursor.getCount() == 0)
            return null;
        Ussd ussd = new Ussd();
        ussd.setId(cursor.getLong(0));
        ussd.setMessage(cursor.getString(1));
        ussd.setTimeStamp(cursor.getLong(2));
        ussd.setBalance(cursor.getString(3));
        return ussd;
    }
}