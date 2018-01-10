package com.oleynikov.hp.g_group.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.oleynikov.hp.g_group.R;
import com.oleynikov.hp.g_group.activity.main.InfoListener;
import com.oleynikov.hp.g_group.model.Info;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HP on 4/29/2017.
 */

public class InfoRecyclerViewAdapter extends RecyclerView.Adapter<InfoRecyclerViewAdapter.CustomViewHolder> {

    private final InfoListener infoListener;
    private Context context;
    private List<Info> sourseURLImage = new ArrayList<>();

    public InfoRecyclerViewAdapter(Context context, List<Info> sourseURLImage, InfoListener infoListener) {
        this.context = context;
        this.sourseURLImage = sourseURLImage;
        this.infoListener = infoListener;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, final int position) {
        final View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.shares_layout, null);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                infoListener.onInfoItemClick(sourseURLImage.get(position).getSourceUrl());
            }
        });
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CustomViewHolder customViewHolder, int position) {
        customViewHolder.itemView.setFocusable(true);
        Uri uri = Uri.parse(sourseURLImage.get(position).getSourceUrl());
        Glide.with(context).load(uri).into(customViewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return sourseURLImage == null ? 0 : sourseURLImage.size();
    }

    public void notifyInfoAdapter(List<Info> infoList) {
        sourseURLImage.clear();
        sourseURLImage.addAll(infoList);
        notifyDataSetChanged();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        CustomViewHolder(View view) {
            super(view);
            this.imageView = (ImageView) view.findViewById(R.id.thumbnail);
        }
    }
}
