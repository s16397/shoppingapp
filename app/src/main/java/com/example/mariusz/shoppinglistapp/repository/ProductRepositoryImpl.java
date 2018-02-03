package com.example.mariusz.shoppinglistapp.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.example.mariusz.shoppinglistapp.entity.Product;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

    public static final String ROOT_ELEMENT = "product";

    @Inject
    DatabaseReference productDatabase;

    @Override
    public Completable addProduct(final Product product) {
        return Completable.fromAction(() -> insertProduct(product));
    }

    @Override
    public LiveData<Product> getProductById(final Long productId) {
        return new MutableLiveData<>();
    }

    @Override
    public Completable updateProduct(Product product) {
        return Completable.fromAction(() -> updateProductEntry(product));
    }

    @Override
    public LiveData<List<Product>> getProducts() {
//        return productDatabase.productDao().findAllProducts();

        MutableLiveData<List<Product>> productsLiveData = new MutableLiveData<>();
        productDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    List<Product> products = new ArrayList<>();
                    for(DataSnapshot productSnapshot : dataSnapshot.getChildren()) {
                        Product product = productSnapshot.getValue(Product.class);
                        products.add(product);
                    }
                    productsLiveData.postValue(products);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return productsLiveData;
    }

    @Override
    public Completable deleteProduct(Product product) {
        return Completable.fromAction(() -> removeProduct(product));
    }

    private void insertProduct(Product product) {
        String id = productDatabase.push().getKey();
        product.setId(id);
        productDatabase.child(id).setValue(product);
    }

    private void removeProduct(Product product) {
        productDatabase.child(product.getId()).removeValue();
    }

    private void updateProductEntry(Product product) {
        productDatabase.child(product.getId()).setValue(product);
    }
}
