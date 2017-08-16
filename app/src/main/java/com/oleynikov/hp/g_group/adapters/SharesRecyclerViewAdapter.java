package com.oleynikov.hp.g_group.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.oleynikov.hp.g_group.R;
import com.oleynikov.hp.g_group.activity.ViewImageActivity;
import com.oleynikov.hp.g_group.model.Info;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HP on 4/29/2017.
 */

public class SharesRecyclerViewAdapter extends RecyclerView.Adapter<SharesRecyclerViewAdapter.CustomViewHolder> {

    Context context;
    List<Info> sourseURLImage = new ArrayList<>();

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        protected ImageView imageView;


        public CustomViewHolder(View view) {
            super(view);
            this.imageView = (ImageView) view.findViewById(R.id.thumbnail);


        }
    }

    public SharesRecyclerViewAdapter(Context context, List<Info> sourseURLImage) {
        this.context = context;
        this.sourseURLImage = sourseURLImage;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, final int position) {
        final View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.shares_layout, null);


        CustomViewHolder viewHolder = new CustomViewHolder(view);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final CustomViewHolder customViewHolder, final int position) {
        customViewHolder.itemView.setFocusable(true);



        customViewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, ViewImageActivity.class);
                intent.putExtra("URL", sourseURLImage.get(position).getSourceUrl());
                context.startActivity(intent);

            }
        });
        Uri uri = Uri.parse(sourseURLImage.get(position).getSourceUrl());
        Glide.with(context).load(uri).into(customViewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return sourseURLImage.size();
    }


}
