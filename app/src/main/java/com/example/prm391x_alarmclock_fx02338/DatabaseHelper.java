package com.example.prm391x_alarmclock_fx02338;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.ListView;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "alarmList.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME ="alarm";
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_HOUR = "hour";
    private static final String KEY_MINUTE = "minute";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_alarm_table = "CREATE TABLE " + TABLE_NAME + "(" + KEY_ID + " INTEGER PRIMARY KEY, " + KEY_TITLE + " TEXT," + KEY_HOUR +" TEXT," + KEY_MINUTE + " TEXT" + ")";
        db.execSQL(create_alarm_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //delete old table
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        //create new table
        onCreate(db);
    }

    //insert data
    public void addAlarm(Alarm alarm){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_TITLE,alarm.getTitle());
        contentValues.put(KEY_HOUR,alarm.getHour());
        contentValues.put(KEY_MINUTE,alarm.getMinute());
        db.insert(TABLE_NAME,null,contentValues);
        Log.e("đã thêm", alarm.getTitle() + alarm.getHour()+ alarm.getMinute());

    }
    //view data\

    public Cursor viewData(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        return cursor;
    }
//    public void showAlarmList(){
//        DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
//        SQLiteDatabase db = databaseHelper.getWritableDatabase();
//        Cursor cursor = db.rawQuery("Select * from " + TABLE_NAME,null);
//        ListView alarmList = (ListView)getView().findViewById(R.id.alarm_list);
//        AlarmCursorAdapter alarmCursorAdapter = new AlarmCursorAdapter(getActivity(), cursor);
//    }
}
