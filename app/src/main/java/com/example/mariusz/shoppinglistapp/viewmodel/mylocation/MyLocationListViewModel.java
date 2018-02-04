package com.example.mariusz.shoppinglistapp.viewmodel.mylocation;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.example.mariusz.shoppinglistapp.entity.MyLocation;
import com.example.mariusz.shoppinglistapp.injection.ShoppingListComponent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Mariusz on 2018-02-03.
 */

public class MyLocationListViewModel extends ViewModel implements ShoppingListComponent.Injectable {

    public static final String MY_LOCATION_DATA_SOURCE_NAME = "my_location";
    private static final String TAG = "MyLocationListViewModel";

    private final MutableLiveData<List<MyLocation>> myLocations = new MutableLiveData<>();
    private final DatabaseReference myLocationDataSource = FirebaseDatabase.getInstance()
            .getReference(MY_LOCATION_DATA_SOURCE_NAME);

    @Override
    public void inject(ShoppingListComponent shoppingListComponent) {
        shoppingListComponent.inject(this);
    }

    public LiveData<List<MyLocation>> getMyLocations() {
        if (myLocations.getValue() == null) {
            myLocationDataSource.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        List<MyLocation> fetchedMyLocations = StreamSupport
                                .stream(dataSnapshot.getChildren().spliterator(), false)
                                .map(ds -> ds.getValue(MyLocation.class))
                                .collect(Collectors.toList());
                        myLocations.postValue(fetchedMyLocations);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
        return myLocations;
    }

    public void deleteMyLocation(MyLocation myLocation) {
        Completable.fromAction(() -> myLocationDataSource.child(myLocation.getId()).removeValue())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, String.format("Delete request for MyLocation with ID: {} sent",
                                myLocation.getId()));
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, String.format("Delete request for MyLocation with ID: {} is complete",
                                myLocation.getId()));
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, String.format("Delete request for MyLocation with ID: {} failed with following message: {}",
                                myLocation.getId(), e.getMessage()));
                    }
                });
    }
}
