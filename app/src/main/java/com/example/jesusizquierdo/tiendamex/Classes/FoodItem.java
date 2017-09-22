package com.example.jesusizquierdo.tiendamex.Classes;

import com.example.jesusizquierdo.tiendamex.R;

import java.io.Serializable;

/**
 * Created by Jesus Izquierdo on 4/20/2017.
 */

public class FoodItem implements Serializable{
    String name;
    String price;
    int picture;

    public FoodItem() {
    }

    public FoodItem(String name, String price, int picture) {
        this.name = name;
        this.price = price;
        this.picture = picture;


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
