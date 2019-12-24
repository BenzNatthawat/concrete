package com.example.concrete_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class RegisterActivity extends AppCompatActivity {

    EditText usernameUI;
    EditText passwordUI;
    EditText nameUI;
    Button btRegisterUI;
    String result;
    JSONObject objDataResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        usernameUI = (EditText) findViewById(R.id.editTextUsername);
        passwordUI = (EditText) findViewById(R.id.editTextPassword);
        nameUI = (EditText) findViewById(R.id.editTextName);
        btRegisterUI = (Button) findViewById(R.id.register);

        btRegisterUI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    result = new RegisterActivity.RequestAsync(usernameUI.getText().toString(), passwordUI.getText().toString(), nameUI.getText().toString()).execute().get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    objDataResult = new JSONObject(result);
                    sharedData.setToken((String) objDataResult.get("token"));
                    sharedData.setName((String) objDataResult.get("name"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if(sharedData.getToken() != ""){
                    Intent intent = new Intent();
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูล", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public class RequestAsync extends AsyncTask<String,String,String> {

        SharedData sharedData = SharedData.getInstance();
        String username, password, name;
        public RequestAsync(String userName, String password, String name) {
            this.username = userName;
            this.password = password;
            this.name = name;
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                // POST Request
                JSONObject postDataParams = new JSONObject();
                postDataParams.put("username", username);
                postDataParams.put("password", password);
                postDataParams.put("name", name);

                return RequestHandler.sendPost(BuildConfig.SERVER_URL + "/register", postDataParams);
            }
            catch(Exception e){
                return new String("Exception: " + e.getMessage());
            }
        }
    }
}
