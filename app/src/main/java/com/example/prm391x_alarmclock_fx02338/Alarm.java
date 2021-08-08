package com.example.prm391x_alarmclock_fx02338;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Calendar;

import static com.example.prm391x_alarmclock_fx02338.AlarmReceiver.TITLE;


public class Alarm {
    private int alarmId;
    private int hour, minute;
    private String title;

    public Alarm(int alarmId, int hour, int minute, String title) {
        super();
        this.alarmId = alarmId;
        this.hour = hour;
        this.minute = minute;
        this.title = title;
    }

    public int getAlarmId() {
        return alarmId;
    }

    public void setAlarmId(int alarmId) {
        this.alarmId = alarmId;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title +" "+ hour +": " +minute;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void schedule(Context context){
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        //send data to alarmBroadcastReceiver
        Intent intent = new Intent(context,AlarmReceiver.class);
        intent.putExtra(TITLE,title);
        //allow AlarmManager to access application
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,alarmId,intent,0);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY,hour);
        calendar.set(Calendar.MINUTE,minute);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);
        if (calendar.getTimeInMillis() <= System.currentTimeMillis()) {
            calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 1);
        }
        alarmManager.setExact(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);
    }
}
