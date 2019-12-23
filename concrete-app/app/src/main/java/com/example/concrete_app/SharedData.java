package com.example.concrete_app;

public class SharedData {

    private static SharedData instance = new SharedData();
    public static SharedData getInstance() {
        return instance;
    }
    public static void setInstance(SharedData instance) {
        SharedData.instance = instance;
    }


    private String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6eyJpZCI6MSwidXNlcm5hbWUiOiJiZW56In0sImlhdCI6MTU3NzEyMTE5NCwiZXhwIjoxNTc3MjA3NTk0fQ.0ISq8H8ZMkh5iySgkokN6TdD_B_o3giYDEvvIH1P_-0";
    private String name = "";

    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}