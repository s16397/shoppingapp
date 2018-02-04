package com.example.mariusz.shoppinglistapp.ui.mylocation.list;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;

import com.example.mariusz.shoppinglistapp.entity.MyLocation;
import com.example.mariusz.shoppinglistapp.viewmodel.mylocation.MyLocationListViewModel;

public class MyLocationFragment extends Fragment {

    private static final String TAG = "ProductListFragment";
    private MyLocationAdapter myLocationAdapter;
    private MyLocationListViewModel myLocationListViewModel;

    private final RecyclerView.OnClickListener myLocationClickListener = view -> {
        MyLocation myLocation = (MyLocation)view.getTag();
    };

    private final RecyclerView.OnClickListener deleteMyLocationClickListener = view -> {
        MyLocation myLocation = (MyLocation)view.getTag();
        myLocationListViewModel.deleteMyLocation(myLocation);
    };
}
