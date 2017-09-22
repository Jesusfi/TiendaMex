package com.example.jesusizquierdo.tiendamex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.jesusizquierdo.tiendamex.Classes.FoodItem;
import com.example.jesusizquierdo.tiendamex.Classes.Ingredients;
import com.example.jesusizquierdo.tiendamex.RecycleViews.RecycleIngredientExtrasAdapter;
import com.example.jesusizquierdo.tiendamex.RecycleViews.RecycleIngredientIncludeAdapter;

import java.util.ArrayList;

public class OrderFood extends AppCompatActivity {
    RecyclerView includes, extras;
    RecycleIngredientIncludeAdapter adapter;
    RecycleIngredientExtrasAdapter adapter2;
    ArrayList<Ingredients> ingredientses;
    ArrayList<Ingredients> ingredientses1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_food);
        Bundle b = this.getIntent().getExtras();
        FoodItem foodItem = (FoodItem) b.getSerializable("key5");


        includes = (RecyclerView) findViewById(R.id.rv_includes);
        extras = (RecyclerView) findViewById(R.id.rv_extras);

        ingredientses = new ArrayList<>();
        ingredientses.add(new Ingredients("tomato", R.drawable.tomato));
        ingredientses.add(new Ingredients("tomato", R.drawable.tomato));
        ingredientses.add(new Ingredients("tomato", R.drawable.tomato));
        ingredientses.add(new Ingredients("tomato", R.drawable.tomato));

        adapter = new RecycleIngredientIncludeAdapter(this, ingredientses);
        includes.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        includes.setLayoutManager(linearLayoutManager);
        includes.setAdapter(adapter);

        ingredientses1 = new ArrayList<>();
        ingredientses1.add((new Ingredients("Bread",R.drawable.onion)));
        ingredientses1.add((new Ingredients("Bread",R.drawable.onion)));
        ingredientses1.add((new Ingredients("Bread",R.drawable.onion)));
        ingredientses1.add((new Ingredients("Bread",R.drawable.onion)));
        ingredientses1.add((new Ingredients("Bread",R.drawable.onion)));


        adapter2 = new RecycleIngredientExtrasAdapter(this, ingredientses1);
        extras.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        extras.setLayoutManager(linearLayoutManager1);
        extras.setAdapter(adapter2);


    }
    public void AddToInclude(Ingredients ingredients){
        ingredientses.add(ingredients);
        adapter.notifyDataSetChanged();
        Toast.makeText(OrderFood.this,"" + ingredientses.size(),Toast.LENGTH_SHORT).show();
    }
    public void  AddToExtras(Ingredients ingredients){
        ingredientses1.add(ingredients);
        adapter2.notifyDataSetChanged();
    }
}
