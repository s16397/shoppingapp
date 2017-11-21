package com.example.mariusz.shoppinglistapp.ui.product.update;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.mariusz.shoppinglistapp.R;
import com.example.mariusz.shoppinglistapp.ShoppingListApplication;
import com.example.mariusz.shoppinglistapp.injection.ShoppingListFactory;
import com.example.mariusz.shoppinglistapp.ui.product.ProductData;
import com.example.mariusz.shoppinglistapp.viewmodel.update.UpdateProductViewModel;

public class UpdateProductFragment extends Fragment {
    private static final String TAG = "UpdateProductFragment";
    private EditText updateEditTextProductName;
    private EditText updateEditTextProductPrice;
    private EditText updateEditTextProductQuantity;
    private CheckBox checkBoxProductPurchased;
    private Button buttonUpdateProduct;
    private UpdateProductViewModel updateProductViewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_update_product, container, false);
        setupViewModel();
        setupViews(view);
        setupListeners();
        return view;
    }

    private void setupViews(View view) {
        updateEditTextProductName = view.findViewById(R.id.update_edit_text_product_name);
        updateEditTextProductPrice = view.findViewById(R.id.update_edit_text_product_price);
        updateEditTextProductQuantity = view.findViewById(R.id.update_edit_text_product_quantity);
        buttonUpdateProduct = view.findViewById(R.id.button_update_product);
        checkBoxProductPurchased = view.findViewById(R.id.check_box_is_product_purchased);
        updateEditTextProductName.setText(updateProductViewModel.getProductName());
        updateEditTextProductPrice.setText(String.valueOf(updateProductViewModel.getProductPrice()));
        updateEditTextProductQuantity.setText(String.valueOf(updateProductViewModel.getProductQuantity()));
        checkBoxProductPurchased.setChecked(updateProductViewModel.isProductPurchased());
    }

    private void setupViewModel() {
        ShoppingListApplication shoppingListApplication =
                (ShoppingListApplication)getActivity().getApplication();
        updateProductViewModel = ViewModelProviders
                .of(this, new ShoppingListFactory(shoppingListApplication))
                .get(UpdateProductViewModel.class);
        ProductData productData = getActivity().getIntent().getParcelableExtra(ProductData.KEY_NAME);
        Log.d(TAG, productData.toString());
        updateProductViewModel.setProductId(productData.getProductId());
        updateProductViewModel.setProductName(productData.getProductName());
        updateProductViewModel.setProductPrice(productData.getProductPrice());
        updateProductViewModel.setProductQuantity(productData.getProductQuantity());
        updateProductViewModel.setProductPurchased(productData.isProductPurchased());
    }

    private void setupListeners() {
        updateEditTextProductName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                updateProductViewModel.setProductName(editable.toString());
            }
        });

        updateEditTextProductPrice.addTextChangedListener(new TextWatcher() {
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
                updateProductViewModel.setProductPrice(price);
            }
        });

        updateEditTextProductQuantity.addTextChangedListener(new TextWatcher() {
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
                updateProductViewModel.setProductQuantity(quantity);
            }
        });

        checkBoxProductPurchased.setOnCheckedChangeListener((compoundButton, b) ->
                updateProductViewModel.setProductPurchased(b));

        buttonUpdateProduct.setOnClickListener(l -> {
            updateProductViewModel.updateProduct();
            getActivity().finish();
        });
    }
}
