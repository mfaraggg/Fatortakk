package com.example.fatortakk;

public class Item {
    private String item_name;
    private Float item_price;
    private int item_quantity;
    private int user;
    private int receiptID;

    public Item(String item_name, Float item_price, int item_quantity, int user, int receiptID)
    {
        this.item_name = item_name;
        this.item_price = item_price;
        this.item_quantity = item_quantity;
        this.user = user;
        this.receiptID = receiptID;
    }

    public String getItemName(){return item_name;}
    public Float getItemPrice(){return item_price;}
    public int getItemQuantity(){return item_quantity;}
    public int getUserID(){return user;}
    public int getReceiptID(){return receiptID;}

}
