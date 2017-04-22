package com.example.jesusizquierdo.tiendamex.Classes;

import com.example.jesusizquierdo.tiendamex.R;

/**
 * Created by Jesus Izquierdo on 4/20/2017.
 */

public class FoodItem {
    String name;
    String price;
    int picture;

    public FoodItem(){}
    public FoodItem(String name,String price){
        this.name = name;
        this.price = price;
        picture = R.drawable.taco;


    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public int getPicture() {
        return picture;
    }
}
