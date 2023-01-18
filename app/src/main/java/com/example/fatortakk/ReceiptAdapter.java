package com.example.fatortakk;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class ReceiptAdapter extends BaseAdapter {
    private ArrayList<Receipt> ReceiptList;
    private Context context;
    private int Layout;

    public ReceiptAdapter(ArrayList<Receipt> ReceiptList, Context context, int Layout)
    {
        this.ReceiptList = ReceiptList;
        this.context = context;
        this.Layout = Layout;
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
        TextView StoreTxt, DateTxt, TimeTxt;
    }
    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView=layoutInflater.inflate(Layout, null);
        viewHolder.StoreTxt = convertView.findViewById(R.id.Store);
        viewHolder.DateTxt = convertView.findViewById(R.id.Date);
        viewHolder.TimeTxt = convertView.findViewById(R.id.Timing);

        Receipt receipt = ReceiptList.get(pos);
        viewHolder.StoreTxt.setText(receipt.getName());
        viewHolder.DateTxt.setText(receipt.getDate());
        viewHolder.TimeTxt.setText(receipt.getTime());

        return convertView;
    }
}





