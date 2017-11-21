package com.example.mariusz.shoppinglistapp.ui.product.list;


import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mariusz.shoppinglistapp.R;
import com.example.mariusz.shoppinglistapp.ShoppingListApplication;
import com.example.mariusz.shoppinglistapp.entity.Product;
import com.example.mariusz.shoppinglistapp.injection.ShoppingListFactory;
import com.example.mariusz.shoppinglistapp.ui.product.ProductData;
import com.example.mariusz.shoppinglistapp.ui.product.add.AddProductActivity;
import com.example.mariusz.shoppinglistapp.ui.product.update.UpdateProductActivity;
import com.example.mariusz.shoppinglistapp.viewmodel.list.ProductListViewModel;

import java.util.ArrayList;

public class ProductListFragment extends Fragment {

    private static final String TAG = "ProductListFragment";
    private ProductAdapter productAdapter;
    private ProductListViewModel productListViewModel;

    private View.OnClickListener deleteClickListener = view -> {
        Product product = (Product) view.getTag();
        productListViewModel.deleteProduct(product);
    };

    private View.OnClickListener productClickListener = view -> {
        Product product = (Product) view.getTag();
        ProductData productData = ProductData.fromEntity(product);
        Log.d(TAG, product.toString());
        Intent updateProductIntent = new Intent(getContext(), UpdateProductActivity.class);
        updateProductIntent.putExtra(ProductData.KEY_NAME, productData);
        startActivity(updateProductIntent);
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list_products, container,false);
        setupRecyclerView(view);
        FloatingActionButton floatingActionButton = view.findViewById(R.id.fab_add);
        floatingActionButton.setOnClickListener(
                view1 -> startActivity(new Intent(getContext(), AddProductActivity.class)));

        ShoppingListApplication shoppingListApplication =
                (ShoppingListApplication) getActivity().getApplication();
        productListViewModel = ViewModelProviders
                .of(this, new ShoppingListFactory(shoppingListApplication))
                .get(ProductListViewModel.class);

        productListViewModel.getProducts().observe(this, products -> {
            Log.d(TAG, "Products changed: " + products);
            productAdapter.setProducts(products);
        });

        return view;
    }

    private void setupRecyclerView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_list_products);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        productAdapter = new ProductAdapter(new ArrayList(), getContext(), deleteClickListener,
                productClickListener);
        recyclerView.setAdapter(productAdapter);
        final DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(
                recyclerView.getContext(), linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
    }
}
