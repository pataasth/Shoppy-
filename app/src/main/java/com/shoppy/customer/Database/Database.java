package com.shoppy.customer.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;
import com.shoppy.customer.Model.Order;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteAssetHelper {
   private static  final String DB_NAME="shoppy.db";
   private static final int DB_VAR=1;


    public Database(Context context) {
        super(context, DB_NAME,null,DB_VAR);
    }

    public List<Order> getCarts()
    {
        SQLiteDatabase db= getReadableDatabase();
        SQLiteQueryBuilder qb= new SQLiteQueryBuilder();

        String[] sqlSelect = {"ProductName", "Quantity", "Price", "Discount"};
        String sqlTable = "orderDetail";

        qb.setTables(sqlTable);
        Cursor c = qb.query(db, sqlSelect, null, null, null, null, null);

        final List<Order> result = new ArrayList<>();
        if(c.moveToFirst()){
            do{
                result.add(new Order(
                        c.getString(c.getColumnIndex("ProductName")),
                        c.getString(c.getColumnIndex("Quantity")),
                        c.getString(c.getColumnIndex("Price")),
                        c.getString(c.getColumnIndex("Discount"))
                ));
            }while (c.moveToNext());
        }
        return result;
    }

    public void addToCart(Order order){
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("INSERT INTO orderDetail( ProductName, Quantity, Price, Discount) VALUES('%s','%s','%s','%s');",
                order.getProductName(),
                order.getQuantity(),
                order.getPrice(),
                order.getDiscount());

        db.execSQL(query);
    }

    public void removeFromCart(String order){
        SQLiteDatabase db = getReadableDatabase();

        String query = String.format("DELETE FROM orderDetail WHERE ProductId='"+order+"'");
        db.execSQL(query);
    }
    public void cleanCart(){
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("DELETE FROM orderDetail");
        db.execSQL(query);
    }


}
