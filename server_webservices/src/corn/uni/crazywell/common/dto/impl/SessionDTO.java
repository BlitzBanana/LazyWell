package corn.uni.crazywell.common.dto.impl;

import corn.uni.crazywell.common.dto.DTO;

import java.util.Date;


/**
 * Created by blacksheep on 16/06/15.
 */
public class SessionDTO implements DTO {
    private int id;
    private Date time;
    private int showId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getShowId() {
        return showId;
    }

    public void setShowId(int showId) {
        this.showId = showId;
    }
}
