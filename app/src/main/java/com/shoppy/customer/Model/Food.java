package com.shoppy.customer.Model;

public class Food {
    private String Name,Image,menuId;

    public Food() {

    }

    public Food(String name, String image, String menuId) {
        Name = name;
        Image = image;
        this.menuId = menuId;
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
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }
}



