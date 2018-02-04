package com.example.mariusz.shoppinglistapp.ui.mylocation.list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mariusz.shoppinglistapp.R;
import com.example.mariusz.shoppinglistapp.entity.MyLocation;

import java.util.List;


class MyLocationAdapter extends RecyclerView.Adapter<MyLocationViewHolder> {

    private final Context context;
    private final View.OnClickListener viewOnClickListener;
    private final View.OnClickListener deleteOnClickListener;
    private List<MyLocation> myLocations;

    MyLocationAdapter(List<MyLocation> myLocations,
                      Context context,
                      View.OnClickListener viewOnClickListener,
                      View.OnClickListener deleteOnClickListener) {
        this.myLocations = myLocations;
        this.context = context;
        this.viewOnClickListener = viewOnClickListener;
        this.deleteOnClickListener = deleteOnClickListener;
    }

    @Override
    public MyLocationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_my_location, parent, false);
        return new MyLocationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyLocationViewHolder holder, int position) {
        MyLocation myLocation = myLocations.get(position);
        holder.nameTextView.setText(myLocation.getName());
        holder.descriptionTextView.setText(myLocation.getDescription());
        holder.radiusTextView.setText(myLocation.getRadius());
        holder.itemView.setTag(myLocation);
        holder.itemView.setOnClickListener(viewOnClickListener);
        holder.deleteButton.setTag(myLocation);
        holder.deleteButton.setOnClickListener(deleteOnClickListener);
    }

    @Override
    public int getItemCount() {
        return myLocations.size();
    }

    void setMyLocations(List<MyLocation> myLocations) {
        this.myLocations = myLocations;
        notifyDataSetChanged();
    }
}
