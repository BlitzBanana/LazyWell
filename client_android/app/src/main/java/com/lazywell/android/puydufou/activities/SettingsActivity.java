package com.lazywell.android.puydufou.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TimePicker;

import com.gc.materialdesign.views.Slider;
import com.lazywell.android.puydufou.R;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Slider lunchSlider = (Slider) findViewById(R.id.lunch_slider);
        TimePicker lunchTimePicker = (TimePicker) findViewById((R.id.lunch_time_picker));

        lunchSlider.setValue(25);
        lunchTimePicker.setIs24HourView(true);
        lunchTimePicker.setCurrentHour(12);
        lunchTimePicker.setCurrentMinute(30);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
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
}
