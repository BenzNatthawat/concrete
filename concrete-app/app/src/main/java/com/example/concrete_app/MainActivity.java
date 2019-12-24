package com.example.concrete_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final int REGISTER_ACTIVITY_REQUEST_CODE = 1;
    private static final int LOGIN_ACTIVITY_REQUEST_CODE = 2;
    private static final int ORDERS_ACTIVITY_REQUEST_CODE = 3;
    Button btnLoginUI;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLoginUI = (Button) findViewById(R.id.btnLogin);
        btnRegister = (Button) findViewById(R.id.btnRegister);

        btnLoginUI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Login = new Intent(MainActivity.this, LoginActivity.class);
                startActivityForResult(Login, LOGIN_ACTIVITY_REQUEST_CODE);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Register = new Intent(MainActivity.this, RegisterActivity.class);
                startActivityForResult(Register, REGISTER_ACTIVITY_REQUEST_CODE);
            }
        });
    }

    // This method is called when the second activity finishes
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        SharedData sharedData = SharedData.getInstance();

        // check that it is the SecondActivity with an OK result
        if (requestCode == LOGIN_ACTIVITY_REQUEST_CODE || requestCode == REGISTER_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) { // Activity.RESULT_OK
                if(sharedData.getToken() != "") {
                    Intent Orders = new Intent(MainActivity.this, OrdersActivity.class);
                    startActivityForResult(Orders, ORDERS_ACTIVITY_REQUEST_CODE);
                }
            }
        }
    }
}
