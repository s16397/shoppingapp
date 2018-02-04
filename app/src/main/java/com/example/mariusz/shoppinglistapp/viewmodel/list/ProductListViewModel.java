package com.example.mariusz.shoppinglistapp.viewmodel.list;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.example.mariusz.shoppinglistapp.entity.Product;
import com.example.mariusz.shoppinglistapp.injection.ShoppingListComponent;
import com.example.mariusz.shoppinglistapp.repository.ProductRepository;
import com.example.mariusz.shoppinglistapp.repository.ProductRepositoryImpl;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ProductListViewModel extends ViewModel implements ShoppingListComponent.Injectable {

    private static final String TAG = "ProductListViewModel";
    public static final String PRODUCT_LIST = "product";

//    @Inject
//    ProductRepository productRepository;

    private MutableLiveData<List<Product>> products = new MutableLiveData<>();
    private DatabaseReference productDatabase = FirebaseDatabase.getInstance()
            .getReference(PRODUCT_LIST);

    @Override
    public void inject(ShoppingListComponent shoppingListComponent) {
        shoppingListComponent.inject(this);
        //products = productRepository.getProducts();
    }

    public LiveData<List<Product>> getProducts() {
        if (products.getValue() == null) {
            productDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        List<Product> fetchedProducts = new ArrayList<>();
                        for(DataSnapshot productSnapshot : dataSnapshot.getChildren()) {
                            Product product = productSnapshot.getValue(Product.class);
                            fetchedProducts.add(product);
                        }
                        products.postValue(fetchedProducts);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
        return products;
    }

    public void deleteProduct(Product product) {
         Completable.fromAction(() -> productDatabase.child(product.getId()).removeValue())
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