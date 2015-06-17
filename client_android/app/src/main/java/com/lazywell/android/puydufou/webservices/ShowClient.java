package com.lazywell.android.puydufou.webservices;

import com.lazywell.android.puydufou.activities.ShowDetailsActivity;
import com.lazywell.android.puydufou.business.Rate;
import com.lazywell.android.puydufou.entities.persistent.ShowEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by victor on 16/06/2015.
 */
public class ShowClient {

    public List<ShowEntity> getShows(){

        // Use asyncTask and KSOAP to retrieve all ShowEntities
        List<ShowEntity> showEntities = new ArrayList<>();

        // Save all in database
        for (ShowEntity showEntity : showEntities)
            showEntity.save();

        showEntities = ShowEntity.listAll(ShowEntity.class);

        return showEntities;
    }

    public void RateShow (Rate rate)
    {

    }
}
