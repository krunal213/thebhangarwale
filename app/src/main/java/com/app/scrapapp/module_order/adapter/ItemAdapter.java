package com.app.scrapapp.module_order.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.app.scrapapp.R;
import com.app.scrapapp.entity.Item;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends ArrayAdapter {

    public ItemAdapter(@NonNull Context context,@NonNull List<Item> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position,View convertView,ViewGroup parent) {
        String item = (String) getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_item, parent, false);
        }

        TextView textViewItemName = convertView.findViewById(R.id.textViewItemName);
        textViewItemName.setText(item);


        if (position == getCount() - 1) {
            convertView.findViewById(R.id.divider).setVisibility(View.GONE);
        } else {
            convertView.findViewById(R.id.divider).setVisibility(View.VISIBLE);
        }


        return convertView;
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        Item item = (Item) super.getItem(position);
        return item.getItemName()+" ("+item.getItemOriginalPrice()+")";
    }

    /*@Override
    public View getDropDownView(int position,View convertView,ViewGroup parent) {
        Item item = (Item) getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_item, parent, false);
        }

        TextView textViewItemName = convertView.findViewById(R.id.textViewItemName);
        textViewItemName.setText("Hello");

        TextView textViewItemOriginalPrice = convertView.findViewById(R.id.textViewItemOriginalPrice);
        textViewItemOriginalPrice.setText("Hi");


        *//*if (position == getCount() - 1) {
            convertView.findViewById(R.id.divider).setVisibility(View.GONE);
        } else {
            convertView.findViewById(R.id.divider).setVisibility(View.VISIBLE);
        }
*//*

        System.out.println("Item : ");


        return convertView;
    }*/

}
