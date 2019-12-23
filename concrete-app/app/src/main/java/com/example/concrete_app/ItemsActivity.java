package com.example.concrete_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class ItemsActivity extends AppCompatActivity {

    String items;
    ListView listView;
    ArrayList<Items> contactAdapter = new  ArrayList<Items>();
    JSONObject objDataResult, itemsObject;
    JSONArray jsonArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        try {
            items = new ItemsActivity.RequestAsync().execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            objDataResult = new JSONObject(items);
            jsonArray = objDataResult.getJSONArray("results");

            System.out.println("objDataResult");
            System.out.println(objDataResult);
            for (int i = 0; i < jsonArray.length(); i++) {
                itemsObject = jsonArray.getJSONObject(i);
                System.out.println("xxxxxxx");
                System.out.println(itemsObject);
                Items item = new Items( i+1, itemsObject.getString("cube"), Float.parseFloat(itemsObject.getString("price")), Float.parseFloat(itemsObject.getString("installment")) );
                contactAdapter.add(item);
            }
        } catch (JSONException e) {
            Toast.makeText(getApplicationContext(), "ชื่อผู้ใช้งานหรือรหัสผ่านไม่ถูกต้อง", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

        ItemsAdapter adapter = new ItemsAdapter(this, contactAdapter);
        listView = (ListView) findViewById(R.id.listViewOrders);
        listView.setAdapter(adapter);
    }

    public class RequestAsync extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... strings) {
            try {
                return RequestHandler.sendGet(BuildConfig.SERVER_URL + "/items");
            }
            catch(Exception e){
                return new String("Exception: " + e.getMessage());
            }
        }
    }
}

