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
import android.widget.TextView;
import android.widget.Toast;

import com.example.jesusizquierdo.tiendamex.Classes.FoodItem;
import com.example.jesusizquierdo.tiendamex.Classes.FoodOrder;
import com.example.jesusizquierdo.tiendamex.Classes.Person;
import com.example.jesusizquierdo.tiendamex.Dialog.QuickOrderDialogFragment;
import com.example.jesusizquierdo.tiendamex.Dialog.SimpleDialogFragment;
import com.example.jesusizquierdo.tiendamex.RecycleViews.RecycleViewFoodAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ScrollingActivity extends AppCompatActivity implements QuickOrderDialogFragment.OnCompleteListenerQM {
    FirebaseAuth firebaseAuth;
    Button signOut;
    RecyclerView recyclerView;
    RecycleViewFoodAdapter adapter;
    TextView greetingUser;

    String nameFRV;
    int pic;
    int quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = firebaseAuth.getCurrentUser();

        signOut = (Button) findViewById(R.id.signout_button);
        recyclerView = (RecyclerView) findViewById(R.id.foodlist);
        greetingUser = (TextView) findViewById(R.id.textViewWelcomeName);

        ArrayList<FoodOrder> foodItemArrayList = new ArrayList<>();

        foodItemArrayList.add(new FoodOrder(1, new FoodItem("Taco", "1.99", R.drawable.taco)));
        foodItemArrayList.add(new FoodOrder(2, new FoodItem("Taco", "2.99", R.drawable.taco)));
        foodItemArrayList.add(new FoodOrder(3, new FoodItem("Taco", "3.99", R.drawable.taco)));
        foodItemArrayList.add(new FoodOrder(4, new FoodItem("Taco", "4.99", R.drawable.taco)));
        foodItemArrayList.add(new FoodOrder(1, new FoodItem("Burrito", "1.99", R.drawable.burrito)));
        foodItemArrayList.add(new FoodOrder(2, new FoodItem("Burrito", "2.99", R.drawable.burrito)));
        foodItemArrayList.add(new FoodOrder(3, new FoodItem("Burrito", "4.99", R.drawable.burrito)));
        foodItemArrayList.add(new FoodOrder(4, new FoodItem("Burrito", "6.99", R.drawable.burrito)));
        foodItemArrayList.add(new FoodOrder(5, new FoodItem("Burrito", "10.99", R.drawable.burrito)));
//askfsakdjf ;a
        String string = "tis ";


        adapter = new RecycleViewFoodAdapter(this, foodItemArrayList);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Discussion Info");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Person person = dataSnapshot.child("Users").child(user.getUid()).getValue(Person.class);
                greetingUser.setText("Welcome " + person.getNameFull() + " are you ready to order?");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ScrollingActivity.this, Orders.class));
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();


            }
        });

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                //finish();
                startActivity(new Intent(ScrollingActivity.this, Login.class));
                finish();
                // overridePendingTransition(R.anim.slide_out_left,R.anim.slide_in_right);
            }
        });


    }

    public void showSimpleDialog() {
        QuickOrderDialogFragment quickOrderDialogFragment = new QuickOrderDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("link", nameFRV);
        bundle.putInt("key", pic);
        bundle.putInt("key2", quantity);
        quickOrderDialogFragment.setArguments(bundle);
        quickOrderDialogFragment.show(getSupportFragmentManager(), "Custom Dialog Fragment");

    }

    public void onComplete(String email, String password, String name) {
        Toast.makeText(ScrollingActivity.this, nameFRV, Toast.LENGTH_SHORT).show();

    }

    public void setName(String theName, int pictemp, int quantityTemp) {
        nameFRV = theName;
        pic = pictemp;
        quantity = quantityTemp;
    }
}
