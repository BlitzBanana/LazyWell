package corn.uni.crazywell.data.dao.impl;

import corn.uni.crazywell.data.dao.AbstractGenericDAO;
import corn.uni.crazywell.data.entities.SessionEntity;

/**
 * Created by blacksheep on 16/06/15.
 */
public class SessionDAO extends AbstractGenericDAO<SessionEntity>{
    public SessionDAO(){
        super(SessionEntity.class);
    }
}
