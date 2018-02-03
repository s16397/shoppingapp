package com.example.mariusz.shoppinglistapp.viewmodel.add;

import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.example.mariusz.shoppinglistapp.entity.Product;
import com.example.mariusz.shoppinglistapp.injection.ShoppingListComponent;
import com.example.mariusz.shoppinglistapp.repository.ProductRepository;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.example.mariusz.shoppinglistapp.viewmodel.list.ProductListViewModel.PRODUCT_LIST;

@NoArgsConstructor
@Data
public class AddProductViewModel extends ViewModel implements ShoppingListComponent.Injectable {

    private static final String TAG = "AddProductViewModel";

//    @Getter(AccessLevel.NONE)
//    @Setter(AccessLevel.NONE)
//    @Inject
//    ProductRepository productRepository;
    private String productName;
    private double productPrice;
    private int productQuantity;
    //TODO handle purchasing product
//    private boolean isProductPurchased;

    private DatabaseReference productDatabase = FirebaseDatabase
            .getInstance()
            .getReference(PRODUCT_LIST);



    public void addProduct() {
        Product product = Product.builder()
                .name(productName)
                .price(productPrice)
                .quantity(productQuantity)
                .isPurchased(false)
                .build();

        Completable.fromAction(() -> insertProduct(product))
//        productRepository.addProduct(product)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG, "onComplete - successfully added product");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG,"onError - add: ", e);
                    }
                });
    }

    @Override
    public void inject(ShoppingListComponent shoppingListComponent) {
        shoppingListComponent.inject(this);
    }

    private void insertProduct(Product product) {
        String id = productDatabase.push().getKey();
        product.setId(id);
        productDatabase.child(id).setValue(product);
    }
}
