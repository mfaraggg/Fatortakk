package com.example.fatortakk;


import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;



public class ReceiptAdapter extends BaseAdapter {
    private ArrayList<Receipt> ReceiptList;
    private Context context;
    private int Layout;
    String[] StoreNames;
    int[] logoImages;

    public ReceiptAdapter(ArrayList<Receipt> ReceiptList, Context context, int Layout, String[] StoreNames, int[] logoImages)
    {
        this.ReceiptList = ReceiptList;
        this.context = context;
        this.Layout = Layout;
        this.StoreNames = StoreNames;
        this.logoImages = logoImages;
    }

    @Override
    public int getCount() {
        return ReceiptList.size();
    }

    @Override
    public Object getItem(int pos) {
        return ReceiptList.get(pos);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    private class ViewHolder
    {
        TextView StoreTxt, DateTxt, TimeTxt, TotalTxt;
        ImageView Logo;
    }
    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView=layoutInflater.inflate(Layout, null);
        viewHolder.StoreTxt = convertView.findViewById(R.id.StoreTxt);
        viewHolder.DateTxt = convertView.findViewById(R.id.DateTxt);
        viewHolder.TimeTxt = convertView.findViewById(R.id.TimeTxt);
        viewHolder.TotalTxt = convertView.findViewById(R.id.TotalTxt);
        viewHolder.Logo = convertView.findViewById(R.id.Logos);

        Receipt receipt = ReceiptList.get(pos);
        viewHolder.StoreTxt.setText(receipt.getName());
        viewHolder.DateTxt.setText(receipt.getDate());
        viewHolder.TimeTxt.setText(receipt.getTime());
        viewHolder.TotalTxt.setText(String.valueOf(receipt.getTotal()));

        boolean isLogoFound = false;
        for (int i = 0; i < StoreNames.length; i++) {
            if (receipt.getName().equals(StoreNames[i])) {
                // Set the logo image
                viewHolder.Logo.setImageResource(logoImages[i]);
                isLogoFound = true;
                break;
            }
        }
        if (!isLogoFound) {
            viewHolder.Logo.setImageResource(R.drawable.default_logo);
        }


        return convertView;
    }
}





