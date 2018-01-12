package com.oleynikov.hp.ggroup.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.oleynikov.hp.ggroup.R;
import com.oleynikov.hp.ggroup.activity.event.EventClickListener;

import java.util.ArrayList;
import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.CustomViewHolder> {

    private Context context;
    private List<String> sourseURLImage = new ArrayList<>();
    private EventClickListener eventClickListener;

    public EventAdapter(@NonNull Context context, @NonNull List<String> sourseURLImage, @NonNull EventClickListener eventClickListener) {
        this.context = context;
        this.sourseURLImage = sourseURLImage;
        this.eventClickListener = eventClickListener;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_event, null);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, final int position) {
        Uri uri = Uri.parse(sourseURLImage.get(position));
        Glide.with(context).load(uri).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return sourseURLImage.size();
    }

    public void update(@NonNull List<String> list) {
        sourseURLImage.clear();
        sourseURLImage.addAll(list);
        notifyDataSetChanged();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;

        CustomViewHolder(View itemView) {
            super(itemView);
            this.imageView = (ImageView) itemView.findViewById(R.id.imageViewEventAdapter);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    eventClickListener.onEventClick(sourseURLImage.get(getAdapterPosition()));
                }
            });
        }
    }
}
