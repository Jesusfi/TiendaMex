package com.example.jesusizquierdo.tiendamex;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.jesusizquierdo.tiendamex.Classes.FoodItem;
import com.example.jesusizquierdo.tiendamex.RecycleViews.RecycleViewFoodAdapter;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class ScrollingActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    Button signOut;
    RecyclerView recyclerView;
    RecycleViewFoodAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        firebaseAuth = FirebaseAuth.getInstance();

        signOut = (Button) findViewById(R.id.signout_button);
        recyclerView = (RecyclerView) findViewById(R.id.foodlist);

        ArrayList<FoodItem> foodItemArrayList = new ArrayList<>();

        foodItemArrayList.add(new FoodItem("Taco", "1.99"));
        foodItemArrayList.add(new FoodItem("Burrito", "1.99"));
        foodItemArrayList.add(new FoodItem("Quesidilla", "1.99"));
        foodItemArrayList.add(new FoodItem("Chicken", "1.99"));
        foodItemArrayList.add(new FoodItem("Taco", "1.99"));
        foodItemArrayList.add(new FoodItem("Taco", "1.99"));
        foodItemArrayList.add(new FoodItem("Taco", "1.99"));

        adapter = new RecycleViewFoodAdapter(this, foodItemArrayList);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();


            }
        });

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(ScrollingActivity.this, Login.class));
                finish();
                // overridePendingTransition(R.anim.slide_out_left,R.anim.slide_in_right);
            }
        });
    }
}
