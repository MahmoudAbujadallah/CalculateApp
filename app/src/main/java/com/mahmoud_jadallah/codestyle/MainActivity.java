package com.mahmoud_jadallah.codestyle;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;


public class MainActivity extends AppCompatActivity {
    TextView tvBirthDay;
    TextView tvRemain;
    TextView tvAge;
    TextView tvIsBirth;
    TextView tvIsAge;
    TextView tvIsRemain;
    Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvRemain = findViewById(R.id.main_tv_remain);
        tvBirthDay = findViewById(R.id.main_tv_birthday);
        tvIsBirth = findViewById(R.id.tv_vis_birth);
        tvIsRemain = findViewById(R.id.tv_vis_remain);
        tvIsAge = findViewById(R.id.tv_vis_age);
        tvAge = findViewById(R.id.main_tv_age);
    }

    public void showCalender(View view) {
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH);
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        final DatePickerDialog datePickerDialog = new DatePickerDialog(
                MainActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month += 1;
                tvBirthDay.setText(dayOfMonth + " / " + month + " / " + year);
                tvIsBirth.setVisibility(View.VISIBLE);
                calcRemain(year, month, dayOfMonth);
                calcAge(year, month, dayOfMonth);
            }
        },
                currentYear,
                currentMonth,
                currentDay
        );
        datePickerDialog.show();
    }


    private void calcRemain(int year, int month, int day) {
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH);
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        if (year != 0 && month != 0 && day != 0 && currentYear > year) {
            int _year = currentYear - year;
            int _month = currentMonth - month;
            int _day = currentDay - day;
            _day = _day < 0 ? (_day * -1) : _day;
            String resultMessage = "";
            String y = getResources().getString(R.string.year);
            String m = getResources().getString(R.string.month);
            String d = getResources().getString(R.string.day);
            String and = getResources().getString(R.string.and);
            String complete = getResources().getString(R.string.complete);
            if (_month < 0) {
                if (((_month * -1) - 1) == 0) {
                    resultMessage = _day + " " + d + " " + complete + " " + _year + " " + y;

                } else {
                    resultMessage = ((_month * -1) - 1) + " " + m + " " + and + " " + _day + " " + d + "\n" + complete + " " + _year + " " + y;
                }
            }
            tvRemain.setText(resultMessage);
            tvIsRemain.setVisibility(View.VISIBLE);

        } else {
            Toast.makeText(MainActivity.this, R.string.error, Toast.LENGTH_SHORT).show();
        }
    }


    private void calcAge(int year, int month, int day) {
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH);
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        int _day = currentDay - day;
        if (_day < 0) {
            _day += 30;
            currentMonth = currentMonth - 1;
        }
        int _month = currentMonth - month;
        if (_month < 0) {
            _month += 12;
            currentYear = currentYear - 1;
        }

        int _year = currentYear - year;

        String y = getResources().getString(R.string.year);
        String m = getResources().getString(R.string.month);
        String d = getResources().getString(R.string.day);
        String birthDay;
        if (_month == 11) {
            birthDay = (_year + 1) + " " + y + ", " + 0 + " " + m + ", " + (_day) + " " + d + ".";

        } else {
            birthDay = _year + " " + y + ", " + (_month + 1) + " " + m + ", " + (_day + 1) + " " + d + ".";

        }
        tvAge.setText(birthDay);
        tvIsAge.setVisibility(View.VISIBLE);

    }

}