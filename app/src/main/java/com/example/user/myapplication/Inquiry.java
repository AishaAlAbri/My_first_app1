package com.example.user.myapplication;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class Inquiry extends AppCompatActivity {

    private EditText etDate;
    private SimpleDateFormat dateFormatter;
    private DatePickerDialog datePickerDialogStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inquiry);
        etDate = (EditText) findViewById(R.id.editTextDate);
        dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        setDateField();

    }


    private void setDateField() {
        final Calendar newCalendar = Calendar.getInstance();

        etDate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Calendar cal = Calendar.getInstance();
                cal.setTime(new Date());
                datePickerDialogStart.getDatePicker().setMinDate(cal.getTimeInMillis());
                datePickerDialogStart.show();
            }
        });



        datePickerDialogStart = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                try {
                    Calendar newDate = Calendar.getInstance();
                    newDate.set(year, monthOfYear, dayOfMonth);
                    etDate.setText(dateFormatter.format(newDate.getTime()));
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

    }
}
