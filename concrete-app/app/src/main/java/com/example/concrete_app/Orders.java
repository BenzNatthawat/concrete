package com.example.concrete_app;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Orders {
    public int index;
    public String bill;
    public String delivery;

    public Orders(int index, String bill, String delivery) {
        this.index = index;
        this.bill = bill;
        this.delivery = delivery;
    }

    public String getIndex() {
        return Integer.toString(index);
    }

    public String getDelivery() {
        return delivery;
    }

    public String getBill() {
        return bill;
    }
}
