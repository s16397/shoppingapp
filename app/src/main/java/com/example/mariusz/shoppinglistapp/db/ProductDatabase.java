package com.example.mariusz.shoppinglistapp.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import com.example.mariusz.shoppinglistapp.dao.ProductDao;
import com.example.mariusz.shoppinglistapp.entity.Product;

@Database(entities = {Product.class}, version = 1)
public abstract class ProductDatabase extends RoomDatabase {

    public abstract ProductDao productDao();
}
