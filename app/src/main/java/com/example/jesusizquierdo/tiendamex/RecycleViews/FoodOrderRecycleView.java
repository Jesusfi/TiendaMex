package com.example.jesusizquierdo.tiendamex.RecycleViews;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jesusizquierdo.tiendamex.Classes.FoodItem;
import com.example.jesusizquierdo.tiendamex.OrderFood;
import com.example.jesusizquierdo.tiendamex.Orders;
import com.example.jesusizquierdo.tiendamex.R;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Jesus Izquierdo on 5/2/2017.
 */

public class FoodOrderRecycleView extends RecyclerView.Adapter<FoodOrderRecycleView.MyViewHolder2> {
    Context mContext;
    ArrayList<FoodItem> foodItems;
    public FoodOrderRecycleView(Context context, ArrayList<FoodItem> foodItems){
        this.mContext = context;
        this.foodItems = foodItems;
    }

    @Override
    public MyViewHolder2 onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_fullorder_list_rv,parent,false);
        return new MyViewHolder2(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder2 holder, int position) {
        FoodItem foodItem = foodItems.get(position);

        holder.nameFood.setText(foodItem.getName());
        holder.food.setImageResource(foodItem.getPicture());
    }

    @Override
    public int getItemCount() {
        return foodItems.size();
    }

    public class MyViewHolder2 extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView nameFood, priceFood;
        ImageView food;
        public MyViewHolder2(View itemView) {
            super(itemView);
            food = (ImageView) itemView.findViewById(R.id.imageView_fullorder);
            nameFood = (TextView) itemView.findViewById(R.id.textView_name_fullorder);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            FoodItem getItem = foodItems.get(getAdapterPosition());
            Bundle bundle = new Bundle();
            bundle.putSerializable("key5",getItem);
            Intent intent = new Intent(mContext, OrderFood.class);
            intent.putExtras(bundle);
            Toast.makeText(mContext,"Clicked",Toast.LENGTH_SHORT).show();
            ((Orders)mContext).startActivity(intent);

        }
    }
}
