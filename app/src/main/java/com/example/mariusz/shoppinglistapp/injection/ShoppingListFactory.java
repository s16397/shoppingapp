package com.example.mariusz.shoppinglistapp.injection;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.mariusz.shoppinglistapp.ShoppingListApplication;

public class ShoppingListFactory extends ViewModelProvider.NewInstanceFactory {

    private ShoppingListApplication application;

    public ShoppingListFactory(ShoppingListApplication application) {
        this.application = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        T t = super.create(modelClass);
        if (t instanceof ShoppingListComponent.Injectable) {
            ((ShoppingListComponent.Injectable)t).inject(application.getShoppingListComponent());
        }
        return t;
    }
}
