package com.example.concrete_app;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

public class ConfirmSendActivity extends AppCompatActivity {

    EditText nameId, dateId, tel, descriptionId;
    Button sendOrderId;
    TextView totalPriceId;
    String result;
    SharedData sharedData;
    JSONObject objDataResult;
    private Calendar myCalendar = Calendar.getInstance();

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


        final DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String myFormat = "MM/dd/yy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                dateId.setText(sdf.format(myCalendar.getTime()));
            }

        };

        dateId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(ConfirmSendActivity.this, datePickerListener, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        sendOrderId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sharedData.sizeBaskets() > 0) {
                    try {
                        result = new RequestAsync(nameId.getText().toString(), dateId.getText().toString(), tel.getText().toString(), descriptionId.getText().toString(), sharedData.getBaskets()).execute().get();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    try {
                        objDataResult = new JSONObject(result);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        if((int) objDataResult.get("status") == 200) {
                            Intent intent = new Intent();
                            setResult(RESULT_OK, intent);
                            finish();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
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
