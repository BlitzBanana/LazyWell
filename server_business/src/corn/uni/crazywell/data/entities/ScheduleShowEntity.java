package corn.uni.crazywell.data.entities;

import javax.persistence.*;

/**
 * Created by blacksheep on 16/06/15.
 */
@Entity
@Table(name = "schedule_show", schema = "", catalog = "lazywell")
public class ScheduleShowEntity {
    private int id;
    private int scheduleId;
    private int showId;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "schedule_id")
    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    @Basic
    @Column(name = "show_id")
    public int getShowId() {
        return showId;
    }

    public void setShowId(int showId) {
        this.showId = showId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ScheduleShowEntity that = (ScheduleShowEntity) o;

        if (id != that.id) return false;
        if (scheduleId != that.scheduleId) return false;
        if (showId != that.showId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + scheduleId;
        result = 31 * result + showId;
        return result;
    }
}
