package com.example.concrete_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class OrdersActivity extends AppCompatActivity {

    String orders;
    ListView listView;
    ArrayList<Orders> contactAdapter = new  ArrayList<Orders>();
    JSONObject objDataResult, orderObject;
    JSONArray jsonArray;
    Button btnCreateOrders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        try {
            orders = new RequestAsync().execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            objDataResult = new JSONObject(orders);
            jsonArray = objDataResult.getJSONArray("results");

            for (int i = 0; i < jsonArray.length(); i++) {
                orderObject = jsonArray.getJSONObject(i);
                String pattern = "MM-dd-yyyy";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                Date date = simpleDateFormat.parse(orderObject.getString("deliveryDateTime"));
                Orders order = new Orders( i+1, orderObject.getString("id"), simpleDateFormat.format(date) );
                contactAdapter.add(order);
            }
        } catch (JSONException e) {
            Toast.makeText(getApplicationContext(), "ชื่อผู้ใช้งานหรือรหัสผ่านไม่ถูกต้อง", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        btnCreateOrders = (Button) findViewById(R.id.btnCreateOrders);
        btnCreateOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent CreateOrders = new Intent(OrdersActivity.this, FromOrderActivity.class);
                startActivity(CreateOrders);
            }
        });

        OrdersAdapter adapter = new OrdersAdapter(this, contactAdapter);
        listView = (ListView) findViewById(R.id.listViewOrders);
        listView.setAdapter(adapter);
    }

    public class RequestAsync extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... strings) {
            try {
                return RequestHandler.sendGet(BuildConfig.SERVER_URL + "/orders");
            }
            catch(Exception e){
                return new String("Exception: " + e.getMessage());
            }
        }
    }
}
