package com.lazywell.android.puydufou.entities.persistent;

import android.graphics.Bitmap;

import com.lazywell.android.puydufou.entities.ISchedulable;
import com.slimgears.slimrepo.core.annotations.GenerateEntity;

import java.util.Date;
import java.util.List;

/**
 * Created by victor on 16/06/2015.
 */
@GenerateEntity
public class AbstractShowEntity implements ISchedulable {
    protected int id;
    protected String name;
    protected String description;
    protected int priority;
    protected List<AbstractSessionEntity> sessions;
    protected Date creationDate;
    protected Bitmap image;
    protected int actorNumber;
    protected double score;
    protected AbstractCoordinatesEntity coordinates;
}
