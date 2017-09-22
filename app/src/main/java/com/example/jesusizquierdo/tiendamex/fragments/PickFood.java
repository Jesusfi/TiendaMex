package com.example.jesusizquierdo.tiendamex.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jesusizquierdo.tiendamex.Classes.FoodItem;
import com.example.jesusizquierdo.tiendamex.R;
import com.example.jesusizquierdo.tiendamex.RecycleViews.FoodOrderRecycleView;

import java.util.ArrayList;


public class PickFood extends Fragment {
    RecyclerView recyclerView;
    FoodOrderRecycleView adapter;

    public PickFood() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewRoot = inflater.inflate(R.layout.fragment_pick_food, container, false);


        recyclerView = (RecyclerView) viewRoot.findViewById(R.id.full_food_list_rv);

        ArrayList<FoodItem> foodItemArrayList = new ArrayList<>();
        foodItemArrayList.add(new FoodItem("Taco", "4.99", R.drawable.taco));
        foodItemArrayList.add(new FoodItem("Burrito", "4.99", R.drawable.burrito));
        foodItemArrayList.add(new FoodItem("Quesidilla", "4.99", R.drawable.taco));
        foodItemArrayList.add(new FoodItem("Posole", "4.99", R.drawable.burrito));


        adapter = new FoodOrderRecycleView(getActivity(), foodItemArrayList);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);


        return viewRoot;
    }


}
