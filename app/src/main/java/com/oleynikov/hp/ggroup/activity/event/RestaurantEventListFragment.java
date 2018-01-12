package com.oleynikov.hp.ggroup.activity.event;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.oleynikov.hp.ggroup.GgroopApplication;
import com.oleynikov.hp.ggroup.R;
import com.oleynikov.hp.ggroup.activity.ViewImageActivity;
import com.oleynikov.hp.ggroup.adapters.EventAdapter;
import com.oleynikov.hp.ggroup.data.Callback;

import java.util.ArrayList;
import java.util.List;

import static android.widget.Toast.LENGTH_LONG;

/**
 * Created by Vladyslav on 11.01.2018
 */

public class RestaurantEventListFragment extends Fragment implements EventClickListener {
    public static final String TAG = RestaurantEventListFragment.class.getSimpleName();

    private static final String RESTAURANT_ID = "restaurant id";
    private EventAdapter eventAdapter;
    private ProgressBar progressBar;

    public static RestaurantEventListFragment newInstance(int restaurantId) {
        Log.d(TAG, "newInstance: restaurantId = " + restaurantId);
        Bundle args = new Bundle();
        RestaurantEventListFragment fragment = new RestaurantEventListFragment();
        args.putInt(RESTAURANT_ID, restaurantId);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_restaurant_events, container, false);
        RecyclerView rvList = (RecyclerView) view.findViewById(R.id.recyclerEvents);
        progressBar = (ProgressBar) view.findViewById(R.id.pb);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        rvList.setHasFixedSize(true);
        rvList.setLayoutManager(gridLayoutManager);
        eventAdapter = new EventAdapter(getContext(), new ArrayList<String>(), this);
        rvList.setAdapter(eventAdapter);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int restaurantId = getArguments().getInt(RESTAURANT_ID);
        GgroopApplication.getRepository().getEventListByRestaurantId(restaurantId, new Callback<List<String>>() {
            @Override
            public void onSuccess(List<String> data) {
                Log.d(TAG, "onSuccess: listSize " + data.size() );
                progressBar.setVisibility(View.GONE);
                if (!data.isEmpty()) {
                    if (eventAdapter != null) {
                        eventAdapter.update(data);
                    }
                } else {
                    Toast.makeText(getContext(), "Сервер не отвечает!", LENGTH_LONG).show();
                }
            }

            @Override
            public void onError(Throwable throwable) {
                Log.d(TAG, "onError: ");
                Toast.makeText(getContext(), "Ошибка загрузки!", LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onEventClick(String imageUrl) {
        Intent intent = new Intent(getContext(), ViewImageActivity.class);
        intent.putExtra("URL", imageUrl);
        getContext().startActivity(intent);
    }
}
