package com.example.concrete_app;

import java.util.ArrayList;

public class SharedData {

    private static SharedData instance = new SharedData();
    public static SharedData getInstance() {
        return instance;
    }
    public static void setInstance(SharedData instance) {
        SharedData.instance = instance;
    }


    private String token = "";
    private String name = "";
    private ArrayList<Basket> baskets = new ArrayList<Basket>();

    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public void setBasketEmpty() { this.baskets =  new ArrayList<Basket>();  }
    public void addBaskets(Basket item) { this.baskets.add(item); }
    public int sizeBaskets() {
        if( baskets != null ) {
            return baskets.size();
        } else {
            return 0;
        }
    }
    public Basket getBasket(int i) {
        return baskets.get(i);
    }
    public ArrayList<Basket> getBaskets() {
        return baskets;
    }
}