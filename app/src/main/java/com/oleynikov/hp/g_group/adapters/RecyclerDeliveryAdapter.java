package com.oleynikov.hp.g_group.adapters;

/**
 * Created by HP on 6/3/2017.
 */

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.oleynikov.hp.g_group.R;
import com.oleynikov.hp.g_group.activity.DeliveryActivity;
import com.oleynikov.hp.g_group.model.FoodItem;
import com.oleynikov.hp.g_group.model.Item;
import com.oleynikov.hp.g_group.other.FoodList;

import java.util.List;

public class RecyclerDeliveryAdapter extends RecyclerView.Adapter<RecyclerDeliveryAdapter.MyViewHolder> {
    private List<Item> mFoodList;
    private Context context;
    private onRecyclerViewItemClickListener mItemClickListener;
    private int idRest;

    public RecyclerDeliveryAdapter(Context context, List<Item> mFoodList, int idRest) {
        this.context = context;
        this.mFoodList = mFoodList;
        this.idRest = idRest;
    }

    public void setOnItemClickListener(onRecyclerViewItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public interface onRecyclerViewItemClickListener {
        void onItemClickListener(View view, int position);
    }


    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CardView mCardView;
        TextView mTextViewTitle;
        TextView mTextViewWeight;
        TextView mTextViewCost;
        TextView mTextViewConsist;
        ImageView mImageViewFood;
        ImageButton mImageButtonAddInND;

        MyViewHolder(View v) {
            super(v);
            mImageViewFood = (ImageView) v.findViewById(R.id.imageViewDelivery);
            mTextViewCost = (TextView) v.findViewById(R.id.textViewCostDish);
            mTextViewConsist = (TextView) v.findViewById(R.id.textViewConsist);
            mTextViewWeight = (TextView) v.findViewById(R.id.textViewWeight);
            mCardView = (CardView) v.findViewById(R.id.card_viewDelivery);
            mTextViewTitle = (TextView) v.findViewById(R.id.textViewNameDish);
            this.mImageButtonAddInND = (ImageButton) v.findViewById(R.id.buttonAddInND);
            mImageButtonAddInND.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClickListener(v, getAdapterPosition());
            }
        }
    }


    public RecyclerDeliveryAdapter(Context context, List<Item> mFoodList) {
        this.context = context;
        this.mFoodList = mFoodList;
    }


    @Override
    public RecyclerDeliveryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                                   int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_item, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.mTextViewTitle.setText(mFoodList.get(position).getTitle());
        holder.mTextViewCost.setText(mFoodList.get(position).getPrice());
        holder.mTextViewWeight.setText(mFoodList.get(position).getWeight());
        holder.mTextViewConsist.setText(mFoodList.get(position).getDescription());






        if(mFoodList.get(position).getImages().getBig() != null) {
           Glide
                   .with(context)
                   .load(mFoodList.get(position).getImages().getBig())
                   .into(holder.mImageViewFood);

       }
       else
           {
               if (idRest == 121) {
                   holder.mImageViewFood.setImageResource(R.drawable.eleven_delivery);
               }
               if (idRest == 120) {
                   holder.mImageViewFood.setImageResource(R.drawable.almezze_delivery);
               }
               if (idRest == 119) {
                   holder.mImageViewFood.setImageResource(R.drawable.kinza_logo);
               }
           }
//
//
    }

    @Override
    public int getItemCount() {
        return mFoodList.size();
    }
}
