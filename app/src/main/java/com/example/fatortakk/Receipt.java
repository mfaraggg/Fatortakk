package com.example.fatortakk;
import java.io.Serializable;
import java.util.List;

public class Receipt implements Serializable{
    private String name;
    private String date;
    private float total;
    private String time;

    public Receipt(String name, String date, String time, float total)
    {
        this.name = name;
        this.date = date;
        this.time = time;
        this.total = total;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public float getTotal() {
        return total;
    }
}

