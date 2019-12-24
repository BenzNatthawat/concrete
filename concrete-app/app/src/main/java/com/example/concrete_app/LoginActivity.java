package com.example.concrete_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class LoginActivity extends AppCompatActivity {

    private static final int REGISTER_ACTIVITY_REQUEST_CODE = 1;

    EditText usernameUI;
    EditText passwordUI;
    Button btnLoginUI;

    String result;
    JSONObject objDataResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameUI = (EditText) findViewById(R.id.editTextUsername);
        passwordUI = (EditText) findViewById(R.id.editTextPassword);
        btnLoginUI = (Button) findViewById(R.id.btnLogin);
        final SharedData sharedData = SharedData.getInstance();

        btnLoginUI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    result = new RequestAsync(usernameUI.getText().toString(), passwordUI.getText().toString()).execute().get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    objDataResult = new JSONObject(result);
                    sharedData.setToken((String) objDataResult.get("token"));
                    sharedData.setName((String) objDataResult.get("name"));
                    Intent intent = new Intent();
                    setResult(RESULT_OK, intent);
                    finish();
                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), "ชื่อผู้ใช้งานหรือรหัสผ่านไม่ถูกต้อง", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });

    }

    public class RequestAsync extends AsyncTask<String,String,String> {

        SharedData sharedData = SharedData.getInstance();
        String username, password;
        public RequestAsync(String userName, String password) {
            this.username = userName;
            this.password = password;
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                // POST Request
                JSONObject postDataParams = new JSONObject();
                postDataParams.put("username", username);
                postDataParams.put("password", password);
                return RequestHandler.sendPost(BuildConfig.SERVER_URL + "/login", postDataParams);
            }
            catch(Exception e){
                return new String("Exception: " + e.getMessage());
            }
        }
    }
}

