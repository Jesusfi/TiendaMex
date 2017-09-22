package com.example.jesusizquierdo.tiendamex.RecycleViews;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jesusizquierdo.tiendamex.Classes.FoodOrder;
import com.example.jesusizquierdo.tiendamex.Classes.Strings;
import com.example.jesusizquierdo.tiendamex.Orders;
import com.example.jesusizquierdo.tiendamex.R;
import com.example.jesusizquierdo.tiendamex.ScrollingActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Jesus Izquierdo on 5/5/2017.
 */

public class FirebaseOrderPendingViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    View mView;
    Context mContext;

    public FirebaseOrderPendingViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindOrder(Strings strings) {
        TextView quantityTextView = (TextView) mView.findViewById(R.id.textView_order_quantity);
        TextView nameTextView = (TextView) mView.findViewById(R.id.textView_name_of_order);



        nameTextView.setText(strings.getName());
    }

    @Override
    public void onClick(View view) {
        final ArrayList<Strings> foodOrderArrayList = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Strings");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    foodOrderArrayList.add(snapshot.getValue(Strings.class));
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
