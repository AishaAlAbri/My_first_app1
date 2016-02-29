package com.example.user.myapplication;

import android.preference.PreferenceActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class ViewSubmitted extends AppCompatActivity
{
    private ListView listView;
    private ArrayList<InquiryData> inquiryDataArray;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_submitted);
        inquiryDataArray = new ArrayList<>();

        listView = (ListView) findViewById(R.id.listView);
        getData();
    }

    private void fillListView()
    {
        List<String> data = new ArrayList<>();
        for(int i = 0; i<inquiryDataArray.size();i++)
        {
            data.add(inquiryDataArray.get(i).getName());
        }
        ArrayAdapter ad = new ArrayAdapter(this,android.R.layout.simple_list_item_1,data);
        listView.setAdapter(ad);

        listView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                Toast.makeText(ViewSubmitted.this,inquiryDataArray.get(position).getUserID()+"",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
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

                    JSONArray jsonArray = new JSONArray(jsonStr);
                for (int i = 0; i<jsonArray.length();i++)
                {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    InquiryData inquiryData = new InquiryData();
                    inquiryData.setUserID(jsonObject.getInt("userID"));
                    inquiryData.setName(jsonObject.getString("name"));
                    inquiryData.setPhone(jsonObject.getInt("phone"));
                    inquiryData.setDate(jsonObject.getString("date"));
                    inquiryData.setInstitute(jsonObject.getString("institute"));
                    inquiryData.setSubject(jsonObject.getString("subject"));
                    inquiryData.setSupervisor(jsonObject.getString("supervisor"));
                    inquiryData.setReplyDate(jsonObject.getString("replayDate"));
                    inquiryData.setSignature(jsonObject.getString("signature"));

                    inquiryDataArray.add(inquiryData);
                }

                fillListView();



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
