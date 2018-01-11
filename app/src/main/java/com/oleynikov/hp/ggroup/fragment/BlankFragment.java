package com.oleynikov.hp.ggroup.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oleynikov.hp.ggroup.R;
import com.oleynikov.hp.ggroup.adapters.RecyclerDeliveryAdapter;
import com.oleynikov.hp.ggroup.model.Item;

import java.util.ArrayList;
import java.util.List;

public class BlankFragment extends Fragment {
    private List<Item> mFoodListAlMezze = new ArrayList<>();

    public BlankFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_blank, container, false);
        getFoodList();
        RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.rv_recycler_view);
        rv.setHasFixedSize(true);
        RecyclerDeliveryAdapter adapter = new RecyclerDeliveryAdapter(getActivity(), mFoodListAlMezze);
        rv.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);
        return rootView;
    }

    public void getFoodList() {
        mFoodListAlMezze = new ArrayList<>();
//        mFoodListAlMezze.add(new FoodList("Hot-Dog", 1, "100 UAH"));
//        mFoodListAlMezze.add(new FoodList("Hot-Dog1", 1, "100 UAH"));
//        mFoodListAlMezze.add(new FoodList("Hot-Dog2", 1, "100 UAH"));
    }
}
