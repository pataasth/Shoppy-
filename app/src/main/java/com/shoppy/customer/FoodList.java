package com.shoppy.customer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.shoppy.customer.Interface.ItemClickListener;
import com.shoppy.customer.Model.Food;
import com.shoppy.customer.ViewHolder.FoodViewHolder;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class FoodList extends AppCompatActivity {
RecyclerView recyclerView;
RecyclerView.LayoutManager layoutManager;

FirebaseDatabase database;
DatabaseReference foodList;

String categoryId="";



FirebaseRecyclerAdapter<Food, FoodViewHolder> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);


        //Firebase
        database = FirebaseDatabase.getInstance();
        foodList = database.getReference("Items");

        recyclerView = (RecyclerView)findViewById(R.id.recycler_food);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //get Intent here
        if (getIntent() != null)
            categoryId = getIntent().getStringExtra("CategoryId");
        if (!categoryId.isEmpty() )
        {
            loadListFood(categoryId);
        }


    }

    private void loadListFood(String categoryId) {
        FirebaseRecyclerOptions<Food> options =new FirebaseRecyclerOptions.Builder<Food>()
                .setQuery(foodList.orderByChild("menuId").equalTo(categoryId),Food.class)
                .build();


        adapter = new FirebaseRecyclerAdapter<Food, FoodViewHolder>(options) {
        @Override
        protected void onBindViewHolder(@NonNull FoodViewHolder holder, int position, @NonNull Food model) {

            holder.food_Name.setText(model.getName());
            Picasso.get().load(model.getImage()).into(holder.food_image, new Callback() {
                @Override
                public void onSuccess() {

                }

                @Override
                public void onError(Exception e) {

                }
            });

            final Food local = model;
            holder.setItemClickListener(new ItemClickListener() {
                @Override
                public void onClick(View view, int position, boolean isLongClick) {
                    //Toast.makeText(FoodList.this , "" +local.getName(),Toast.LENGTH_SHORT).show();
                    Intent foodId = new Intent(FoodList.this, Sub.class);
                    //Because CategoryId is key, so we just get the key of this item
                    foodId.putExtra("FoodId", adapter.getRef(position).getKey());
                    startActivity(foodId);

                }
            });
        }

        @NonNull
        @Override
        public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.food_item,parent,false);


            return new FoodViewHolder(view);

        }
    };

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),1);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter.startListening();

        Log.d("TAG" , "" +adapter.getItemCount());
        recyclerView.setAdapter(adapter);
    }

}
