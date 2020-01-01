package com.example.concrete_app;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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
    Button confrimSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        confrimSend = findViewById(R.id.confrimSend);
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

            for (int i = 0; i < jsonArray.length(); i++) {
                itemsObject = jsonArray.getJSONObject(i);
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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BottomDialog bottomSheet = new BottomDialog();

                try {
                    itemsObject = jsonArray.getJSONObject(position);
                    Bundle bundle = new Bundle();
                    bundle.putString("id", itemsObject.getString("id"));
                    bundle.putString("cube", itemsObject.getString("cube"));
                    bundle.putString("price", String.valueOf(Float.parseFloat(itemsObject.getString("price"))));

                    bottomSheet.setArguments(bundle);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                bottomSheet.show(getSupportFragmentManager(), "exampleBottomSheet");
            }
        });

        confrimSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent confirmSend = new Intent(ItemsActivity.this, ConfirmSendActivity.class);
                startActivity(confirmSend);
            }
        });
    }

    public void setResultFromFragment() {
        SharedData sharedData = SharedData.getInstance();
        confrimSend.setText("ยืนยันการสั่ง " + sharedData.sizeBaskets());
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

