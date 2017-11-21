package com.example.mariusz.shoppinglistapp.viewmodel.update;


import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.example.mariusz.shoppinglistapp.entity.Product;
import com.example.mariusz.shoppinglistapp.injection.ShoppingListComponent;
import com.example.mariusz.shoppinglistapp.repository.ProductRepository;

import javax.inject.Inject;

import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Data
public class UpdateProductViewModel extends ViewModel implements ShoppingListComponent.Injectable {

    private static final String TAG = "UpdateProductViewModel";

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @Inject
    ProductRepository productRepository;

    private int productId;
    private String productName;
    private double productPrice;
    private int productQuantity;
    private boolean isProductPurchased;

    public void updateProduct() {
        Product product = Product.builder()
                .id(productId)
                .name(productName)
                .price(productPrice)
                .quantity(productQuantity)
                .isPurchased(isProductPurchased)
                .build();
        productRepository.updateProduct(product)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG, "onComplete - product updated");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError - updating product", e);
                    }
                });
    }

    @Override
    public void inject(ShoppingListComponent shoppingListComponent) {
        shoppingListComponent.inject(this);
    }
}
