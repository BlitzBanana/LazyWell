package com.lazywell.android.puydufou.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.facebook.FacebookSdk;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareButton;
import com.lazywell.android.puydufou.R;
import com.lazywell.android.puydufou.tools.BitmapUtils;

public class EntertainmentActivity extends AppCompatActivity
{
    static final int REQUEST_IMAGE_CAPTURE = 1;

    ImageView picture;
    Button takepicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_entertainment);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            if(imageBitmap != null)
                showSocialSharePopup(imageBitmap);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_entertainment, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_photo) {
            Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showSocialSharePopup(Bitmap picture)
    {
        MaterialDialog popup = new MaterialDialog.Builder(this)
                .title(R.string.social_view_title)
                .customView(R.layout.social_view, true)
                .show();
        SharePhoto photo = new SharePhoto.Builder()
                .setBitmap(picture)
                .build();
        SharePhotoContent content = new SharePhotoContent.Builder()
                .addPhoto(photo)
                .build();
        ShareButton shareButton = (ShareButton)popup.findViewById(R.id.FBbutton);
        shareButton.setShareContent(content);
    }
}
