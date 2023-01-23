package com.example.fatortakk;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class RewardsAdapter extends BaseAdapter
{
    Context context;
    Map<String, String> storeBonus;
    String[] storeNames;
    int[] logoImages;

    public RewardsAdapter(Context context, int[] logoImages,
                          String[] storeNames, HashMap<String, String>storeBonus)
    {
        this.context = context;
        this.logoImages = logoImages;
        this.storeNames = storeNames;
        this.storeBonus = storeBonus;
    }

    @Override
    public int getCount() {
        return storeBonus.size();
    }

    @Override
    public Object getItem(int position) {
        return storeBonus.keySet().toArray()[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView=layoutInflater.inflate(R.layout.singlereward, null);

        String storeName = (String) getItem(position);
        String storePoints =  storeBonus.get(storeName);

        TextView store = convertView.findViewById(R.id.StoreName);
        TextView storept = convertView.findViewById(R.id.Points);
        ImageView categoryImage = convertView.findViewById(R.id.StoreImage);

        store.setText(storeName);
        storept.setText(storePoints);

        boolean isLogoFound = false;
        for (int i = 0; i < storeNames.length; i++) {
            if (storeName.equals(storeNames[i])) {
                // Set the logo image
                categoryImage.setImageResource(logoImages[i]);
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
