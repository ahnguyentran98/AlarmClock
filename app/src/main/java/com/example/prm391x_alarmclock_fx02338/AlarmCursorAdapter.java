package com.example.prm391x_alarmclock_fx02338;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

public class AlarmCursorAdapter extends CursorAdapter {


    public AlarmCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView alarmInfo = (TextView)view.findViewById(R.id.custom_alarm_info);
        String info = cursor.getString(cursor.getColumnIndex("title"))
                + " " +cursor.getString(cursor.getColumnIndex("hour"))
                + ": " +cursor.getString(cursor.getColumnIndex("minute"))
                ;
        alarmInfo.setText(info);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        TextView view = new TextView(context);
        return view;
    }
}
