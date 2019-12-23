package com.example.concrete_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class FromOrderActivity extends AppCompatActivity {

    EditText editTextWide, editTextThick, editTextLong;
    Button btnCalculate;
    Double result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_from_order);

        editTextWide = (EditText) findViewById(R.id.editTextWide);
        editTextThick = (EditText) findViewById(R.id.editTextThick);
        editTextLong = (EditText) findViewById(R.id.editTextLong);
        btnCalculate = (Button) findViewById(R.id.calculate);

//        btnCalculate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                result = Double.parseDouble(editTextWide.getText().toString()) * Double.parseDouble(editTextThick.getText().toString()) * Double.parseDouble(editTextLong.getText().toString());
//            }
//        });

    }

}

