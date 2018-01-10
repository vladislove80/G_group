package com.oleynikov.hp.ggroup.profile;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.oleynikov.hp.ggroup.R;

import com.oleynikov.hp.ggroup.other.Transaction;

import java.util.List;

/**
 * Created by HP on 5/21/2017.
 */

public class RecyclerViewTransactionAdapter extends RecyclerView.Adapter<RecyclerViewTransactionAdapter.MyView> {

    private List<Transaction> mList;

    public class MyView extends RecyclerView.ViewHolder {

        public TextView mTextViewTimeTransaction;
        public TextView mTextViewDateTransaction;
        public TextView mTextViewCostTransaction;

        public MyView(View itemView) {
            super(itemView);

            mTextViewTimeTransaction = (TextView) itemView.findViewById(R.id.textViewTimeTransaction);
            mTextViewDateTransaction = (TextView) itemView.findViewById(R.id.textViewDateTransaction);
            mTextViewCostTransaction = (TextView) itemView.findViewById(R.id.textViewCostTransaction);
        }
    }

    public RecyclerViewTransactionAdapter(List<Transaction> mTransaction) {
        this.mList = mTransaction;
    }


   

    @Override
    public MyView onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_transaction, parent, false);

        return new MyView(itemView);
    }

    @Override
    public void onBindViewHolder(MyView holder, int position) {

        holder.mTextViewTimeTransaction.setText(mList.get(position).mTime);
        holder.mTextViewDateTransaction.setText(mList.get(position).mDate);
        holder.mTextViewCostTransaction.setText(mList.get(position).mCost);

    }

  

    @Override
    public int getItemCount() {
        return mList.size();
    }


}
