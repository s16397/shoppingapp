package com.example.mariusz.shoppinglistapp;

import android.app.Application;
import android.support.annotation.NonNull;

import com.example.mariusz.shoppinglistapp.injection.DaggerShoppingListComponent;
import com.example.mariusz.shoppinglistapp.injection.ShoppingListComponent;
import com.example.mariusz.shoppinglistapp.injection.ShoppingListModule;

public class ShoppingListApplication extends Application {

    private final ShoppingListComponent shoppingListComponent = createShoppingListComponent();

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @NonNull
    private ShoppingListComponent createShoppingListComponent() {
        return DaggerShoppingListComponent.builder()
                .shoppingListModule(new ShoppingListModule(this))
                .build();
    }

    public ShoppingListComponent getShoppingListComponent() {
        return shoppingListComponent;
    }

}
