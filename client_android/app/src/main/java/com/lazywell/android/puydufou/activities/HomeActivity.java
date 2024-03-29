package com.lazywell.android.puydufou.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.lazywell.android.puydufou.R;
import com.lazywell.android.puydufou.entities.persistent.CoordinatesEntity;
import com.lazywell.android.puydufou.entities.persistent.ScheduleEntity;
import com.lazywell.android.puydufou.entities.persistent.ScheduleSessionEntity;
import com.lazywell.android.puydufou.entities.persistent.SessionEntity;
import com.lazywell.android.puydufou.entities.persistent.ShowEntity;

import java.util.Date;
import java.util.List;

public class HomeActivity extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        findViewById(R.id.background_layout).setBackgroundResource(R.drawable.background);
        findViewById(R.id.button_discover).setOnClickListener(this);
        findViewById(R.id.button_plan).setOnClickListener(this);
        findViewById(R.id.button_entertainment).setOnClickListener(this);
        findViewById(R.id.button_recommended_plan).setOnClickListener(this);
        findViewById(R.id.button_resto).setOnClickListener(this);

        /*for(int i=0; i<5; i++) {
            CoordinatesEntity coordinates = new CoordinatesEntity(12, 12);
            coordinates.save();

            Date duration = new Date();
            duration.setHours(2);
            duration.setMinutes(0);

            ShowEntity show = new ShowEntity();
            show.setName("Spectacle " + i);
            show.setActorNumber(2);
            show.setCoordinates(coordinates);
            show.setCreationDate(new Date());
            show.setDescription("Description du spectacle " + i);
            show.setImage(null);
            show.setPriority(1);
            show.setScore(i);
            show.setDuration(duration);
            show.save();

            Date sessionDate = new Date();
            sessionDate.setHours(i);
            sessionDate.setMinutes(0);
            SessionEntity session = new SessionEntity(sessionDate, show);
            session.save();

            sessionDate = new Date();
            sessionDate.setHours(i + 3);
            sessionDate.setMinutes(0);
            session = new SessionEntity(sessionDate, show);
            session.save();

            sessionDate = new Date();
            sessionDate.setHours(i+6);
            sessionDate.setMinutes(0);
            session = new SessionEntity(sessionDate, show);
            session.save();

            ScheduleEntity schedule = ScheduleEntity.findById(ScheduleEntity.class, 1l);

            if (schedule == null) {
                schedule = new ScheduleEntity("local");
                schedule.save();
            }
        }*/

        List<SessionEntity> sessionEntities = SessionEntity.listAll(SessionEntity.class);
        for(SessionEntity session : sessionEntities)
            Log.d("DEBUG SESSION", session.getShow().getName() + "   " + session.getTime().toString());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_discover:
                startActivity(new Intent(this, ShowPlanningActivity.class));
                break;
            case R.id.button_plan:
                startActivity(new Intent(this, PlanningCreatorActivity.class));
                break;
            case R.id.button_recommended_plan:
                startActivity(new Intent(this, RecommendedPlanningActivity.class));
                break;
            case R.id.button_entertainment:
                startActivity(new Intent(this, EntertainmentActivity.class));
                break;
            case R.id.button_resto:{
                startActivity(new Intent(this, RestaurantShopActivity.class));
            }
        }
    }
}
