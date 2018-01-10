package com.oleynikov.hp.g_group.profile;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.oleynikov.hp.g_group.R;
import com.oleynikov.hp.g_group.activity.EditProfileActivity;
import com.oleynikov.hp.g_group.other.Transaction;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView mRecyclerViewCards;
    private RecyclerView mRecyclerViewTransactions;
    private List<Balance> mBalance;
    private List<Transaction> mTransaction;
    private RecyclerView.LayoutManager mRecyclerViewLayoutManager;
    private RecyclerView.LayoutManager mRecyclerViewLayoutManagerTransactions;
    private RecyclerViewBalanceCardAdapter mRecyclerViewAdapterCards;
    private RecyclerViewTransactionAdapter mRecyclerViewTransactionsAdapter;
    private LinearLayoutManager mHorizontalLayoutCards;
    private LinearLayoutManager mHorizontalLayoutTransactions;
    private ImageButton mImageButtonEditProfile;
    private ImageButton mImageButtonOpenSort;
    private ImageButton mImageButtonHome;
    private LinearLayout mLinearLayoutSort;
    private LinearLayout mLinearLayoutRVCard;
    private RelativeLayout mRelativeLayoutTimeTransaction;
    private Button mButtonOnDate;
    private Button mButtonOnCost;
    private ImageView mImageViewRotation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        setViews();
        setListenersToViews();
        // Adding items to RecyclerView.
        addBalanceCardToRecyclerViewArrayList();
        addListTransactionToRecyclerViewArrayList();

        setCardsRecycler();
        setTransactionsRecycler();
    }

    private void setTransactionsRecycler() {
        mRecyclerViewTransactions = (RecyclerView) findViewById(R.id.recyclerTransactions);
        mRecyclerViewLayoutManagerTransactions = new LinearLayoutManager(getApplicationContext());
        mRecyclerViewTransactions.setLayoutManager(mRecyclerViewLayoutManagerTransactions);
        mRecyclerViewTransactionsAdapter = new RecyclerViewTransactionAdapter(mTransaction);
        mHorizontalLayoutTransactions = new LinearLayoutManager(ProfileActivity.this, LinearLayoutManager.VERTICAL, false);
        mRecyclerViewTransactions.setLayoutManager(mHorizontalLayoutTransactions);
        mRecyclerViewTransactions.setAdapter(mRecyclerViewTransactionsAdapter);
    }

    private void setCardsRecycler() {
        mRecyclerViewCards = (RecyclerView) findViewById(R.id.recyclerViewCards);
        mRecyclerViewLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerViewCards.setLayoutManager(mRecyclerViewLayoutManager);
        mRecyclerViewAdapterCards = new RecyclerViewBalanceCardAdapter(mBalance);
        mHorizontalLayoutCards = new LinearLayoutManager(ProfileActivity.this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerViewCards.setLayoutManager(mHorizontalLayoutCards);
        mRecyclerViewCards.setAdapter(mRecyclerViewAdapterCards);
    }

    private void setListenersToViews() {
        mImageButtonHome.setOnClickListener(this);
        mImageButtonOpenSort.setOnClickListener(this);
        mImageButtonEditProfile.setOnClickListener(this);
        mButtonOnCost.setOnClickListener(this);
        mButtonOnDate.setOnClickListener(this);
    }

    private void setViews() {
        mImageButtonHome = (ImageButton) findViewById(R.id.imageButtonHome);
        mImageButtonOpenSort = (ImageButton) findViewById(R.id.imageButtonOpenSort);
        mImageButtonEditProfile = (ImageButton) findViewById(R.id.imageButtonEditProfile);
        mLinearLayoutSort = (LinearLayout) findViewById(R.id.LinearLayoutOpen);
        mLinearLayoutRVCard = (LinearLayout) findViewById(R.id.layoutRVCard);
        mRelativeLayoutTimeTransaction = (RelativeLayout) findViewById(R.id.layoutTimeTransaction);
        mButtonOnCost = (Button) findViewById(R.id.buttonOnCost);
        mButtonOnDate = (Button) findViewById(R.id.buttonOnDate);
        mImageViewRotation = (ImageView) findViewById(R.id.imageViewRotate);
        mLinearLayoutSort.setVisibility(View.GONE);
        mLinearLayoutRVCard.setVisibility(View.GONE);
        mRelativeLayoutTimeTransaction.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageButtonOpenSort:
                if ((mLinearLayoutSort.getVisibility() == View.VISIBLE)) {
//                    mLinearLayoutSort.setAlpha(0.0f);
                    mLinearLayoutSort.animate()
                            .translationY(0)
                            .alpha(0.0f)
                            .setInterpolator(new DecelerateInterpolator())
                            .setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    super.onAnimationEnd(animation);
                                    mLinearLayoutSort.setVisibility(View.GONE);
                                    mLinearLayoutRVCard.setVisibility(View.GONE);
                                }
                            });
                    mImageViewRotation.setRotation(180);
                    mRelativeLayoutTimeTransaction.animate()
                            .translationY(0)
                            .alpha(0.0f)
                            .setInterpolator(new DecelerateInterpolator())
                            .setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    super.onAnimationEnd(animation);
                                    mRelativeLayoutTimeTransaction.setVisibility(View.GONE);
                                }
                            });
                } else {
                    mLinearLayoutSort.setAlpha(0.0f);
                    mLinearLayoutSort.animate()
                            .translationY(0)
                            .alpha(1.0f)
                            .setInterpolator(new DecelerateInterpolator())
                            .setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationStart(Animator animation) {
                                    super.onAnimationStart(animation);
                                    mLinearLayoutSort.setVisibility(View.VISIBLE);
                                }
                            });
                    mRelativeLayoutTimeTransaction.animate()
                            .translationY(0)
                            .alpha(1.0f)
                            .setInterpolator(new DecelerateInterpolator())
                            .setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationStart(Animator animation) {
                                    super.onAnimationStart(animation);
                                    mRelativeLayoutTimeTransaction.setVisibility(View.VISIBLE);
                                }
                            });
                    mImageViewRotation.setRotation(0);
                }
                break;
            case R.id.imageButtonHome:
                onBackPressed();
                break;
            case R.id.imageButtonEditProfile:
                startActivity(new Intent(ProfileActivity.this, EditProfileActivity.class));
                finish();
                break;
            case R.id.buttonOnDate:
                mButtonOnCost.setBackgroundColor(Color.BLACK);
                mButtonOnCost.setTextColor(Color.WHITE);
                mButtonOnDate.setBackgroundResource(R.drawable.rounded_button);
                mButtonOnDate.setTextColor(Color.BLACK);
                mLinearLayoutRVCard.setVisibility(View.GONE);
                mRelativeLayoutTimeTransaction.setVisibility(View.VISIBLE);
                break;
            case R.id.buttonOnCost:
                mButtonOnDate.setBackgroundColor(Color.BLACK);
                mButtonOnDate.setTextColor(Color.WHITE);
                mButtonOnCost.setBackgroundResource(R.drawable.rounded_button);
                mButtonOnCost.setTextColor(Color.BLACK);
                mLinearLayoutRVCard.setVisibility(View.VISIBLE);
                mRelativeLayoutTimeTransaction.setVisibility(View.GONE);
                break;
        }
    }

    public class Balance {
        public String mCost;
        public String mType;

        public Balance(String mCost, String mType) {
            this.mCost = mCost;
            this.mType = mType;
        }
    }

    // function to add items in RecyclerView.
    public void addBalanceCardToRecyclerViewArrayList() {
        mBalance = new ArrayList<>();
        mBalance.add(new Balance("ONE", "Normal"));
        mBalance.add(new Balance("ONE", "Normal"));
        mBalance.add(new Balance("ONE", "Normal"));
        mBalance.add(new Balance("ONE", "Normal"));
        mBalance.add(new Balance("ONE", "Normal"));
    }

    private void addListTransactionToRecyclerViewArrayList() {
        mTransaction = new ArrayList<>();
        mTransaction.add(new Transaction("22.01.22", "00:00:00", "34.50"));
        mTransaction.add(new Transaction("22.01.22", "00:00:00", "34.50"));
        mTransaction.add(new Transaction("22.01.22", "00:00:00", "34.50"));
        mTransaction.add(new Transaction("22.01.22", "00:00:00", "34.50"));
        mTransaction.add(new Transaction("22.01.22", "00:00:00", "34.50"));
        mTransaction.add(new Transaction("22.01.22", "00:00:00", "34.50"));
        mTransaction.add(new Transaction("22.01.22", "00:00:00", "34.50"));
    }
}
