package com.example.concrete_app;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class ConfirmSendActivity extends AppCompatActivity {

    EditText nameId, dateId, tel, descriptionId;
    Button sendOrderId;
    TextView totalPriceId;
    String result;
    SharedData sharedData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_send);

        sharedData = SharedData.getInstance();

        nameId = findViewById(R.id.nameId);
        dateId = findViewById(R.id.dateId);
        tel = findViewById(R.id.tel);
        descriptionId = findViewById(R.id.descriptionId);
        sendOrderId = findViewById(R.id.sendOrderId);
        totalPriceId = findViewById(R.id.totalPriceId);

        sendOrderId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    result = new RequestAsync(nameId.getText().toString(), dateId.getText().toString(), tel.getText().toString(), descriptionId.getText().toString(), sharedData.getBaskets()).execute().get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(sharedData.getBaskets());
            }
        });
    }

    public class RequestAsync extends AsyncTask<String,String,String> {

        String name, deliveryDateTime, tel, note;
        JSONArray itemJsArray = new JSONArray();

        public RequestAsync(String name, String deliveryDateTime, String tel, String note, ArrayList<Basket> baskets) {
            this.name = name;
            this.deliveryDateTime = deliveryDateTime;
            this.tel = tel;
            this.note = note;
            for (int i = 0; i < baskets.size(); i++) {
                JSONObject basket = new JSONObject();
                try {
                    basket.put("items_id", baskets.get(i).idItem);
                    basket.put("price", baskets.get(i).price);
                    basket.put("list", baskets.get(i).nameItem);
                    basket.put("quantity", baskets.get(i).numberItem);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                itemJsArray.put(basket);
            }
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                // POST Request
                JSONObject postDataParams = new JSONObject();
                postDataParams.put("name", name);
                postDataParams.put("deliveryDateTime", deliveryDateTime);
                postDataParams.put("tel", tel);
                postDataParams.put("note", note);
                postDataParams.put("item", itemJsArray);

                return RequestHandler.sendPost(BuildConfig.SERVER_URL + "/orders", postDataParams);
            }
            catch(Exception e){
                return new String("Exception: " + e.getMessage());
            }
        }
    }
}
