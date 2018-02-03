package com.example.mariusz.shoppinglistapp.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
//@Entity(tableName = Product.TABLE_NAME)
public class Product {
//    public static final String TABLE_NAME = "PRODUCT";
//    public static final String ID_COLUMN_NAME = "ID";
//    public static final String NAME_COLUMN_NAME = "NAME";
//    public static final String PRICE_COLUMN_NAME = "PRICE";
//    public static final String QUANTITY_COLUMN_NAME = "QUANTITY";
//    public static final String IS_PURCHASED_COLUMN_NAME = "IS_PURCHASED";

//    @ColumnInfo(name = ID_COLUMN_NAME)
//    @PrimaryKey(autoGenerate = true)
    private String id;
//    @ColumnInfo(name = NAME_COLUMN_NAME)
    private String name;
//    @ColumnInfo(name = PRICE_COLUMN_NAME)
    private Double price;
//    @ColumnInfo(name = QUANTITY_COLUMN_NAME)
    private int quantity;
//    @ColumnInfo(name = IS_PURCHASED_COLUMN_NAME)
    private boolean isPurchased;
}
