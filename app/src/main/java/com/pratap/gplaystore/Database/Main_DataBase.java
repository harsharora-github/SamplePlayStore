package com.pratap.gplaystore.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import static com.pratap.gplaystore.Database.Database_Constants.INSTALL_TABLE;

/**
 * Created by harsh.arora on 02-11-2017.
 */

public class Main_DataBase extends SQLiteOpenHelper {



    private static final String COLUMN_ID = "id";
    private static final String STATUS = "status";
    private static final String DATETIME = "date_time";
    private static final String PACKAGE_NAME = "package_name";
    private static final String APP_URL = "app_url";
    private static final String LOCATION = "app_loc";


    public Main_DataBase(Context context) {

        super(context, Database_Constants.DB, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table " + INSTALL_TABLE + " " +
                        "(" + COLUMN_ID + " integer primary key, " + STATUS + " text," + DATETIME + " DATETIME DEFAULT CURRENT_TIMESTAMP," + PACKAGE_NAME + " text," + APP_URL + " text," + LOCATION + " text)"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + INSTALL_TABLE);

        onCreate(db);
    }



    private boolean checkTable(String table_name) {
        SQLiteDatabase db;
        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name = '"
                + table_name + "'", null);
        if (cursor.getCount() > 0) {
            cursor.close();
            db.close();
            return true;
        } else {
            cursor.close();
            db.close();
            return false;
        }

    }


    public boolean insertINSTALL_TABLE(String status,String package_name,String app_url,String app_loc) {
        if (checkTable(INSTALL_TABLE)) {
            SQLiteDatabase db;
            db = this.getWritableDatabase();
            try {
                ContentValues contentValues = new ContentValues();
                contentValues.put(STATUS, status);

                contentValues.put(PACKAGE_NAME, package_name);
                contentValues.put(APP_URL, app_url);
                contentValues.put(LOCATION, app_loc);

                db.insert(INSTALL_TABLE, null, contentValues);
            } catch (SQLiteException e) {
                e.printStackTrace();
                return false;
            } finally {
                db.close();
            }

        } else {
            SQLiteDatabase db;
            db = this.getWritableDatabase();
            try {
                ContentValues contentValues = new ContentValues();
                contentValues.put(STATUS, status);

                db.execSQL(
                        "create table " + INSTALL_TABLE + " " +
                                "(" + COLUMN_ID + " integer primary key, " + STATUS + " text," + DATETIME + " DATETIME DEFAULT CURRENT_TIMESTAMP," + PACKAGE_NAME + " package_name," + APP_URL + " text," + LOCATION + " text)"
                );
                db.insert(INSTALL_TABLE, null, contentValues);
            } catch (SQLiteException e) {

                e.printStackTrace();
                return false;
            } finally {
                db.close();
            }

        }
        return true;
    }



    public boolean insertUPDATE_TABLE1(String status, String pkg) {
        if (checkTable(INSTALL_TABLE)) {
            SQLiteDatabase db;
            db = this.getWritableDatabase();
            Log.d("harsh","UpdateStatus;"+status);
            try {
                ContentValues contentValues = new ContentValues();
                contentValues.put(STATUS, status);
               int x =  db.update(INSTALL_TABLE, contentValues, PACKAGE_NAME + "='"+pkg+"'",null);
               Log.d("harsh","UpdateCount;"+String.valueOf(x));

            } catch (SQLiteException e) {
                e.printStackTrace();
                return false;
            } finally {
                db.close();
            }

        } else {
            SQLiteDatabase db;
            db = this.getWritableDatabase();
            try {
                ContentValues contentValues = new ContentValues();
                contentValues.put(STATUS, status);

                db.execSQL(
                        "create table " + INSTALL_TABLE + " " +
                                "(" + COLUMN_ID + " integer primary key, " + STATUS + " text," + DATETIME + " DATETIME DEFAULT CURRENT_TIMESTAMP," + PACKAGE_NAME + " package_name," + APP_URL + " app_url)"
                );
                db.insert(INSTALL_TABLE, null, contentValues);
            } catch (SQLiteException e) {

                e.printStackTrace();
                return false;
            } finally {
                db.close();
            }

        }
        return true;
    }

    public boolean insertINSTALL_TABLE2(String app_loc,String pkg) {
        if (checkTable(INSTALL_TABLE)) {
            SQLiteDatabase db;
            db = this.getWritableDatabase();
            try {
                ContentValues contentValues = new ContentValues();
                contentValues.put(LOCATION, app_loc);
                 db.update(INSTALL_TABLE, contentValues, PACKAGE_NAME + "='"+pkg+"'",null);
               // Log.d("harsh","Updatelocation;"+String.valueOf(x));
            } catch (SQLiteException e) {
                e.printStackTrace();
                return false;
            } finally {
                db.close();
            }

        } else {
            SQLiteDatabase db;
            db = this.getWritableDatabase();
            try {
                ContentValues contentValues = new ContentValues();
                contentValues.put(LOCATION, app_loc);

                db.execSQL(
                        "create table " + INSTALL_TABLE + " " +
                                "(" + COLUMN_ID + " integer primary key, " + STATUS + " text," + DATETIME + " DATETIME DEFAULT CURRENT_TIMESTAMP," + PACKAGE_NAME + " package_name," + APP_URL + " app_url)"
                );
                  db.update(INSTALL_TABLE, contentValues, PACKAGE_NAME + "='"+pkg+"'",null);
               // Log.d("harsh","Updatelocation;"+String.valueOf(x));
            } catch (SQLiteException e) {

                e.printStackTrace();
                return false;
            } finally {
                db.close();
            }

        }
        return true;
    }

    public boolean deleteRecordsHOMEKEY(String hr) {
        SQLiteDatabase db;
        db = this.getWritableDatabase();
        try {
            db.delete( INSTALL_TABLE, DATETIME + " < DATETIME('now', '-" + hr + " hours')", null);
            // db.rawQuery("delete from " + DBEssentials.HOME_KEY + " where " + DATETIME + " < DATETIME('now', '-" + hr + " hours')", null);
        } catch (SQLiteException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        return true;
    }

    public ArrayList<InstallTable> selectDATA (){
        SQLiteDatabase db;

        db = this.getReadableDatabase();

        Cursor c = db.rawQuery("select id, status, package_name, app_url from InstallTABLE WHERE status is null or status = '' limit 1" , null);

        ArrayList<String> mArrayList = new ArrayList<String>();
        ArrayList<InstallTable> mArrayList1 = new ArrayList<InstallTable>();
        InstallTable ins = new InstallTable();
        for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            ins.setid(c.getString(c.getColumnIndex(COLUMN_ID)));
            ins.setPackage_name(c.getString(c.getColumnIndex(PACKAGE_NAME)));
            ins.setStatus(c.getString(c.getColumnIndex(STATUS)));
            ins.setApp_url(c.getString(c.getColumnIndex(APP_URL)));
            mArrayList1.add(ins);
        }
        c.close();
        db.close();
        return mArrayList1;
    }
}
