package com.example.user.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.OutputStreamWriter;

import cz.msebera.android.httpclient.Header;

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

        getSignature();

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
        tvReplayDate.setText("Reply Date: " + inquiryData.getReplyDate());




    }

    private void getSignature()
    {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("Query", "select signature from users where userID= "+inquiryData.getUserID());
        client.post("http://104.197.212.107:3000/query", params, new TextHttpResponseHandler()
                {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String res)
                    {
                        // called when response HTTP status is "200 OK
                        try {
                            //Log.d("Signature result: ",res);
                            JSONArray jsonArray = new JSONArray(res);
                            String signature  = jsonArray.getJSONObject(0).getString("signature");
                            inquiryData.setSignature(signature);
                            imageSignature.setImageBitmap(inquiryData.getSignature());
                            }
                        catch (NullPointerException npe){npe.printStackTrace();}
                        catch (Exception e){e.printStackTrace();}

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
}
