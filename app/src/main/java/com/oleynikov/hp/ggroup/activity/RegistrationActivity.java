package com.oleynikov.hp.ggroup.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.ImageButton;

import com.oleynikov.hp.ggroup.R;
import com.oleynikov.hp.ggroup.other.ImagePicker;

import java.io.ByteArrayOutputStream;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String SAVED_IMAGE = "SavedImage";
    private ImageButton mButtonBackInLogo;
    private ImageButton mImageButtonPhotoPicker;
    private SharedPreferences sharedPreferences;
    public static final int PICK_IMAGE_ID = 1;
    public static final String PREFS_NAME = "UserImage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        mButtonBackInLogo = (ImageButton) findViewById(R.id.imageButtonBackInLogo);
        mButtonBackInLogo.setOnClickListener(this);
        mImageButtonPhotoPicker = (ImageButton) findViewById(R.id.imageButtonPhotoPicker);
        mImageButtonPhotoPicker.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageButtonBackInLogo:

                startActivity(new Intent(this, LogoActivity.class));
                finish();

                break;
            case R.id.imageButtonPhotoPicker:
                Intent chooseImageIntent = ImagePicker.getPickImageIntent(this);

                startActivityForResult(chooseImageIntent, PICK_IMAGE_ID);


                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case PICK_IMAGE_ID:
                Bitmap bitmap = ImagePicker.getImageFromResult(this, resultCode, data);
                mImageButtonPhotoPicker.setImageBitmap(bitmap);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] b = baos.toByteArray();
                String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
                SharedPreferences shre = PreferenceManager.getDefaultSharedPreferences(this);
                SharedPreferences.Editor edit = shre.edit();
                edit.putString(SAVED_IMAGE, encodedImage);
                edit.commit();

                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
                break;
        }
    }
}
