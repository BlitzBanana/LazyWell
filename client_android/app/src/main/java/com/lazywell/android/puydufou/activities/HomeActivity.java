package com.lazywell.android.puydufou.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.lazywell.android.puydufou.R;
import com.lazywell.android.puydufou.entities.persistent.CoordinatesEntity;
import com.lazywell.android.puydufou.entities.persistent.ScheduleEntity;
import com.lazywell.android.puydufou.entities.persistent.ScheduleSessionEntity;
import com.lazywell.android.puydufou.entities.persistent.SessionEntity;
import com.lazywell.android.puydufou.entities.persistent.ShowEntity;

import java.util.Date;

public class HomeActivity extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        findViewById(R.id.background_layout).setBackgroundResource(R.drawable.background);
        findViewById(R.id.button_discover).setOnClickListener(this);
        findViewById(R.id.button_plan).setOnClickListener(this);
        findViewById(R.id.button_entertainment).setOnClickListener(this);

        /*CoordinatesEntity coordinates = new CoordinatesEntity(10, 10);
        coordinates.save();

        Date duration = new Date();
        duration.setHours(2);
        duration.setMinutes(0);

        ShowEntity show = new ShowEntity();
        show.setName("Spectacle 1");
        show.setActorNumber(2);
        show.setCoordinates(coordinates);
        show.setCreationDate(new Date());
        show.setDescription("Description du spectacle 1");
        show.setImage(null);
        show.setPriority(1);
        show.setScore(4.5);
        show.setDuration(duration);
        show.save();

        SessionEntity session = new SessionEntity(new Date(), show);
        session.save();

        ScheduleEntity schedule = ScheduleEntity.findById(ScheduleEntity.class, 1l);

        if(schedule == null) {
            schedule = new ScheduleEntity("local");
            schedule.save();
        }

        ScheduleSessionEntity scheduleSession = new ScheduleSessionEntity(schedule, session);
        scheduleSession.save();*/
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
