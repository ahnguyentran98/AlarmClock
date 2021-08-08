package com.example.prm391x_alarmclock_fx02338;


import android.app.AlarmManager;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Random;

import static androidx.core.content.ContextCompat.getSystemService;

public class AlarmFragment extends Fragment  {


    public Button addButton;
    public EditText alarmTitle;
    public TextView alarmInfo;
    public TimePicker timePicker;
    public Calendar calendar;
    public AlarmManager alarmManager;
    Intent intent;
    public DatabaseHelper databaseHelper = new DatabaseHelper(getActivity()) ;
    public ListView alarmList1;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.alarm_fragment, container, false);
        addButton = (Button)rootView.findViewById(R.id.alarm_add);
        alarmTitle = (EditText) rootView.findViewById(R.id.alarm_title);
        alarmInfo = (TextView) rootView.findViewById(R.id.alarm_info);
        timePicker = (TimePicker) rootView.findViewById(R.id.time_picker);
        //get listview from main
        alarmList1 = (ListView)getView().findViewById(R.id.alarm_list);
        alarmManager = (AlarmManager)getActivity().getSystemService(Context.ALARM_SERVICE);
        calendar = Calendar.getInstance();
        intent = new Intent(getActivity(),AlarmReceiver.class);
        addButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                scheduleAlarm();

//                ((MainActivity)getActivity()).viewData();
//                //back to main

            }
        });

        return rootView;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)

    public void scheduleAlarm() {
        int alarmId = new Random().nextInt(Integer.MAX_VALUE);
        int hour = timePicker.getCurrentHour();
        int minute = timePicker.getCurrentMinute();
        //create new alarm
        Alarm alarm = new Alarm(alarmId,hour,minute,alarmTitle.getText().toString());

        alarm.schedule(getContext());
        Toast.makeText(getActivity(),"Báo thức " + alarmTitle.getText() + " được đặt hẹn giờ lúc " + hour + " : " + minute,Toast.LENGTH_LONG).show();



    }
    public void showAlarmList(){
        Cursor c = databaseHelper.viewData();
        AlarmCursorAdapter alarmCursorAdapter = new AlarmCursorAdapter(getActivity(),c);
        alarmList1.setAdapter(alarmCursorAdapter);
    }




}
