package com.example.concrete_app;

public class Orders {
    public int index;
    public String bill;
    public String delivery;
    public double sumPrice;
    public String status;

    public Orders(int index, String bill, String delivery, double sumPrice, String status) {
        this.index = index;
        this.bill = bill;
        this.delivery = delivery;
        this.sumPrice = sumPrice;
        this.status = status;
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
    public String getStatus() {
        return status;
    }
    public double getSumPrice() { return sumPrice; }
}
