package com.example.mariusz.shoppinglistapp.injection;


import android.content.Context;

import com.example.mariusz.shoppinglistapp.ShoppingListApplication;
import com.example.mariusz.shoppinglistapp.repository.ProductRepository;
import com.example.mariusz.shoppinglistapp.repository.ProductRepositoryImpl;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Module
public class ShoppingListModule {

    private final ShoppingListApplication shoppingListApplication;

    @Provides
    Context applicationContext() {
        return shoppingListApplication;
    }

//    @Provides
//    @Singleton
//    ProductRepository provideProductRepository() {
//        return new ProductRepositoryImpl();
//    }
//
//    @Provides
//    @Singleton
//    DatabaseReference provideFirebasDatabase() {
//
//        return FirebaseDatabase
//                .getInstance()
//                .getReference(ProductRepositoryImpl.ROOT_ELEMENT);
//    }

//    @Provides
//    @Singleton
//    ProductDatabase provideProductDatabase(Context context) {
//        return Room.databaseBuilder(context.getApplicationContext(), ProductDatabase.class,
//                "product_db").build();
//    }
}
