package com.example.concrete_app;

public class Orders {
    public int index;
    public String bill;
    public String delivery;
    public double sumPrice;

    public Orders(int index, String bill, String delivery, double sumPrice) {
        this.index = index;
        this.bill = bill;
        this.delivery = delivery;
        this.sumPrice = sumPrice;

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
    public double getSumPrice() { return sumPrice; }
}
