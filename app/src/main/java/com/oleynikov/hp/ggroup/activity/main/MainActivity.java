package com.oleynikov.hp.ggroup.activity.main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.azoft.carousellayoutmanager.CarouselLayoutManager;
import com.azoft.carousellayoutmanager.CarouselZoomPostLayoutListener;
import com.azoft.carousellayoutmanager.CenterScrollListener;
import com.oleynikov.hp.ggroup.GgroopApplication;
import com.oleynikov.hp.ggroup.R;
import com.oleynikov.hp.ggroup.activity.AboutUsActivity;
import com.oleynikov.hp.ggroup.activity.DeliveryActivity;
import com.oleynikov.hp.ggroup.activity.LogoActivity;
import com.oleynikov.hp.ggroup.activity.ShareActivity;
import com.oleynikov.hp.ggroup.activity.ViewImageActivity;
import com.oleynikov.hp.ggroup.activity.event.RestaurantEventsActivity;
import com.oleynikov.hp.ggroup.adapters.InfoRecyclerViewAdapter;
import com.oleynikov.hp.ggroup.data.Callback;
import com.oleynikov.hp.ggroup.model.Info;
import com.oleynikov.hp.ggroup.model.Restaurant;
import com.oleynikov.hp.ggroup.profile.ProfileActivity;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.oleynikov.hp.ggroup.activity.RegistrationActivity.SAVED_IMAGE;


public class MainActivity extends AppCompatActivity implements InfoListener, View.OnClickListener {
    public static final String TAG = MainActivity.class.getSimpleName();
    public static final String rest = "rest";
    private RecyclerView mSharesRecyclerView;
    private InfoRecyclerViewAdapter myRecyclerAdapter;
    private DrawerLayout mDrawerLayout;
    private ImageButton mImageButtonOpenNB;
    private ImageButton mImageButtonHotPerci;
    private ImageButton mImageButtonHotPerciGovorova;
    private ImageButton mImageButtonHotPerciChernomorsk;
    private ImageButton mImageButtonAlMezze;
    private ImageButton mImageButtonElevenDogs;
    private ImageButton mImageButtonKinza;
    private NavigationView mNavigationView;
    private Button mButtonFavoriteClient;
    private Button mButtonDelivery;
    private TextView mTextViewInfoLabel;
    private int prevCenterPos;

    CircleImageView mCircleImageView;

    private ArrayList<Restaurant> listRest = new ArrayList<Restaurant>();
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent = new Intent(this, ShareActivity.class);
        initViews();
        setListeners();
        initNavigationDrawer();
        initRecycler();

