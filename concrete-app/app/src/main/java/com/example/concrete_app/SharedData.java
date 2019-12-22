package com.example.concrete_app;

public class SharedData {

    private static SharedData instance = new SharedData();
    public static SharedData getInstance() {
        return instance;
    }
    public static void setInstance(SharedData instance) {
        SharedData.instance = instance;
    }


    private String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6eyJpZCI6MSwidXNlcm5hbWUiOiJiZW56In0sImlhdCI6MTU3Njk3MDU3NSwiZXhwIjoxNTc3MDU2OTc1fQ.LByEqqCr3hrlCE_wTB0_EeoV3DJZRfT6OSOvK907kpc";
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