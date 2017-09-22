package com.example.jesusizquierdo.tiendamex.RecycleViews;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jesusizquierdo.tiendamex.Classes.Ingredients;
import com.example.jesusizquierdo.tiendamex.OrderFood;
import com.example.jesusizquierdo.tiendamex.R;

import java.util.ArrayList;

/**
 * Created by Jesus Izquierdo on 5/5/2017.
 */

public class RecycleIngredientIncludeAdapter extends RecyclerView.Adapter<RecycleIngredientIncludeAdapter.MyViewHolder3> {
    Context context;
    ArrayList<Ingredients> ingredients;

    public RecycleIngredientIncludeAdapter(Context context, ArrayList<Ingredients> ingredients){
        this.context = context;
        this.ingredients = ingredients;
    }
    @Override
    public RecycleIngredientIncludeAdapter.MyViewHolder3 onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_ingredient_rv,parent,false);
        return new MyViewHolder3(itemView);
    }

    @Override
    public void onBindViewHolder(RecycleIngredientIncludeAdapter.MyViewHolder3 holder, int position) {
        Ingredients ingredient = ingredients.get(position);
        holder.pic.setImageResource(ingredient.getPic());
        holder.name.setText(ingredient.getName());
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public class MyViewHolder3 extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView pic;
        TextView name;
        public MyViewHolder3(View itemView) {
            super(itemView);
            pic = (ImageView) itemView.findViewById(R.id.imageView_ingredient);
            name = (TextView) itemView.findViewById(R.id.textView_ingredient_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            ((OrderFood)context).AddToExtras(ingredients.get(getAdapterPosition()));
            ingredients.remove(getAdapterPosition());
            notifyItemRemoved(getAdapterPosition());
            notifyItemRangeChanged(getAdapterPosition(),ingredients.size());
        }
    }



}
