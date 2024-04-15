package com.shoppy.customer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
//import android.view.View;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shoppy.customer.Database.Database;
import com.shoppy.customer.Model.Order;
import com.shoppy.customer.Model.SubItem;
import com.squareup.picasso.Picasso;



public class SubDetails extends AppCompatActivity {

    TextView sub_name, sub_price, sub_description;
    ImageView sub_image;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton btnCart;
    ElegantNumberButton numberButton;
    FirebaseDatabase database;
    DatabaseReference Items;
    SubItem subItem;
    String subId="";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_details);

        //Firebase
        database = FirebaseDatabase.getInstance();
        Items = database.getReference("SubItems");

        numberButton = findViewById(R.id.number_button);
        btnCart = findViewById(R.id.btnCart);

       btnCart.setOnClickListener(new View.OnClickListener() {
           @Override
         public void onClick(View view) {
           new Database(getBaseContext()).addToCart(new Order(

                       subItem.getName(),
                       numberButton.getNumber(),
                       subItem.getPrice(),
                       subItem.getDiscount()
               ));

               Toast.makeText(SubDetails.this , "Added to Cart" , Toast.LENGTH_SHORT).show();

           }
     });

        sub_description = findViewById(R.id.sub_description);
        sub_name = findViewById(R.id.sub_name);
        sub_price = findViewById(R.id.sub_price);
        sub_image     = findViewById(R.id.img_sub);

        collapsingToolbarLayout = findViewById(R.id.collapsing);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppbar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppbar);

       //Get SubItem id From Intent
        if (getIntent() != null)
            subId = getIntent().getStringExtra("SubId");
        if (!subId.isEmpty() ){
            getDetailItems(subId);
        }

    }

    private void getDetailItems(String subId) {
        Items.child(subId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                 subItem = dataSnapshot.getValue(SubItem.class);

                //Set Image
               Picasso.get().load(subItem.getImage()).into(sub_image);

                collapsingToolbarLayout.setTitle( subItem.getName());
                sub_price.setText(subItem.getPrice());
                sub_name.setText(subItem.getName());
                sub_description.setText(subItem.getDescription());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
