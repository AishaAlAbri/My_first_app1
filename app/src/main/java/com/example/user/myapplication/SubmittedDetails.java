package com.example.user.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class SubmittedDetails extends AppCompatActivity
{

    private InquiryData inquiryData;
    private TextView tvName,tvPhone,tvDate,tvInstitute,tvSubject,tvSupervisor,tvReplayDate;
    private ImageView imageSignature;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submitted_details);
        inquiryData = (InquiryData)getIntent().getSerializableExtra("data");

        tvName = (TextView) findViewById(R.id.tvName);
        tvPhone = (TextView) findViewById(R.id.tvPhone);
        tvDate = (TextView) findViewById(R.id.tvDate);
        tvInstitute = (TextView) findViewById(R.id.tvInstitute);
        tvSubject = (TextView) findViewById(R.id.tvSubject);
        tvSupervisor = (TextView) findViewById(R.id.tvSupervisor);
        tvReplayDate = (TextView) findViewById(R.id.tvReplyDate);
        imageSignature = (ImageView) findViewById(R.id.imageSignature);


        tvName.setText("Name: " +inquiryData.getName());
        tvPhone.setText("Phone: " +inquiryData.getPhone());
        tvDate.setText("Date: " +inquiryData.getDate());
        tvInstitute.setText("Institute: " +inquiryData.getInstitute());
        tvSubject.setText("Subject: " +inquiryData.getSubject());
        tvSupervisor.setText("Supervisor: " +inquiryData.getSupervisor());
        tvReplayDate.setText("Replay Date: " +inquiryData.getReplyDate());

    }
}
