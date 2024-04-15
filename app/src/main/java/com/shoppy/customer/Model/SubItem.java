package com.shoppy.customer.Model;

public class SubItem {
   // public static int getImage;
    private String Name,Image,MenuId,Discount,Description,Price;

    public SubItem() {
    }

    public SubItem(String name, String image, String menuId, String discount, String description, String price) {
        Name = name;
        Image = image;
        MenuId = menuId;
        Discount = discount;
        Description = description;
        Price = price;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getMenuId() {
        return MenuId;
    }

    public void setMenuId(String menuId) {
        MenuId = menuId;
    }

    public String getDiscount() {
        return Discount;
    }

    public void setDiscount(String discount) {
        Discount = discount;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }
}
