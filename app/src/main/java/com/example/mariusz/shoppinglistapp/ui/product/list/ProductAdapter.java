package com.example.mariusz.shoppinglistapp.ui.product.list;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mariusz.shoppinglistapp.R;
import com.example.mariusz.shoppinglistapp.entity.Product;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductViewHolder> {

    private List<Product> products;
    private final Context context;
    private View.OnClickListener deleteClickListener;
    private View.OnClickListener viewClickListener;

    ProductAdapter(List<Product> products,
                   Context context,
                   View.OnClickListener deleteClickListner,
                   View.OnClickListener viewClickListener) {
        this.products = products;
        this.context = context;
        this.deleteClickListener = deleteClickListner;
        this.viewClickListener = viewClickListener;
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        Product product = products.get(position);
        holder.productNameTextView.setText(product.getName());
        holder.productPriceTextView.setText(String.valueOf(product.getPrice()));
        holder.productQuantityTextView.setText(String.valueOf(product.getQuantity()));
        holder.itemView.setTag(product);
        holder.deleteButton.setTag(product);
        holder.deleteButton.setOnClickListener(deleteClickListener);
        holder.itemView.setOnClickListener(viewClickListener);
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                                  .inflate(R.layout.list_item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    void setProducts(List<Product> products) {
        this.products = products;
        notifyDataSetChanged();
    }
}
