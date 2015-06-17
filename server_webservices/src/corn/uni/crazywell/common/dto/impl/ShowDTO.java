package corn.uni.crazywell.common.dto.impl;

import corn.uni.crazywell.common.dto.DTO;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;


/**
 * Created by blacksheep on 16/06/15.
 */
public class ShowDTO implements DTO {
    private int id;
    private String name;
    private String description;
    private int priority;
    private Date creationDate;
    private byte[] image;
    private int actorNumber;
    private CoordinateDTO coordinate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public CoordinateDTO getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(CoordinateDTO coordinate) {
        this.coordinate = coordinate;
    }
}
