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
import com.oleynikov.hp.ggroup.profile.ProfileActivity;

import java.io.ByteArrayOutputStream;

import static com.oleynikov.hp.ggroup.activity.RegistrationActivity.PICK_IMAGE_ID;
import static com.oleynikov.hp.ggroup.activity.RegistrationActivity.SAVED_IMAGE;

public class EditProfileActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton mImageButtonBackInProfile;
    ImageButton mImageButtonPhotoPickerEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        mImageButtonBackInProfile = (ImageButton) findViewById(R.id.imageButtonBackInProfile);
        mImageButtonBackInProfile.setOnClickListener(this);
        mImageButtonPhotoPickerEdit = (ImageButton) findViewById(R.id.imageButtonPhotoPickerEdit);
        mImageButtonPhotoPickerEdit.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.imageButtonBackInProfile:

                startActivity(new Intent(this, ProfileActivity.class));
                finish();


                break;
            case R.id.imageButtonPhotoPickerEdit:
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
                mImageButtonPhotoPickerEdit.setImageBitmap(bitmap);
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
