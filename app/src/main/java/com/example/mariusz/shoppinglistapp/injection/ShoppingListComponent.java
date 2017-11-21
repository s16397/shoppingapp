package com.example.mariusz.shoppinglistapp.injection;

import com.example.mariusz.shoppinglistapp.viewmodel.add.AddProductViewModel;
import com.example.mariusz.shoppinglistapp.viewmodel.update.UpdateProductViewModel;
import com.example.mariusz.shoppinglistapp.viewmodel.list.ProductListViewModel;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ShoppingListModule.class})
public interface ShoppingListComponent {

    void inject(ProductListViewModel productListViewModel);

    void inject(AddProductViewModel addProductViewModel);

    void inject(UpdateProductViewModel updateProductViewModel);

    interface Injectable {
        void inject(ShoppingListComponent shoppingListComponent);
    }



}
