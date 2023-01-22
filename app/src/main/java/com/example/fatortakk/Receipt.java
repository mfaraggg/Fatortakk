package com.example.fatortakk;
import java.io.Serializable;
import java.util.List;

public class Receipt {
    private String name;
    private String date;
    private float total;
    private String time;
    private int id;

    public Receipt(String name, String date, String time, float total, int receiptID)
    {
        this.name = name;
        this.date = date;
        this.time = time;
        this.total = total;
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDate()
    {
        return date;
    }

    public String getTime()
    {
        return time;
    }

    public float getTotal() {
        return total;
    }

    public int getReceiptID(){return id;}
}

