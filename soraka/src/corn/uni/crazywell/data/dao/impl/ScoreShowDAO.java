package corn.uni.crazywell.data.dao.impl;

import corn.uni.crazywell.data.dao.AbstractGenericDAO;
import corn.uni.crazywell.data.entities.ShowScoreEntity;

/**
 * Created by blacksheep on 16/06/15.
 */
public class ScoreShowDAO extends AbstractGenericDAO<ShowScoreEntity> {
    public ScoreShowDAO(){
        super(ShowScoreEntity.class);
    }
}
