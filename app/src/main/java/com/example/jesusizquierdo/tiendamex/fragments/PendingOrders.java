package com.example.jesusizquierdo.tiendamex.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jesusizquierdo.tiendamex.Classes.Strings;
import com.example.jesusizquierdo.tiendamex.R;
import com.example.jesusizquierdo.tiendamex.RecycleViews.FirebaseOrderPendingViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class PendingOrders extends Fragment {
    private DatabaseReference mRestaurantReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;
    RecyclerView mRecyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewRoot = inflater.inflate(R.layout.fragment_pending_orders, container, false);
        mRecyclerView = (RecyclerView) viewRoot.findViewById(R.id.i_this_works);
        mRestaurantReference = FirebaseDatabase.getInstance().getReference("Strings");
        setUpFirebaseAdapter();
        return viewRoot;
    }

    private void setUpFirebaseAdapter() {
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Strings, FirebaseOrderPendingViewHolder>
                (Strings.class, R.layout.custom_pending_order_rv, FirebaseOrderPendingViewHolder.class,
                        mRestaurantReference) {

            @Override
            protected void populateViewHolder(FirebaseOrderPendingViewHolder viewHolder, Strings model, int position) {
                viewHolder.bindOrder(model);
            }

        };
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mFirebaseAdapter);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }

}
