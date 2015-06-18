package com.lazywell.android.puydufou.activities;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.gc.materialdesign.views.Slider;
import com.lazywell.android.puydufou.R;
import com.lazywell.android.puydufou.tools.IntegerUtils;

public class SettingsActivity extends PreferenceActivity implements View.OnClickListener {

    SharedPreferences sharedPref;
    TextView startDateView;
    TextView endDateView;
    TextView lunchDateView;
    Slider lunchDurationSlider;
    Slider breakCountSlider;
    Slider breakDurationSlider;
    Slider breakVariationSlider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // Value TextViews
        final TextView lunchDurationValue = (TextView) findViewById(R.id.settings_value_lunch_duration);
        final TextView breakCountValue = (TextView) findViewById(R.id.settings_value_break_count);
        final TextView breakDurationValue = (TextView) findViewById(R.id.settings_value_break_duration);
        final TextView breakVariationValue = (TextView) findViewById(R.id.settings_value_break_variation);
        // Time TextViews
        startDateView = (TextView) findViewById(R.id.arrive_date_label);
        endDateView = (TextView) findViewById(R.id.end_date_label);
        lunchDateView = (TextView) findViewById(R.id.lunch_date_label);
        // Sliders
        lunchDurationSlider = (Slider) findViewById(R.id.lunch_slider);
        breakCountSlider = (Slider) findViewById(R.id.break_count_slider);
        breakDurationSlider = (Slider) findViewById(R.id.break_duration_slider);
        breakVariationSlider = (Slider) findViewById(R.id.break_variation_slider);

        // Setup time textViews listeners
        startDateView.setOnClickListener(this);
        endDateView.setOnClickListener(this);
        lunchDateView.setOnClickListener(this);

        // Setup time sliders listeners
        lunchDurationSlider.setOnValueChangedListener(
                new Slider.OnValueChangedListener() {
                    @Override
                    public void onValueChanged(int value) {
                        lunchDurationValue.setText(String.valueOf(value) + " minutes");
                    }
                }
        );
        breakCountSlider.setOnValueChangedListener(
                new Slider.OnValueChangedListener() {
                    @Override
                    public void onValueChanged(int value) {
                        breakCountValue.setText(String.valueOf(value));
                    }
                }
        );
        breakDurationSlider.setOnValueChangedListener(
                new Slider.OnValueChangedListener() {
                    @Override
                    public void onValueChanged(int value) {
                        breakDurationValue.setText(String.valueOf(value) + " minutes");
                    }
                }
        );
        breakVariationSlider.setOnValueChangedListener(
                new Slider.OnValueChangedListener() {
                    @Override
                    public void onValueChanged(int value) {
                        breakVariationValue.setText(String.valueOf(value) + " minutes");
                    }
                }
        );

