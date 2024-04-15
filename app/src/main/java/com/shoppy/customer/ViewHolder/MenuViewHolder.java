package com.shoppy.customer.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.shoppy.customer.Interface.ItemClickListener;
import com.shoppy.customer.R;

import androidx.recyclerview.widget.RecyclerView;

public class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView txtMenuName;
    public ImageView imageView;

    private ItemClickListener itemClickListener;

    public MenuViewHolder(View itemView){
        super(itemView);

        txtMenuName = itemView.findViewById(R.id.menu_name);
        imageView = itemView.findViewById(R.id.menu_image);

        itemView.setOnClickListener(this);

    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view)
    {

        itemClickListener.onClick(view, getAdapterPosition(), false);
    }
}

