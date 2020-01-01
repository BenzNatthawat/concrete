package com.example.concrete_app;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


public class BottomDialog extends BottomSheetDialogFragment {

    TextView nameItem, priceTotal;
    Button buttonAddId, buttonReduceId, sendId;
    EditText numberId;
    int number = 1;
    String price;
    String cube;
    String id;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_sheet_layout, container, false);

        buttonAddId = v.findViewById(R.id.buttonAddId);
        buttonReduceId = v.findViewById(R.id.buttonReduceId);
        sendId = v.findViewById(R.id.sendId);
        nameItem = (TextView)  v.findViewById(R.id.itemsId);
        priceTotal = (TextView)  v.findViewById(R.id.priceTotal);
        numberId = (EditText) v.findViewById(R.id.numberId);

        Bundle bundle = getArguments();
        cube = bundle.getString("cube");
        price = bundle.getString("price");
        id = bundle.getString("id");

        nameItem.setText(cube + " " + price);
        priceTotal.setText(price);
        numberId.setText(""+number);

        buttonAddId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number = Integer.parseInt(numberId.getText().toString());
                number = number + 1;
                numberId.setText(""+number);
                priceTotal.setText(""+number * Float.parseFloat(price));
            }
        });
        buttonReduceId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(number > 1 ) {
                    number = Integer.parseInt(numberId.getText().toString());
                    number = number - 1;
                    numberId.setText(""+number);
                    priceTotal.setText(""+number * Float.parseFloat(price));
                }
            }
        });
        sendId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedData sharedData = SharedData.getInstance();
                Basket order = new Basket(id, cube, number, Float.parseFloat(price) );
                sharedData.addBaskets(order);

                ((ItemsActivity) getActivity()).setResultFromFragment();
                dismiss();
            }
        });
        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
}