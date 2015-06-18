package corn.uni.crazywell.data.entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.Arrays;

/**
 * Created by blacksheep on 17/06/15.
 */
@Entity
@Table(name = "show", schema = "", catalog = "lazywell")
public class ShowEntity {
    private int id;
    private String name;
    private String description;
    private int priority;
    private Date creationDate;
    private byte[] image;
    private int actorNumber;
    private int coordinateId;
    private long duration;

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, insertable = true, updatable = true, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "description", nullable = true, insertable = true, updatable = true, length = 1000)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "priority", nullable = false, insertable = true, updatable = true)
    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Basic
    @Column(name = "duration", nullable = false, insertable = true, updatable = true)
    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    @Basic
    @Column(name = "creation_date", nullable = false, insertable = true, updatable = true)
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Basic
    @Column(name = "image", nullable = true, insertable = true, updatable = true)
    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Basic
    @Column(name = "actor_number", nullable = false, insertable = true, updatable = true)
    public int getActorNumber() {
        return actorNumber;
    }

    public void setActorNumber(int actorNumber) {
        this.actorNumber = actorNumber;
    }

    @Basic
    @Column(name = "coordinate_id")
    public int getCoordinateId() {
        return coordinateId;
    }

    public void setCoordinateId(int coordinateId) {
        this.coordinateId = coordinateId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShowEntity entity = (ShowEntity) o;

        if (id != entity.id) return false;
        if (priority != entity.priority) return false;
        if (duration != entity.duration) return false;
        if (coordinateId != entity.coordinateId) return false;
        if (name != null ? !name.equals(entity.name) : entity.name != null) return false;
        if (description != null ? !description.equals(entity.description) : entity.description != null) return false;
        if (creationDate != null ? !creationDate.equals(entity.creationDate) : entity.creationDate != null)
            return false;
        if (!Arrays.equals(image, entity.image)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + priority;
        result = 31 * result + (int) (duration ^ (duration >>> 32));
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        result = 31 * result + (image != null ? Arrays.hashCode(image) : 0);
        result = 31 * result + actorNumber;
        result = 31 * result + coordinateId;
        return result;
    }
}
