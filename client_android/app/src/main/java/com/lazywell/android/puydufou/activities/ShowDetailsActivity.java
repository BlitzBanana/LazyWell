package com.lazywell.android.puydufou.activities;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.lazywell.android.puydufou.R;
import com.lazywell.android.puydufou.adapters.DialogRateAdapter;
import com.lazywell.android.puydufou.business.Rate;
import com.lazywell.android.puydufou.entities.persistent.ShowEntity;
import com.lazywell.android.puydufou.tools.BitmapUtils;

public class ShowDetailsActivity extends AppCompatActivity
{

    Button score;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);
        Long showId = getIntent().getLongExtra("showId", 1);
        ShowEntity show = ShowEntity.findById(ShowEntity.class, showId);
        score = (Button) findViewById(R.id.buttonScore);
        score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRatePopup();
            }
        });

        TextView nameshow = (TextView)findViewById(R.id.showname);
        nameshow.setText(show.getName());

        TextView moy = (TextView) findViewById(R.id.moyenne);
        moy.setText(String.valueOf(show.getScore()));

        TextView actor = (TextView) findViewById(R.id.nbrActeur);
        actor.setText(String.valueOf(show.getActorNumber()));

        TextView description = (TextView) findViewById(R.id.descriptionShow);
        description.setText(show.getDescription());

        Bitmap picture = BitmapUtils.getImage(show.getImage());

        if(picture == null)
            picture = BitmapFactory.decodeResource(getResources(), R.drawable.no_image_found);

        ImageView pictureShow = (ImageView) findViewById(R.id.imageShow);
        pictureShow.setImageBitmap(picture);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showRatePopup(){
        Rate[] rates = Rate.values();

        final DialogRateAdapter adapter = new DialogRateAdapter(this, rates);
        new MaterialDialog.Builder(this)
                .title(R.string.dialog_rate_title)
                .adapter(adapter,
                        new MaterialDialog.ListCallback() {
                            @Override
                            public void onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                                Toast.makeText(ShowDetailsActivity.this, "Clicked item " + adapter.getItem(which), Toast.LENGTH_SHORT).show();
                            }
                        })
                .show();
    }
}
