package com.example.user.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import cz.msebera.android.httpclient.Header;


public class MainActivity extends AppCompatActivity {

    private TextView tvResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        tvResult = (TextView) findViewById(R.id.textViewResult);

       /* Button submitBtn =(Button)(findViewById(R.id.submit));
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/
    }

    public void insert(View v)
    {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("Insert", "INSERT INTO users (name,phone,date,institute,subject,supervisor," +
                "replyDate,signature) value ('test2',9999,'2000','ev','test','test','test','test')");
        client.post("http://104.197.212.107:3000/Insert", params, new TextHttpResponseHandler()
                {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String res)
                    {
                        // called when response HTTP status is "200 OK
                        tvResult.setText(res);
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

    public void query(View v)
    {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("Query", "select * from users");
        client.post("http://104.197.212.107:3000/query", params, new TextHttpResponseHandler()
                {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String res)
                    {
                        // called when response HTTP status is "200 OK
                        tvResult.setText(res);
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
