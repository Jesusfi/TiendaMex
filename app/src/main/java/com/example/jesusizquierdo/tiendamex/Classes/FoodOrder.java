package com.example.jesusizquierdo.tiendamex.Classes;

/**
 * Created by Jesus Izquierdo on 5/1/2017.
 */

public class FoodOrder {
    int quantity;
    FoodItem foodItem;
    String namePersonOrder;
    String date;
    boolean isComplete;

    public FoodOrder(int quantity, FoodItem foodItem) {
        this.quantity = quantity;
        this.foodItem = foodItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public FoodItem getFoodItem() {
        return foodItem;
    }
}
