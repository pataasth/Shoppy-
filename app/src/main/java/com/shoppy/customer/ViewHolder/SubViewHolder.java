package com.shoppy.customer.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.shoppy.customer.Interface.ItemClickListener;
import com.shoppy.customer.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SubViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView sub_Name;
    public ImageView sub_image;

    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }



    public SubViewHolder(@NonNull View itemView) {
        super(itemView);
        sub_Name = itemView.findViewById(R.id.sub_name);
        sub_image = itemView.findViewById(R.id.sub_image);

        itemView.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view, getAdapterPosition(), false);

    }
}
