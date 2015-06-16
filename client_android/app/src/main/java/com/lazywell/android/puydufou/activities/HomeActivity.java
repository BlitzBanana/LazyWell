package com.lazywell.android.puydufou.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.lazywell.android.puydufou.R;
import com.lazywell.android.puydufou.entities.persistent.ShowEntity;

public class HomeActivity extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        findViewById(R.id.background_layout).setBackgroundResource(R.drawable.background);
        findViewById(R.id.button_discover).setOnClickListener(this);
        findViewById(R.id.button_plan).setOnClickListener(this);
        findViewById(R.id.button_entertainment).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_discover:
                Toast.makeText(this, "Discover !", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, ShowPlanningActivity.class));
                break;
            case R.id.button_plan:
                Toast.makeText(this, "Plan !", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, PlanningCreatorActivity.class));
                break;
            case R.id.button_entertainment:
                Toast.makeText(this, "Entertainment !", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
