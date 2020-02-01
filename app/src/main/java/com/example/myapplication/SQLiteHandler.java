package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHandler extends SQLiteOpenHelper {
    private static final int DATABSE_VERSION = 1;

    private static final String DATABASE_NAME = "android";

    private static final String TABLE_USER = "users";

    //
    private static final String KEY_GENDER = "gender";
    private static final String KEY_NAME = "name";
    private static final String KEY_AGE = "age";
    private static final String KEY_WEIGHT = "weight";
    private static final String KEY_HEIGHT = "height";
    private static final String KEY_STAR = "stars";
    private static final String KEY_RESULT = "result";
    private static final String KEY_CAL = "calories";
    private static final String KEY_DATE = "dates";
    private static final String KEY_DAILY = "daily";
    private static final String KEY_TEMPO = "tempo";

    private static final String DATABASE1_CREATE = "create table "
            + TABLE_USER + "( "
            + KEY_NAME + " text not null primary key, "
            + KEY_GENDER + " integer not null, "
            + KEY_AGE + " integer not null, "
            + KEY_WEIGHT + " real not null, "
            + KEY_HEIGHT + " real not null, "
            + KEY_STAR + " integer not null, "
            + KEY_RESULT +" text not null, "
            + KEY_CAL + " real not null, "
            + KEY_DATE + " text not null, "
            + KEY_DAILY +" real not null, "
            + KEY_TEMPO +" real not null ) ; ";


    public SQLiteHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABSE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE1_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {



    }
    public void addUser(String name, int gender, int age, double weight, double height, int stars, String result, double calories, String dates, double daily, double tempo) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name);
        values.put(KEY_GENDER, gender);
        values.put(KEY_AGE, age);
        values.put(KEY_WEIGHT, weight);
        values.put(KEY_HEIGHT,height);
        values.put(KEY_STAR, stars);
        values.put(KEY_RESULT, result);
        values.put(KEY_CAL, calories);
        values.put(KEY_DATE, dates);
        values.put(KEY_DAILY, daily);
        values.put(KEY_TEMPO, tempo);

        long id = db.insert (TABLE_USER,null,values);
        db.close();
    }

    public void updatetable(String name, int gender, int age, double weight, double height, int stars, String result, double calories, String dates, double daily) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name);
        values.put(KEY_GENDER, gender);
        values.put(KEY_AGE, age);
        values.put(KEY_WEIGHT, weight);
        values.put(KEY_HEIGHT,height);
        values.put(KEY_STAR, stars);
        values.put(KEY_RESULT, result);
        values.put(KEY_CAL, calories);
        values.put(KEY_DATE, dates);

        String[] selectionArgs = {"Mytitle"};
        long id = db.update (TABLE_USER,values,KEY_NAME + " = ?",selectionArgs) ;
        db.close();
    }


    public Users getUser() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_USER, null);
        Users temp = null;
        while(cursor.moveToNext()){
            temp = new Users(cursor.getString(0), cursor.getInt(1), cursor.getInt(2), cursor.getDouble(3), cursor.getDouble(4), cursor.getInt(5), cursor.getString(6), cursor.getDouble(7), cursor.getString(8), cursor.getDouble(9), cursor.getDouble(10));

        }
        db.close();
        return temp;
    }

    public void update_daily(double daily, String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_DAILY, daily);
        db.update(TABLE_USER, values, KEY_NAME + " = ?", new String[]{name}) ;
        db.close();
    }

    public void update_Users(String dates, String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_DATE, dates);
        db.update(TABLE_USER, values, KEY_NAME + " = ?", new String[]{name}) ;
        db.close();
    }

    public void update_tempo(double tempo, String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TEMPO, tempo);
        db.update(TABLE_USER, values, KEY_NAME + " = ?", new String[]{name}) ;
        db.close();
    }
    public boolean isUserEmpty(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_USER, null);
        boolean temp = true;
        while(cursor.moveToNext()){
            temp = false;
        }
        db.close();
        return temp;
    }

    @Override
    protected void finalize() throws Throwable {
        this.close();
        super.finalize();
    }

    public void clear() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + TABLE_USER);
    }





/*    public void removeAll()
    {
        // db.delete(String tableName, String whereClause, String[] whereArgs);
        // If whereClause is null, it will delete all rows.
        SQLiteDatabase db = this.getWritableDatabase(); // helper is object extends SQLiteOpenHelper
        db.delete(TABLE_USER, null, null);
        db.close();
    }
*/
}