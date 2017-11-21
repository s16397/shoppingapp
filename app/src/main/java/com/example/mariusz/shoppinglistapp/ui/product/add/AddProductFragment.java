package com.example.mariusz.shoppinglistapp.ui.product.add;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.mariusz.shoppinglistapp.R;
import com.example.mariusz.shoppinglistapp.ShoppingListApplication;
import com.example.mariusz.shoppinglistapp.injection.ShoppingListFactory;
import com.example.mariusz.shoppinglistapp.viewmodel.add.AddProductViewModel;

public class AddProductFragment extends Fragment {

    private EditText editTextProductName;
    private EditText editTextProductPrice;
    private EditText editTextProductQuantity;
    private Button buttonAddProduct;
    private AddProductViewModel addProductViewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_product, container, false);
        setupViews(view);
        setupListeners();
        setupViewModel();
        return view;
    }

    private void setupViews(View view) {
        buttonAddProduct =  view.findViewById(R.id.button_add);
        editTextProductName = view.findViewById(R.id.edit_text_product_name);
        editTextProductPrice = view.findViewById(R.id.edit_text_product_price);
        editTextProductQuantity = view.findViewById(R.id.edit_text_product_quantity);
    }

    private void setupViewModel() {
        ShoppingListApplication shoppingListApplication =
                (ShoppingListApplication)getActivity().getApplication();
        addProductViewModel = ViewModelProviders
                .of(this, new ShoppingListFactory(shoppingListApplication))
                .get(AddProductViewModel.class);
        editTextProductName.setText(addProductViewModel.getProductName());
        editTextProductPrice.setText(String.valueOf(addProductViewModel.getProductPrice()));
        editTextProductQuantity.setText(String.valueOf(addProductViewModel.getProductQuantity()));
    }

    private void setupListeners() {
        editTextProductName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                addProductViewModel.setProductName(editable.toString());
            }
        });

        editTextProductPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                double price = 0.0;
                try {
                    price = Double.parseDouble(editable.toString());
                } catch (NumberFormatException nfe) {
                }
                addProductViewModel.setProductPrice(price);
            }
        });

        editTextProductQuantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                int quantity = 0;
                try {
                    quantity = Integer.parseInt(editable.toString());
                } catch (NumberFormatException nfe) {

                }
                addProductViewModel.setProductQuantity(quantity);
            }
        });

        buttonAddProduct.setOnClickListener(l -> {
            addProductViewModel.addProduct();
            getActivity().finish();
        });
    }
}
