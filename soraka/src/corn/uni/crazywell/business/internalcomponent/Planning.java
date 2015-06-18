package corn.uni.crazywell.business.internalcomponent;

import corn.uni.crazywell.common.dto.impl.SessionDTO;

import java.util.List;

/**
 * Created by blacksheep on 14/06/15.
 */
public class Planning {
    List<SessionDTO> activities;

    public List<SessionDTO> getActivities() {
        return activities;
    }

    public void setActivities(List<SessionDTO> activities) {
        this.activities = activities;
    }
}
