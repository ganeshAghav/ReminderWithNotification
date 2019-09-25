package com.example.reminderwithnotifi;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private Button btnSetDate,btnSetTime;
    private TextView txtDate,txtTime;
    private int mYear, mMonth, mDay, mHour, mMinute;
    private Comman comman;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CallBroadcastReciver();
        comman=new Comman();

        txtDate=(TextView)findViewById(R.id.txtSetDate);
        txtTime=(TextView)findViewById(R.id.txtSetTime);


        btnSetDate=(Button)findViewById(R.id.btnSetDate);
        btnSetTime=(Button)findViewById(R.id.btnSetTime);

        btnSetDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Get Current Date
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                                String dayWithZero=String.format("%02d", dayOfMonth);
                                String monthWithZero=String.format("%02d", monthOfYear+1);

                                txtDate.setText(dayWithZero + "-" + (monthWithZero) + "-" + year);

                                comman.setUserDate(dayWithZero + "-" + (monthWithZero) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        btnSetTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Get Current Time
                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                                String hourWithZero=String.format("%02d", hourOfDay);
                                String minuteWithZero=String.format("%02d", minute);

                                txtTime.setText(hourWithZero + ":" + minuteWithZero+":00");
                                comman.setUserTime(hourWithZero + ":" + minuteWithZero+":00");

                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();

            }
        });

    }

    public void CallBroadcastReciver(){

        boolean alarm = (PendingIntent.getBroadcast(this, 0, new Intent("ALARM"), PendingIntent.FLAG_NO_CREATE) == null);

        if(alarm){
            Intent itAlarm = new Intent("ALARM");
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this,0,itAlarm,0);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            calendar.add(Calendar.SECOND, 3);
            AlarmManager alarme = (AlarmManager) getSystemService(ALARM_SERVICE);
            alarme.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),60000, pendingIntent);
        }
    }

}
