package com.example.fatortakk;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class SingleReceiptAdapter extends BaseAdapter {

    private ArrayList<Item> ItemList;
    private Context context;
    private int Layout;

    SingleReceiptAdapter(ArrayList<Item> ItemList, Context context, int Layout)
    {
        this.ItemList = ItemList;
        this.Layout = Layout;
        this.context = context;
    }

    @Override
    public int getCount() {
        return ItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return ItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder
    {
        TextView ItemNameTxt, ItemPriceTxt;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        SingleReceiptAdapter.ViewHolder viewHolder = new SingleReceiptAdapter.ViewHolder();
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView=layoutInflater.inflate(Layout, null);

        Item item = ItemList.get(position);

        viewHolder.ItemNameTxt = convertView.findViewById(R.id.ItemName);
        viewHolder.ItemPriceTxt = convertView.findViewById(R.id.PriceItem);

        viewHolder.ItemNameTxt.setText(item.getItemName());
        viewHolder.ItemPriceTxt.setText(String.valueOf(item.getItemPrice()));


        return convertView;
    }
}














