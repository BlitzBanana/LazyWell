package com.lazywell.android.puydufou.entities.persistent;

import android.graphics.Bitmap;

import com.lazywell.android.puydufou.entities.ISchedulable;
import com.lazywell.android.puydufou.tools.BitmapUtils;
import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by victor on 16/06/2015.
 */
public class ShowEntity extends SugarRecord<ShowEntity> implements ISchedulable {

    protected String name;
    protected String description;
    protected int priority;
    protected Date creationDate;
    protected byte[] image;
    protected int actorNumber;
    protected double score;
    protected CoordinatesEntity coordinates;

    public ShowEntity(){}

    public ShowEntity(
            String name,
            String description,
            int priority,
            Date creationDate,
            byte[] image,
            int actorNumber,
            double score,
            CoordinatesEntity coordinates){
        this.name = name;
        this.description = description;
        this.priority = priority;
        this.creationDate = creationDate;
        this.image = image;
        this.actorNumber = actorNumber;
        this.score = score;
        this.coordinates = coordinates;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getActorNumber() {
        return actorNumber;
    }

    public void setActorNumber(int actorNumber) {
        this.actorNumber = actorNumber;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public CoordinatesEntity getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(CoordinatesEntity coordinates) {
        this.coordinates = coordinates;
    }

    public List<SessionEntity> getSessionEntities() {
        return SessionEntity.find(SessionEntity.class, "showEntity = ?", this.getId().toString());
    }
}