        sharedPref = this.getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);

        String startTime = sharedPref.getString(getString(R.string.saved_start_time), getString(R.string.default_start_time));
        String endTime = sharedPref.getString(getString(R.string.saved_end_time), getString(R.string.default_end_time));
        String lunchTime = sharedPref.getString(getString(R.string.saved_lunch_time), getString(R.string.default_lunch_time));
        int lunchDuration = sharedPref.getInt(getString(R.string.saved_lunch_duration), 40);
        int breakCount = sharedPref.getInt(getString(R.string.saved_break_count), 2);
        int breakDuration = sharedPref.getInt(getString(R.string.saved_break_duration), 25);
        int breakVariation = sharedPref.getInt(getString(R.string.saved_break_variation), 10);

        // Setup time textViews default values
        startDateView.setText(startTime);
        endDateView.setText(endTime);
        lunchDateView.setText(lunchTime);

        // Setup sliders default values
        lunchDurationSlider.setValue(lunchDuration);
        breakCountSlider.setValue(breakCount);
        breakDurationSlider.setValue(breakDuration);
        breakVariationSlider.setValue(breakVariation);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return item.getItemId() == R.id.action_settings || super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.arrive_date_label:
                showTimePickerPopup(startDateView);
                break;
            case R.id.end_date_label:
                showTimePickerPopup(endDateView);
                break;
            case R.id.lunch_date_label:
                showTimePickerPopup(lunchDateView);
                break;
        }
    }

    private void showTimePickerPopup(final TextView textView){
        int hours = getHourFromString(textView.getText().toString());
        int minutes = getMinutesFromString(textView.getText().toString());

        TimePickerDialog tpd = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        textView.setText(IntegerUtils.to2charString(hourOfDay) + ":" + IntegerUtils.to2charString(minute));
                        checkDateValidity(textView);
                    }
                }, hours, minutes, true);
        tpd.show();
    }

    private void checkDateValidity(TextView priorityItem){
        int startHours = getHourFromString(startDateView.getText().toString());
        int startMinutes = getMinutesFromString(startDateView.getText().toString());

        int endHours = getHourFromString(endDateView.getText().toString());
        int endMinutes = getMinutesFromString(endDateView.getText().toString());

        int lunchHours = getHourFromString(lunchDateView.getText().toString());
        int lunchMinutes = getMinutesFromString(lunchDateView.getText().toString());

        switch (priorityItem.getId()){
            case R.id.arrive_date_label:
                if(startHours > lunchHours){
                    lunchHours = startHours;
                    lunchMinutes = startMinutes;
                }

                if(startHours > endHours){
                    endHours = startHours;
                    endMinutes = startMinutes;
                }

                if(startHours == lunchHours){
                    if(startMinutes > lunchMinutes)
                        lunchMinutes = startMinutes;
                }

                if(startHours == endHours){
                    if(startMinutes > endMinutes)
                        endMinutes = startMinutes;
                }
                lunchDateView.setText(IntegerUtils.to2charString(lunchHours) + ":" + IntegerUtils.to2charString(lunchMinutes));
                endDateView.setText(IntegerUtils.to2charString(endHours) + ":" + IntegerUtils.to2charString(endMinutes));
                break;
            case R.id.end_date_label:
                if(endHours < lunchHours){
                    lunchHours = endHours;
                    lunchMinutes = endMinutes;
                }

                if(endHours < startHours){
                    startHours = endHours;
                    startMinutes = endMinutes;
                }

                if(endHours == lunchHours){
                    if(endMinutes < lunchMinutes)
                        lunchMinutes = endMinutes;
                }

                if(endHours == startHours){
                    if(endMinutes < startMinutes)
                        startMinutes = endMinutes;
                }
                lunchDateView.setText(IntegerUtils.to2charString(lunchHours) + ":" + IntegerUtils.to2charString(lunchMinutes));
                startDateView.setText(IntegerUtils.to2charString(startHours) + ":" + IntegerUtils.to2charString(startMinutes));
                break;
            case R.id.lunch_date_label:
                if(lunchHours < startHours){
                    startHours = lunchHours;
                    startMinutes = lunchMinutes;
                }

                if(lunchHours > endHours){
                    endHours = lunchHours;
                    endMinutes = lunchMinutes;
                }

                if(lunchHours == startHours){
                    if(lunchMinutes < startMinutes)
                        startMinutes = lunchMinutes;
                }

                if(lunchHours == endHours){
                    if(lunchMinutes > endMinutes)
                        endMinutes = lunchMinutes;
                }
                startDateView.setText(IntegerUtils.to2charString(startHours) + ":" + IntegerUtils.to2charString(startMinutes));
                endDateView.setText(IntegerUtils.to2charString(endHours) + ":" + IntegerUtils.to2charString(endMinutes));
                break;
        }
    }

    private int getHourFromString(String time){
        return Integer.parseInt(time.split(":")[0]);
    }

    private int getMinutesFromString(String time){
        return Integer.parseInt(time.split(":")[1]);
    }

    @Override
    protected void onPause() {
        saveSettings();
        super.onPause();
    }

    @Override
    protected void onStop() {
        saveSettings();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        saveSettings();
        super.onDestroy();
    }



    private void saveSettings(){
        String startTime    = startDateView.getText().toString();
        String lunchTime    = lunchDateView.getText().toString();
        String endTime      = endDateView.getText().toString();
        int lunchDuration   = lunchDurationSlider.getValue();
        int breakCount      = breakCountSlider.getValue();
        int breakDuration   = breakDurationSlider.getValue();
        int breakVariation  = breakVariationSlider.getValue();

        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(getString(R.string.saved_start_time), startTime);
        editor.putString(getString(R.string.saved_end_time), endTime);
        editor.putString(getString(R.string.saved_lunch_time), lunchTime);
        editor.putInt(getString(R.string.saved_lunch_duration), lunchDuration);
        editor.putInt(getString(R.string.saved_break_count), breakCount);
        editor.putInt(getString(R.string.saved_break_duration), breakDuration);
        editor.putInt(getString(R.string.saved_break_variation), breakVariation);
        editor.apply();
    }
}
