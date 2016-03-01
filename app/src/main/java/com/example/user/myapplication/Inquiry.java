package com.example.user.myapplication;

import android.app.DatePickerDialog;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import cz.msebera.android.httpclient.Header;


public class Inquiry extends AppCompatActivity {

    private EditText etName,etPhone,etEduInstitute,etSubject,etReplyDate;
    private Spinner spSupervisor;
    private SimpleDateFormat dateFormatter;
    private DatePickerDialog datePickerDialogStart;
    private DrawingView drawingView;
    private LinearLayout linearDrawing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inquiry);

        drawingView = new DrawingView(this);
        linearDrawing = (LinearLayout) findViewById(R.id.linearDrawing);
        linearDrawing.addView(drawingView);

        spSupervisor = (Spinner) findViewById(R.id.spSupervisor);

        etName = (EditText) findViewById(R.id.etName);
        etPhone = (EditText) findViewById(R.id.etPhone);
        etEduInstitute = (EditText) findViewById(R.id.etEduInstitute);
        etSubject = (EditText) findViewById(R.id.etSubject);
        etReplyDate = (EditText) findViewById(R.id.etReplyDate);
        dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        setDateField();

    }


    private void setDateField() {
        final Calendar newCalendar = Calendar.getInstance();

        etReplyDate.setOnClickListener(new View.OnClickListener()
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
                    etReplyDate.setText(dateFormatter.format(newDate.getTime()));
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

    }

    public void onSend(View v)
    {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("Insert", getSqlStatement());
        client.post("http://104.197.212.107:3000/Insert", params, new TextHttpResponseHandler()
                {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String res)
                    {
                        // called when response HTTP status is "200 OK
                        //tvResult.setText(res);
                        Log.d("Result: ",res);
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String res, Throwable t)
                    {
                        // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                        Log.d("Error: ", res);
                    }
                }
        );
    }

    private String getSqlStatement()
    {
        String name = etName.getText().toString();
        String phone = etPhone.getText().toString();
        Date currentDate = new Date();
        String date = dateFormatter.format(currentDate);
        String eduInstitute = etEduInstitute.getText().toString();
        String subject = etSubject.getText().toString();
        String supervisor = spSupervisor.getSelectedItem().toString();
        String replyDate = etReplyDate.getText().toString();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        drawingView.getDrawingCache().compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream .toByteArray();
        String signature = Base64.encodeToString(byteArray, Base64.DEFAULT);


        String sql ="INSERT INTO users (name,phone,date,institute,subject,supervisor," +
                "replayDate,signature) values " +
                "('"+name+"',"+phone+",'"+date+"','"+eduInstitute+"'," +
                "'"+subject+"','"+supervisor+"','"+replyDate+"','"+signature+"')";



        return sql;
    }
}
