package com.example.mariusz.shoppinglistapp.ui.product;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.mariusz.shoppinglistapp.entity.Product;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Data
public class ProductData implements Parcelable {

    public static final String KEY_NAME = "productData";

    public static ProductData fromEntity(Product product) {
        return new ProductData(product.getId(), product.getName(),
                product.getPrice(), product.getQuantity(), product.isPurchased());
    }

    public static final Parcelable.Creator<ProductData> CREATOR = new
            Parcelable.Creator<ProductData>() {


                @Override
                public ProductData createFromParcel(Parcel parcel) {
                    return new ProductData(parcel);
                }

                @Override
                public ProductData[] newArray(int i) {
                    return new ProductData[0];
                }
            };

    private final Integer productId;
    private final String productName;
    private final Double productPrice;
    private final int productQuantity;
    private final boolean isProductPurchased;

    public ProductData(Parcel in) {
        this.productId = in.readInt();
        this.productName = in.readString();
        this.productPrice = in.readDouble();
        this.productQuantity = in.readInt();
        this.isProductPurchased = in.readByte() != 0;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.productId);
        parcel.writeString(this.productName);
        parcel.writeDouble(this.productPrice);
        parcel.writeInt(this.productQuantity);
        parcel.writeByte((byte)(this.isProductPurchased ? 1 : 0));
    }
}
