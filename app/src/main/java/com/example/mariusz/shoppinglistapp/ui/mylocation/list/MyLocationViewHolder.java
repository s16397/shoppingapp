package com.example.mariusz.shoppinglistapp.ui.mylocation.list;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.mariusz.shoppinglistapp.R;

class MyLocationViewHolder extends RecyclerView.ViewHolder {

    TextView nameTextView;
    TextView descriptionTextView;
    TextView radiusTextView;
    ImageButton deleteButton;

    public MyLocationViewHolder(View itemView) {
        super(itemView);
        this.nameTextView = itemView.findViewById(R.id.text_view_my_location_name);
        this.descriptionTextView = itemView.findViewById(R.id.text_view_my_location_description);
        this.radiusTextView = itemView.findViewById(R.id.text_view_my_location_radius);
        this.deleteButton = itemView.findViewById(R.id.button_delete_my_location);
    }
}
