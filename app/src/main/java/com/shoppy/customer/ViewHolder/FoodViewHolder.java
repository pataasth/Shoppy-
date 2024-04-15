package com.shoppy.customer.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.shoppy.customer.Interface.ItemClickListener;
import com.shoppy.customer.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class FoodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView food_Name;
    public ImageView food_image;

    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public FoodViewHolder(@NonNull View itemView) {
        super(itemView);

        food_Name = itemView.findViewById(R.id.food_name);
        food_image = itemView.findViewById(R.id.food_image);

        itemView.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view, getAdapterPosition(), false);


    }


}
