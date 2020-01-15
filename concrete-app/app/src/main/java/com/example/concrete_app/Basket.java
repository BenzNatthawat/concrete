package com.example.concrete_app;

public class Basket {
    String idItem;
    String nameItem;
    int numberItem;
    Float price;

     public Basket(String idItem, String nameItem, int numberItem, Float price) {
         this.idItem = idItem;
         this.nameItem = nameItem;
         this.numberItem = numberItem;
         this.price = price;
     }
    public Float getPrice() { return price; }
    public int getNumberItem() { return numberItem; }
    public String getIdItem() { return idItem; }
    public String getNameItem() { return nameItem; }
}
