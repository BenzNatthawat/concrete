package com.example.concrete_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class listOrdersItemAdapter extends  ArrayAdapter<Basket> {
    static class ViewHolder {
        TextView itemIndex;
        TextView itemName;
        TextView quantity;
        TextView price;
    }

    public listOrdersItemAdapter(Context context, ArrayList<Basket> items) {
        super(context, R.layout.listviewlistordersitem, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Basket items = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag
        if (convertView == null) {
            // If there's no view to re-use, inflate a brand new view for row
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.listviewlistordersitem, parent, false);
            viewHolder.itemIndex = (TextView) convertView.findViewById(R.id.itemIndex);
            viewHolder.itemName = (TextView) convertView.findViewById(R.id.itemName);
            viewHolder.quantity = (TextView) convertView.findViewById(R.id.quantity);
            viewHolder.price = (TextView) convertView.findViewById(R.id.price);
            // Cache the viewHolder object inside the fresh view
            convertView.setTag(viewHolder);
        } else {
            // View is being recycled, retrieve the viewHolder object from tag
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Populate the data from the data object via the viewHolder object
        // into the template view.

        viewHolder.itemIndex.setText(items.getIdItem());
        viewHolder.itemName.setText(items.getNameItem());
        viewHolder.quantity.setText(items.getNumberItem()+"");
        viewHolder.price.setText(items.getPrice()+"");
        // Return the completed view to render on screen
        return convertView;
    }
}
