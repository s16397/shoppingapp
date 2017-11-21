package com.example.mariusz.shoppinglistapp.injection;


import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.mariusz.shoppinglistapp.ShoppingListApplication;
import com.example.mariusz.shoppinglistapp.db.ProductDatabase;
import com.example.mariusz.shoppinglistapp.repository.ProductRepository;
import com.example.mariusz.shoppinglistapp.repository.ProductRepositoryImpl;

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

    @Provides
    @Singleton
    ProductRepository provideProductRepository(ProductDatabase productDatabase) {
        return new ProductRepositoryImpl(productDatabase);
    }

    @Provides
    @Singleton
    ProductDatabase provideProductDatabase(Context context) {
        return Room.databaseBuilder(context.getApplicationContext(), ProductDatabase.class,
                "product_db").build();
    }
}
