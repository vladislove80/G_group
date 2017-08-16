package com.oleynikov.hp.g_group.activity;

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
import com.oleynikov.hp.g_group.R;
import com.oleynikov.hp.g_group.adapters.SharesRecyclerViewAdapter;
import com.oleynikov.hp.g_group.model.Info;
import com.oleynikov.hp.g_group.model.Restaurant;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.oleynikov.hp.g_group.activity.RegistrationActivity.SAVED_IMAGE;
import static com.oleynikov.hp.g_group.activity.SplashActivity.LIST_INFO;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String rest = "rest";
    private RecyclerView mSharesRecyclerView;
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
    private TextView mTextViewSharesTheme;
    private int prevCenterPos;


    CircleImageView mCircleImageView;

    private ArrayList<Restaurant> listRest = new ArrayList<Restaurant>();
    private Intent intent;
    private ArrayList<Info> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        intent = new Intent(this, ShareActivity.class);
        Intent i = getIntent();
//        list.clear();
        list = (ArrayList<Info>) i
                .getSerializableExtra(LIST_INFO);

        mTextViewSharesTheme = (TextView) findViewById(R.id.textViewSharesTheme);
        mCircleImageView = (CircleImageView) findViewById(R.id.profile_image);
        mSharesRecyclerView = (RecyclerView) findViewById(R.id.rcyList);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mImageButtonOpenNB = (ImageButton) findViewById(R.id.imageButtonNavigationPanel);
        mImageButtonOpenNB.setOnClickListener(this);
        mImageButtonAlMezze = (ImageButton) findViewById(R.id.imageButtonAlMezze);
        mImageButtonAlMezze.setOnClickListener(this);
        mImageButtonElevenDogs = (ImageButton) findViewById(R.id.imageButtonEleven);
        mImageButtonElevenDogs.setOnClickListener(this);
        mImageButtonHotPerci = (ImageButton) findViewById(R.id.imageButtonCoroleva);
        mImageButtonHotPerci.setOnClickListener(this);
        mImageButtonHotPerciChernomorsk = (ImageButton) findViewById(R.id.imageButtonHotChernomors);
        mImageButtonHotPerciChernomorsk.setOnClickListener(this);
        mImageButtonHotPerciGovorova = (ImageButton) findViewById(R.id.imageButtonGovorova);
        mImageButtonHotPerciGovorova.setOnClickListener(this);
        mImageButtonKinza = (ImageButton) findViewById(R.id.imageButtonKinza);
        mImageButtonKinza.setOnClickListener(this);
        mButtonFavoriteClient = (Button) findViewById(R.id.buttonFavoriteClient);
        mButtonFavoriteClient.setOnClickListener(this);
        mButtonDelivery = (Button) findViewById(R.id.buttonDelivery);
        mButtonDelivery.setOnClickListener(this);
        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
        View hView = mNavigationView.getHeaderView(0);
        mCircleImageView = (CircleImageView) hView.findViewById(R.id.profile_image);
        initNavigationDrawer();
        initRecycler();


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
        SharesRecyclerViewAdapter myRecyclerAdapter = new SharesRecyclerViewAdapter(this, list);
        mSharesRecyclerView.setAdapter(myRecyclerAdapter);
        mSharesRecyclerView.setLayoutManager(layoutManager);
        mSharesRecyclerView.addOnScrollListener(new CenterScrollListener());
        mSharesRecyclerView.setFocusable(true);
        mSharesRecyclerView.requestFocus();
        mTextViewSharesTheme.setText(list.get(mSharesRecyclerView.getWidth() / 2).getRendered());
        mSharesRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int center = mSharesRecyclerView.getWidth() / 2;
                View centerView = mSharesRecyclerView.findChildViewUnder(center, mSharesRecyclerView.getTop());
                int centerPos = mSharesRecyclerView.getChildAdapterPosition(centerView);

                if (prevCenterPos != centerPos) {

                    View prevView = mSharesRecyclerView.getLayoutManager().findViewByPosition(prevCenterPos);
                    if (prevView != null) {

                    }


                    if (centerView != null) {
                        mTextViewSharesTheme.setText(list.get(centerPos).getRendered());

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
                        startActivity(new Intent(MainActivity.this, EventActivity.class));

                        break;

                    case R.id.about_us:
                        startActivity(new Intent(MainActivity.this, AboutUsActivity.class));

                        break;


                }
                return true;
            }
        });


    }


    public void getImageSharedPreferences()

    {
        SharedPreferences shre = PreferenceManager.getDefaultSharedPreferences(this);
        String previouslyEncodedImage = shre.getString(SAVED_IMAGE, "");

        if (!previouslyEncodedImage.equalsIgnoreCase("")) {
            byte[] b = Base64.decode(previouslyEncodedImage, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);
            mCircleImageView.setImageBitmap(bitmap);
        }


    }


}
