package corn.uni.crazywell.business.internalcomponent;

import com.company.Plannifiable;

import java.util.List;

/**
 * Created by blacksheep on 14/06/15.
 */
public class Planning {
    List<Plannifiable> activities;

    public List<Plannifiable> getActivities() {
        return activities;
    }

    public void setActivities(List<Plannifiable> activities) {
        this.activities = activities;
    }
}
