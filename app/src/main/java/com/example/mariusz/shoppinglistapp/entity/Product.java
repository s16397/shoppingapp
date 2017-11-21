package com.example.mariusz.shoppinglistapp.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity(tableName = Product.TABLE_NAME)
public class Product {
    public static final String TABLE_NAME = "PRODUCT";
    public static final String ID_COLUMN_NAME = "ID";
    public static final String NAME_COLUMN_NAME = "NAME";
    public static final String PRICE_COLUMN_NAME = "PRICE";
    public static final String QUANTITY_COLUMN_NAME = "QUANTITY";
    public static final String IS_PURCHASED_COLUMN_NAME = "IS_PURCHASED";

    @ColumnInfo(name = ID_COLUMN_NAME)
    @PrimaryKey(autoGenerate = true)
    private Integer id;
    @ColumnInfo(name = NAME_COLUMN_NAME)
    private String name;
    @ColumnInfo(name = PRICE_COLUMN_NAME)
    private Double price;
    @ColumnInfo(name = QUANTITY_COLUMN_NAME)
    private int quantity;
    @ColumnInfo(name = IS_PURCHASED_COLUMN_NAME)
    private boolean isPurchased;
}
