package com.example.user.myapplication;

import android.preference.PreferenceActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class ViewSubmitted extends AppCompatActivity
{
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_submitted);

        listView = (ListView) findViewById(R.id.listView);
        getData();
    }

    private void getData()
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
                        viewData(res);
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


    private void viewData(String response)
    {
        String jsonStr = response;
        if (jsonStr != null) {
            try {

                    JSONObject jsonObj = new JSONObject(jsonStr);
                    String message = jsonObj.getString("name");
                    Toast.makeText(this, message, Toast.LENGTH_SHORT).show();




            } catch (JSONException e) {
                e.printStackTrace();
                Log.d("Error: ", response);
                Toast.makeText(this, "Error parsing JSON data."+response, Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(this, "Couldn't get any JSON data.", Toast.LENGTH_SHORT).show();
        }
    }
}
