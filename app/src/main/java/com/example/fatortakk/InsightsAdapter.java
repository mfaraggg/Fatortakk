package com.example.fatortakk;

import android.content.Context;
import android.content.res.Resources;
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
    String[] CategoryNames;
    int[] CategoryImages = {R.drawable.baseline_shopping_basket_24,
            R.drawable.baseline_shopping_cart_24,
    R.drawable.baseline_fastfood_24};

    public InsightsAdapter(Context context, Map<String, String> categoryTotals, String FinalTotal, String[] categories) {
        this.context = context;
        this.categoryTotals = categoryTotals;
        this.FinalTotal = FinalTotal;
        this.CategoryNames = categories;
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
        ImageView categoryImage = convertView.findViewById(R.id.CategoryImage);

        categoryName.setText(category);
        categoryTotal.setText(percentString);

        boolean isLogoFound = false;
        for (int i = 0; i < CategoryNames.length; i++) {
            if (category.equals(CategoryNames[i])) {
                // Set the logo image
                categoryImage.setImageResource(CategoryImages[i]);
                isLogoFound = true;
                break;
            }
        }
        if (!isLogoFound) {
            categoryImage.setImageResource(R.drawable.default_logo);
        }

        return convertView;
    }

}