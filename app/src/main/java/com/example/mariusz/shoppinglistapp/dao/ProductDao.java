package com.example.mariusz.shoppinglistapp.dao;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import com.example.mariusz.shoppinglistapp.entity.Product;
import java.util.List;

@Dao
public interface ProductDao {

    @Query("SELECT * FROM " + Product.TABLE_NAME)
    LiveData<List<Product>> findAllProducts();

    @Query("SELECT * FROM " + Product.TABLE_NAME + " WHERE " + Product.ID_COLUMN_NAME + " = :id")
    LiveData<Product> findProductById(Long id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertProduct(Product product);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateProduct(Product product);

    @Delete
    void deleteProduct(Product product);

}
