package com.example.fatortakk;
import java.io.Serializable;
import java.util.List;

public class Receipt {
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

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public String getDate()
    {
        return date;
    }

    public void setTime(String time)
    {
        this.time=time;
    }

    public String getTime()
    {
        return time;
    }

    public void setTotal(float total)
    {
        this.total=total;
    }

    public float getTotal() {
        return total;
    }
}

