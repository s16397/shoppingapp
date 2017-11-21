package com.example.mariusz.shoppinglistapp.ui.product.list;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.mariusz.shoppinglistapp.R;


class ProductViewHolder extends RecyclerView.ViewHolder {

    TextView productNameTextView;
    TextView productPriceTextView;
    TextView productQuantityTextView;
    ImageButton deleteButton;

    ProductViewHolder(View itemView) {
        super(itemView);
        this.productNameTextView = (TextView)itemView.findViewById(R.id.text_view_product_name);
        this.productPriceTextView = (TextView)itemView.findViewById(R.id.text_view_product_price);
        this.productQuantityTextView = (TextView)itemView.findViewById(R.id.text_view_product_quantity);
        this.deleteButton = (ImageButton)itemView.findViewById(R.id.button_delete);
    }
}
