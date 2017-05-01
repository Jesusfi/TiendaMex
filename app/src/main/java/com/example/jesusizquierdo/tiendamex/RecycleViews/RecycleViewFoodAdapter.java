package com.example.jesusizquierdo.tiendamex.RecycleViews;

import android.app.FragmentManager;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jesusizquierdo.tiendamex.Classes.FoodItem;
import com.example.jesusizquierdo.tiendamex.Classes.FoodOrder;
import com.example.jesusizquierdo.tiendamex.Dialog.SimpleDialogFragment;
import com.example.jesusizquierdo.tiendamex.Login;
import com.example.jesusizquierdo.tiendamex.R;
import com.example.jesusizquierdo.tiendamex.ScrollingActivity;

import java.util.ArrayList;

/**
 * Created by Jesus Izquierdo on 4/20/2017.
 */

public class RecycleViewFoodAdapter extends RecyclerView.Adapter<RecycleViewFoodAdapter.MyViewHolder> {

    Context mContext;
    ArrayList<FoodOrder> foodItems;


    public RecycleViewFoodAdapter(Context context, ArrayList<FoodOrder> foodItems) {
        this.mContext = context;
        this.foodItems = foodItems;
    }


    @Override
    public RecycleViewFoodAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_food_list_rv, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecycleViewFoodAdapter.MyViewHolder holder, int position) {
        FoodOrder foodItem = foodItems.get(position);

        holder.nameFood.setText(foodItem.getQuantity() + " " + foodItem.getFoodItem().getName());
        holder.priceFood.setText(foodItem.getFoodItem().getPrice());
        holder.food.setImageResource(foodItem.getFoodItem().getPicture());
    }

    @Override
    public int getItemCount() {
        return foodItems.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nameFood, priceFood;
        ImageView food;

        public MyViewHolder(View itemView) {
            super(itemView);
            food = (ImageView) itemView.findViewById(R.id.imageViewFood);
            nameFood = (TextView) itemView.findViewById(R.id.textViewFoodName);
            priceFood = (TextView) itemView.findViewById(R.id.textViewFoodPrice);
            food.setOnClickListener(this);


        }

        @Override
        public void onClick(View view) {
            FoodOrder foodItem = foodItems.get(getAdapterPosition());
            ((ScrollingActivity) mContext).setName(nameFood.getText().toString(), foodItem.getFoodItem().getPicture(),foodItem.getQuantity());
            ((ScrollingActivity) mContext).showSimpleDialog();
        }
    }
}
