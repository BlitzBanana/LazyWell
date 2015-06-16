package corn.uni.crazywell.common.object;

import com.sun.mail.iap.ByteArray;

import java.sql.Time;
import java.util.List;

/**
 * Created by Thanith on 16/06/2015.
 */
public class Show{
    private int id;
    private String name;
    private String description;
    private int priority;
    private Time date;
    private ByteArray image;
    private int actorNumber;
    private Coordinates coordinates;

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }

    private List<Session> sessions;

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

    public Time getDate() {
        return date;
    }

    public void setDate(Time date) {
        this.date = date;
    }

    public ByteArray getImage() {
        return image;
    }

    public void setImage(ByteArray image) {
        this.image = image;
    }

    public int getActorNumber() {
        return actorNumber;
    }

    public void setActorNumber(int actorNumber) {
        this.actorNumber = actorNumber;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    private long score;

}
