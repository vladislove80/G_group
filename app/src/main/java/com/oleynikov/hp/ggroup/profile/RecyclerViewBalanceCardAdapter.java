package com.oleynikov.hp.ggroup.profile;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.oleynikov.hp.ggroup.R;

import java.util.List;

/**
 * Created by HP on 5/17/2017.
 */

public class RecyclerViewBalanceCardAdapter extends RecyclerView.Adapter<RecyclerViewBalanceCardAdapter.MyView> {

    private List<ProfileActivity.Balance> mList;

    public class MyView extends RecyclerView.ViewHolder {

        public TextView mTextViewBalanceCoin;
        public TextView mTextViewTypeCard;

        public MyView(View view) {
            super(view);

            mTextViewBalanceCoin = (TextView) view.findViewById(R.id.textViewBalanceCoin);
            mTextViewTypeCard = (TextView) view.findViewById(R.id.textViewTypeName);


        }
    }


    public RecyclerViewBalanceCardAdapter(List<ProfileActivity.Balance> horizontalList) {
        this.mList = horizontalList;
    }

    @Override
    public MyView onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_balance, parent, false);

        return new MyView(itemView);
    }

    @Override
    public void onBindViewHolder(final MyView holder, final int position) {

        holder.mTextViewBalanceCoin.setText(mList.get(position).mCost);
        holder.mTextViewTypeCard.setText(mList.get(position).mType);

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

}