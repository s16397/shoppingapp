package com.example.mariusz.shoppinglistapp.repository;


import android.arch.lifecycle.LiveData;

import com.example.mariusz.shoppinglistapp.entity.Product;

import java.util.List;
import java.util.Optional;

import io.reactivex.Completable;

public interface ProductRepository {

    Completable addProduct(Product product);

    LiveData<Product> getProductById(Long productId);

    Completable updateProduct(Product product);

    LiveData<List<Product>> getProducts();

    Completable deleteProduct(Product product);
}
