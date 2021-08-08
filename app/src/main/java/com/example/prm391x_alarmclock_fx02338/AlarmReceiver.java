package com.example.prm391x_alarmclock_fx02338;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AlarmReceiver extends BroadcastReceiver {
    public static final String TITLE = "TITLE";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("toi trong receiver","hello");
        Intent myIntent = new Intent(context, RingService.class);
        myIntent.putExtra(TITLE,intent.getStringExtra(TITLE));
        context.startService(myIntent);
    }
}
