package com.example.concrete_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ItemsAdapter extends ArrayAdapter<Items> {
    static class ViewHolder {
        TextView itemIndex;
        TextView itemCube;
        TextView itemPrice;
        TextView itemInstallment;
    }

    public ItemsAdapter(Context context, ArrayList<Items> items) {
        super(context, R.layout.listviewitem, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Items items = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag
        if (convertView == null) {
            // If there's no view to re-use, inflate a brand new view for row
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.listviewitem, parent, false);
            viewHolder.itemIndex = (TextView) convertView.findViewById(R.id.itemIndex);
            viewHolder.itemCube = (TextView) convertView.findViewById(R.id.itemCube);
            viewHolder.itemPrice = (TextView) convertView.findViewById(R.id.itemPrice);
            viewHolder.itemInstallment = (TextView) convertView.findViewById(R.id.itemInstallment);
            // Cache the viewHolder object inside the fresh view
            convertView.setTag(viewHolder);
        } else {
            // View is being recycled, retrieve the viewHolder object from tag
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Populate the data from the data object via the viewHolder object
        // into the template view.

        viewHolder.itemIndex.setText(items.getIndex());
        viewHolder.itemCube.setText(items.getCube());
        viewHolder.itemPrice.setText(items.getPrice());
        viewHolder.itemInstallment.setText(items.getInstallment());
        // Return the completed view to render on screen
        return convertView;
    }
}
