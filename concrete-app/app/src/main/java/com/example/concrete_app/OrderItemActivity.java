package com.example.concrete_app;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class OrderItemActivity extends AppCompatActivity {

    String orders;
    ListView listView;
    ArrayList<Basket> itemAdapter = new  ArrayList<Basket>();
    JSONObject objDataResult, orderObject, itemsObject;
    JSONArray jsonArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_item);

        Bundle bundle = getIntent().getExtras();
        int position = bundle.getInt("id");

        try {
            orders = new RequestAsync(position).execute().get();
            objDataResult = new JSONObject(orders);

            orderObject = objDataResult.getJSONObject("results");
            jsonArray = orderObject.getJSONArray("item");

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                itemsObject = jsonArray.getJSONObject(i);
                Basket basket = new Basket((i+1)+"", itemsObject.getString("list"), itemsObject.getInt("quantity"), itemsObject.getInt("quantity") * Float.parseFloat(itemsObject.getString("price")));
                itemAdapter.add(basket);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        listItemAdapter adapter = new listItemAdapter(this, itemAdapter);
        listView = (ListView) findViewById(R.id.listViewOrders);
        listView.setAdapter(adapter);

    }

    public class RequestAsync extends AsyncTask<String,String,String> {

        int position;
        public RequestAsync(int position) {
            this.position = position;
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                return RequestHandler.sendGet(BuildConfig.SERVER_URL + "/orders/"+position);
            }
            catch(Exception e){
                return new String("Exception: " + e.getMessage());
            }
        }
    }
}
