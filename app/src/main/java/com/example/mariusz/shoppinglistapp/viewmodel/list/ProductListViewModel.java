package com.example.mariusz.shoppinglistapp.viewmodel.list;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.example.mariusz.shoppinglistapp.entity.Product;
import com.example.mariusz.shoppinglistapp.injection.ShoppingListComponent;
import com.example.mariusz.shoppinglistapp.repository.ProductRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ProductListViewModel extends ViewModel implements ShoppingListComponent.Injectable {

    private static final String TAG = "ProductListViewModel";

    @Inject
    ProductRepository productRepository;

    private LiveData<List<Product>> products = new MutableLiveData<>();

    @Override
    public void inject(ShoppingListComponent shoppingListComponent) {
        shoppingListComponent.inject(this);
        products = productRepository.getProducts();
    }

    public LiveData<List<Product>> getProducts() {
        return products;
    }

    public void deleteProduct(Product product) {
        productRepository.deleteProduct(product)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG, "onComplete - product deleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError - deleting product", e);
                    }
                });
    }
}