package com.example.concrete_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class OrdersAdapter extends ArrayAdapter<Orders> {
    static class ViewHolder {
        TextView orderId;
        TextView billOrder;
        TextView orderDelivery;
        TextView sumPrice;
        TextView orderStatus;
    }

    public OrdersAdapter(Context context, ArrayList<Orders> orders) {
        super(context, R.layout.listvieworder, orders);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Orders orders = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag
        if (convertView == null) {
            // If there's no view to re-use, inflate a brand new view for row
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.listvieworder, parent, false);
            viewHolder.orderId = (TextView) convertView.findViewById(R.id.orderId);
            viewHolder.billOrder = (TextView) convertView.findViewById(R.id.billOrder);
            viewHolder.orderDelivery = (TextView) convertView.findViewById(R.id.orderDelivery);
            viewHolder.sumPrice = (TextView) convertView.findViewById(R.id.sumPrice);
            viewHolder.orderStatus = (TextView) convertView.findViewById(R.id.orderStatus);
            
            // Cache the viewHolder object inside the fresh view
            convertView.setTag(viewHolder);
        } else {
            // View is being recycled, retrieve the viewHolder object from tag
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Populate the data from the data object via the viewHolder object
        // into the template view.

        viewHolder.orderId.setText(orders.getIndex());
        viewHolder.billOrder.setText(orders.getBill());
        viewHolder.orderDelivery.setText(orders.getDelivery());
        viewHolder.orderStatus.setText(orders.getStatus());
        viewHolder.sumPrice.setText(""+orders.getSumPrice());
        
        // Return the completed view to render on screen
        return convertView;
    }
}
