package com.oleynikov.hp.g_group.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.oleynikov.hp.g_group.R;
import com.oleynikov.hp.g_group.activity.DeliveryActivity;
import com.oleynikov.hp.g_group.model.Item;
import com.oleynikov.hp.g_group.other.FoodList;

import java.util.List;

/**
 * Created by HP on 6/25/2017.
 */

public class RecyclerNavDrawerDelivery extends RecyclerView.Adapter<RecyclerNavDrawerDelivery.MyViewHolder> {
    private List<Item> foodList;
    Context context;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView mTextViewFoodName;
        TextView mTextViewFoodCount;
        TextView mTextViewFoodCost;
        ImageButton mImageButtonAdd;
        ImageButton mImageButtonRemove;


        public MyViewHolder(View v) {
            super(v);

            mImageButtonAdd = (ImageButton) v.findViewById(R.id.imageButtonPlus);
            mImageButtonRemove = (ImageButton) v.findViewById(R.id.imageButtonRemove);
            mTextViewFoodName = (TextView) v.findViewById(R.id.textViewNameFoodDelivery);
            mTextViewFoodCount = (TextView) v.findViewById(R.id.textViewCountFood);
            mTextViewFoodCost = (TextView) v.findViewById(R.id.textViewCostFood);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public RecyclerNavDrawerDelivery(Context context , List<Item> foodLists) {
        this.context = context;
        this.foodList = foodLists;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RecyclerNavDrawerDelivery.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_delivery, parent, false);
        // set the view's size, margins, paddings and layout parameters
        RecyclerNavDrawerDelivery.MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final RecyclerNavDrawerDelivery.MyViewHolder holder, final int position) {
        holder.mTextViewFoodName.setText(foodList.get(position).getTitle());
        holder.mTextViewFoodCount.setText(""+foodList.get(position).getCount());
        holder.mTextViewFoodCost.setText(foodList.get(position).getPrice());
        holder.mImageButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                foodList.get(position).setCount( foodList.get(position).getCount() + 1);
//                foodList.get(position).setPrice( foodList.get(position).getPrice() + foodList.get(position).getPrice());
//                holder.mTextViewFoodCost.setText(foodList.get(position).getPrice());

                holder.mTextViewFoodCount.setText(""+foodList.get(position).getCount());

            }
        });
        holder.mImageButtonRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (foodList.get(position).getCount()>1){

                    foodList.get(position).setCount( foodList.get(position).getCount() - 1);
//                    foodList.get(position).setPrice( foodList.get(position).getPrice() - foodList.get(position).getPrice());
                    holder.mTextViewFoodCount.setText(""+foodList.get(position).getCount());
                }
                else
                    {
                        notifyDataSetChanged();
                        foodList.remove(position);
                        notifyDataSetChanged();
                    }

            }
        });
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }
}
