package com.example.user.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SubmittedDetails extends AppCompatActivity
{

    private InquiryData inquiryData;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submitted_details);
        inquiryData = (InquiryData)getIntent().getSerializableExtra("data");
    }
}
