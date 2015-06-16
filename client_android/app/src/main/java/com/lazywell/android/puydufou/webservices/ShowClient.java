package com.lazywell.android.puydufou.webservices;

import com.lazywell.android.puydufou.entities.persistent.ShowEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by victor on 16/06/2015.
 */
public class ShowClient {

    public ShowEntity[] getShows(){

        // Use asyncTask and KSOAP to retrieve all ShowEntities
        List<ShowEntity> showEntities = new ArrayList<>();

        // Save all in database
        for (ShowEntity showEntity : showEntities)
            showEntity.save();

        return null;
    }
}
