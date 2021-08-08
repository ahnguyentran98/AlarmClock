package com.example.prm391x_alarmclock_fx02338;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatCallback;
import androidx.appcompat.widget.AppCompatTextView;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements MakeVisible {
    Button schedule;
    public ListView alarmList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        schedule = (Button)findViewById(R.id.alarm_schedule);

        AlarmFragment fragment = (AlarmFragment)getFragmentManager().findFragmentById(R.id.alarm_frag);
        fragment.showAlarmList();
    }
    //show alarmFragment
    public void alarmFragmentClick(View view){
        //sau khi back mất luôn main view
//        alarmList.setVisibility(View.GONE);
//        schedule.setVisibility(View.GONE);
        Fragment fragment = new AlarmFragment();
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_switch,fragment).addToBackStack(null);
        fragmentTransaction.commit();
    }
    //
    @Override
    public void makeVisible() {
        alarmList.setVisibility(View.VISIBLE);
        schedule.setVisibility(View.VISIBLE);
    }
}