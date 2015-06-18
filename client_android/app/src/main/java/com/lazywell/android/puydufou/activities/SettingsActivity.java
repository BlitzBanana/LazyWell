package com.lazywell.android.puydufou.activities;

import android.app.TimePickerDialog;
import android.os.Bundle;
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

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {

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

        // Setup time textViews default values
        startDateView.setText("09:30");
        endDateView.setText("18:00");
        lunchDateView.setText("12:30");

        // Setup sliders default values
        lunchDurationSlider.setValue(25);
        breakCountSlider.setValue(4);
        breakDurationSlider.setValue(25);
        breakVariationSlider.setValue(10);
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
}