        GgroopApplication.getRepository().getInfoFromPosts(new Callback<List<Info>>() {
            @Override
            public void onSuccess(final List<Info> data) {
                mTextViewInfoLabel.setText(data.get(0).getRendered());
                myRecyclerAdapter.notifyInfoAdapter(data);
                addScrolListener(data);
            }

            @Override
            public void onError(Throwable throwable) {
            }
        });
    }

    private void setListeners() {
        mImageButtonOpenNB.setOnClickListener(this);
        mImageButtonAlMezze.setOnClickListener(this);
        mImageButtonElevenDogs.setOnClickListener(this);
        mImageButtonHotPerci.setOnClickListener(this);
        mImageButtonHotPerciChernomorsk.setOnClickListener(this);
        mImageButtonHotPerciGovorova.setOnClickListener(this);
        mImageButtonKinza.setOnClickListener(this);
        mButtonFavoriteClient.setOnClickListener(this);
        mButtonDelivery.setOnClickListener(this);
    }

    private void initViews() {
        mTextViewInfoLabel = (TextView) findViewById(R.id.textViewInfo);
        mCircleImageView = (CircleImageView) findViewById(R.id.profile_image);
        mSharesRecyclerView = (RecyclerView) findViewById(R.id.rcyList);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mImageButtonOpenNB = (ImageButton) findViewById(R.id.imageButtonNavigationPanel);
        mImageButtonAlMezze = (ImageButton) findViewById(R.id.imageButtonAlMezze);
        mImageButtonElevenDogs = (ImageButton) findViewById(R.id.imageButtonEleven);
        mImageButtonHotPerci = (ImageButton) findViewById(R.id.imageButtonCoroleva);
        mImageButtonHotPerciChernomorsk = (ImageButton) findViewById(R.id.imageButtonHotChernomors);
        mImageButtonHotPerciGovorova = (ImageButton) findViewById(R.id.imageButtonGovorova);
        mImageButtonKinza = (ImageButton) findViewById(R.id.imageButtonKinza);
        mButtonFavoriteClient = (Button) findViewById(R.id.buttonFavoriteClient);
        mButtonDelivery = (Button) findViewById(R.id.buttonDelivery);
        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
        View hView = mNavigationView.getHeaderView(0);
        mCircleImageView = (CircleImageView) hView.findViewById(R.id.profile_image);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.imageButtonNavigationPanel:
                mDrawerLayout.openDrawer(mNavigationView);
                getImageSharedPreferences();
                break;
            case R.id.buttonFavoriteClient:
                startActivity(new Intent(MainActivity.this, LogoActivity.class));
                break;
            case R.id.buttonDelivery:
                startActivity(new Intent(MainActivity.this, DeliveryActivity.class));
                break;
            case R.id.imageButtonAlMezze:
                listRest.clear();
                listRest.add(new Restaurant(getString(R.string.AlMezze), getString(R.string.AlMezzeAdress), getString(R.string.AlMezzeAbout), getString(R.string.AlMezzeURL), getString(R.string.AlMezzeFB)));
                intent.putExtra(rest, listRest);
                startActivity(intent);
                break;
            case R.id.imageButtonCoroleva:
                listRest.clear();
                listRest.add(new Restaurant(getString(R.string.CorolevaName), getString(R.string.CorolevaAdress), getString(R.string.CorolevaAbout), getString(R.string.CorolevaURL), getString(R.string.CorolevaFB)));
                intent.putExtra(rest, listRest);
                startActivity(intent);
                break;
            case R.id.imageButtonEleven:
                listRest.clear();
                listRest.add(new Restaurant(getString(R.string.ElevenDogsName), getString(R.string.ElevenDogsAdress), getString(R.string.ElevenDogsAbout), getString(R.string.ElevenDogsURL), getString(R.string.ElevenDogsFB)));
                intent.putExtra(rest, listRest);
                startActivity(intent);
                break;
            case R.id.imageButtonKinza:
                listRest.clear();
                listRest.add(new Restaurant(getString(R.string.KinzaName), getString(R.string.KinzaAdress), getString(R.string.KinzaAbout), getString(R.string.KinzaURL), getString(R.string.KinzaFB)));
                intent.putExtra(rest, listRest);
                startActivity(intent);
                break;
            case R.id.imageButtonGovorova:
                listRest.clear();
                listRest.add(new Restaurant(getString(R.string.GovorovaName), getString(R.string.GovorovaAdress), getString(R.string.GovorovaAbout), getString(R.string.GovorovaURL), getString(R.string.GovorovaFB)));
                intent.putExtra(rest, listRest);
                startActivity(intent);
                break;
            case R.id.imageButtonHotChernomors:
                listRest.clear();
                listRest.add(new Restaurant(getString(R.string.ChernomorskName), getString(R.string.ChernomorskAdress), getString(R.string.ChernomorskAbout), getString(R.string.ChernomorskURL), getString(R.string.ChernomorskFB)));
                intent.putExtra(rest, listRest);
                startActivity(intent);
                break;
        }
    }

    public void initRecycler() {
        final CarouselLayoutManager layoutManager = new CarouselLayoutManager(CarouselLayoutManager.HORIZONTAL, true);
        mSharesRecyclerView.setHasFixedSize(true);
        layoutManager.setPostLayoutListener(new CarouselZoomPostLayoutListener());
        myRecyclerAdapter = new InfoRecyclerViewAdapter(this, new ArrayList<Info>(), this);
        mSharesRecyclerView.setAdapter(myRecyclerAdapter);
        mSharesRecyclerView.setLayoutManager(layoutManager);
        mSharesRecyclerView.addOnScrollListener(new CenterScrollListener());
        mSharesRecyclerView.setFocusable(true);
        mSharesRecyclerView.requestFocus();
    }

    private void addScrolListener(final List<Info> data) {
        mSharesRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int center = mSharesRecyclerView.getWidth() / 2;
                View centerView = mSharesRecyclerView.findChildViewUnder(center, mSharesRecyclerView.getTop());
                int centerPos = mSharesRecyclerView.getChildAdapterPosition(centerView);
                if (prevCenterPos != centerPos) {
                    if (centerView != null) {
                        mTextViewInfoLabel.setText(data.get(centerPos).getRendered());
                    }
                    prevCenterPos = centerPos;
                }
            }
        });
    }

    public void initNavigationDrawer() {
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.favoriteClient:
                        startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                        break;
                    case R.id.delivery:
                        startActivity(new Intent(MainActivity.this, DeliveryActivity.class));
                        break;
                    case R.id.event:
                        startActivity(new Intent(MainActivity.this, RestaurantEventsActivity.class/*EventActivity.class*/));
                        break;
                    case R.id.about_us:
                        startActivity(new Intent(MainActivity.this, AboutUsActivity.class));
                        break;
                }
                return true;
            }
        });
    }

    public void getImageSharedPreferences() {
        SharedPreferences shre = PreferenceManager.getDefaultSharedPreferences(this);
        String previouslyEncodedImage = shre.getString(SAVED_IMAGE, "");

        if (!previouslyEncodedImage.equalsIgnoreCase("")) {
            byte[] b = Base64.decode(previouslyEncodedImage, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);
            mCircleImageView.setImageBitmap(bitmap);
        }
    }

    @Override
    public void onInfoItemClick(String sourceUrl) {
        intent = new Intent(this, ViewImageActivity.class);
        intent.putExtra("URL", sourceUrl);
        startActivity(intent);
    }
}
