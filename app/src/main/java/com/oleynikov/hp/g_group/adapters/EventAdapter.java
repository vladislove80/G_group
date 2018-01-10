package com.oleynikov.hp.g_group.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.oleynikov.hp.g_group.R;
import com.oleynikov.hp.g_group.activity.ViewImageActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HP on 6/30/2017.
 */

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.CustomViewHolder> {
    Context context;
    List<String> sourseURLImage = new ArrayList<>();

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        protected ImageView imageView;
        public CustomViewHolder(View itemView) {
            super(itemView);
            this.imageView = (ImageView) itemView.findViewById(R.id.imageViewEventAdapter);
        }
    }

    public EventAdapter(Context context, List<String> sourseURLImage) {
        this.context = context;
        this.sourseURLImage = sourseURLImage;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_event, null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);


//        InfoRecyclerViewAdapter.CustomViewHolder viewHolder = new InfoRecyclerViewAdapter.CustomViewHolder(view);


        return viewHolder;

    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, final int position) {

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ViewImageActivity.class);
                intent.putExtra("URL", sourseURLImage.get(position));
                context.startActivity(intent);

            }
        });

        Uri uri = Uri.parse(sourseURLImage.get(position));
        Glide.with(context).load(uri).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return sourseURLImage.size();
    }

    public void update(final List<String> sourseURLImage) {
//        this.sourseURLImage.clear();
//        this.sourseURLImage.addAll(sourseURLImage);
        notifyDataSetChanged();

    }

}
