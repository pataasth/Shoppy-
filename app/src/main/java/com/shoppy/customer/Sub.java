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
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.shoppy.customer.Interface.ItemClickListener;
import com.shoppy.customer.Model.SubItem;
import com.shoppy.customer.ViewHolder.SubViewHolder;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class Sub extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference ItemList;

    String foodId = "";

    FirebaseRecyclerAdapter<SubItem, SubViewHolder> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        //Firebase
        database = FirebaseDatabase.getInstance();
        ItemList = database.getReference("SubItems");


        recyclerView = (RecyclerView) findViewById(R.id.recycler_sub);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //get Intent here
        if (getIntent() != null)
            foodId = getIntent().getStringExtra("FoodId");
        if (!foodId.isEmpty()) {
            loadListSub(foodId);
        }

    }

    private void loadListSub(String foodId) {

        FirebaseRecyclerOptions<SubItem> options = new FirebaseRecyclerOptions.Builder<SubItem>()
                .setQuery(ItemList.orderByChild("MenuId").equalTo(foodId), SubItem.class)
                .build();


        adapter = new FirebaseRecyclerAdapter<SubItem, SubViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull SubViewHolder holder, int position, @NonNull SubItem model) {
                holder.sub_Name.setText(model.getName());
                Picasso.get().load(model.getImage()).into(holder.sub_image, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });
                final SubItem local = model;
                holder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                  //Toast.makeText(Sub.this, "" + local.getName(), Toast.LENGTH_SHORT).show();
                        Intent subDetail = new Intent(Sub.this,SubDetails.class);
                        subDetail.putExtra("SubId",adapter.getRef(position).getKey());
                        startActivity(subDetail);

                    }
                });
            }


            @NonNull
            @Override
            public SubViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sub_item, parent, false);


                return new SubViewHolder(view);
            }

        };
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter.startListening();

        Log.d("TAG", "" + adapter.getItemCount());
        recyclerView.setAdapter(adapter);

    }
}