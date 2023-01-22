package com.example.fatortakk;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Map;

public class InsightsAdapter extends BaseAdapter {

    Context context;
    Map<String, String> categoryTotals;
    String FinalTotal;

    public InsightsAdapter(Context context, Map<String, String> categoryTotals, String FinalTotal) {
        this.context = context;
        this.categoryTotals = categoryTotals;
        this.FinalTotal = FinalTotal;
    }

    @Override
    public int getCount() {
        return categoryTotals.size();
    }

    @Override
    public Object getItem(int position) {
        return categoryTotals.keySet().toArray()[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView=layoutInflater.inflate(R.layout.insight, null);

        String category = (String) getItem(position);
        String total = categoryTotals.get(category);
        double totalValue = Double.parseDouble(total);
        double userTotal = Double.parseDouble(FinalTotal);
        double percentage = (totalValue / userTotal) * 100;
        String percentString = String.format("%.2f", percentage) + "%";

        TextView categoryName = convertView.findViewById(R.id.CategoryName);
        TextView categoryTotal = convertView.findViewById(R.id.Percent);
        //ImageView categoryImage = convertView.findViewById(R.id.CategoryImage);

        categoryName.setText(category);
        categoryTotal.setText(percentString);

        return convertView;
    }

}