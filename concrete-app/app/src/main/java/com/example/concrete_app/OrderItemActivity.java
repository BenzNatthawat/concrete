package com.example.concrete_app;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class OrderItemActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String orders;
    ListView listView;
    ArrayList<Basket> itemAdapter = new  ArrayList<Basket>();
    JSONObject objDataResult, orderObject, itemsObject;
    JSONArray jsonArray;
    Spinner statusUI;
    String status;
    Button submitUI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_item);

        Bundle bundle = getIntent().getExtras();
        final int position = bundle.getInt("id");

        statusUI = findViewById(R.id.role);
        submitUI = findViewById(R.id.submit);

        ArrayAdapter<CharSequence> adapterSpinner = ArrayAdapter.createFromResource(this, R.array.status, android.R.layout.simple_spinner_item);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        statusUI.setAdapter(adapterSpinner);
        statusUI.setOnItemSelectedListener(this);

        try {
            orders = new RequestAsync(position, "GET").execute().get();
            objDataResult = new JSONObject(orders);

            orderObject = objDataResult.getJSONObject("results");
            jsonArray = orderObject.getJSONArray("item");

            if(jsonArray.length() > 0) {
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

            submitUI.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        new RequestAsync(position, "POST", status).execute().get();
                        finish();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        status = text;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    public class RequestAsync extends AsyncTask<String,String,String> {

        int position;
        String method;
        String status;
        public RequestAsync(int position, String method) {
            this.position = position;
            this.method = method;
        }

        public RequestAsync(int position, String method, String status) {
            this.position = position;
            this.method = method;
            this.status = status;
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                if(method.equals("POST")) {
                    JSONObject postDataParams = new JSONObject();
                    postDataParams.put("status", status);
                    return RequestHandler.sendPost(BuildConfig.SERVER_URL + "/orders/" + position, postDataParams);
                } else {
                    return RequestHandler.sendGet(BuildConfig.SERVER_URL + "/orders/" + position);
                }
            }
            catch(Exception e){
                return new String("Exception: " + e.getMessage());
            }
        }
    }
}
