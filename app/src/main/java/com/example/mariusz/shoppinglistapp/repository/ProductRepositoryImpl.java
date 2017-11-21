package com.example.mariusz.shoppinglistapp.repository;

import android.arch.lifecycle.LiveData;

import com.example.mariusz.shoppinglistapp.db.ProductDatabase;
import com.example.mariusz.shoppinglistapp.entity.Product;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import io.reactivex.Completable;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

    @Inject
    ProductDatabase productDatabase;

    @Override
    public Completable addProduct(final Product product) {
        return Completable.fromAction(() -> productDatabase.productDao().insertProduct(product));
    }

    @Override
    public LiveData<Product> getProductById(final Long productId) {
        return productDatabase.productDao().findProductById(productId);
    }

    @Override
    public Completable updateProduct(Product product) {
        return Completable.fromAction(() -> productDatabase.productDao().updateProduct(product));
    }

    @Override
    public LiveData<List<Product>> getProducts() {
        return productDatabase.productDao().findAllProducts();
    }

    @Override
    public Completable deleteProduct(Product product) {
        return Completable.fromAction(() -> productDatabase.productDao().deleteProduct(product));
    }
}
