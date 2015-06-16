package corn.uni.crazywell.common.object;

import java.sql.Time;

/**
 * Created by Thanith on 16/06/2015.
 */
public class Session {
    private int id;
    private Time date;

    public Time getDate() {
        return date;
    }

    public void setDate(Time date) {
        this.date = date;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
