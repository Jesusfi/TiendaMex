package com.example.jesusizquierdo.tiendamex.Classes;

/**
 * Created by Jesus Izquierdo on 5/5/2017.
 */

public class Ingredients {
    String name;
    int pic;

    public String getName() {
        return name;
    }

    public int getPic() {
        return pic;
    }

    public Ingredients(String name, int pic) {

        this.name = name;
        this.pic = pic;
    }
}
