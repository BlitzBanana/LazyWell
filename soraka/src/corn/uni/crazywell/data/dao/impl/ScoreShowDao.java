package corn.uni.crazywell.data.dao.impl;

import corn.uni.crazywell.common.Bubble;
import corn.uni.crazywell.common.exception.DAOException;
import corn.uni.crazywell.data.dao.AbstractGenericDAO;
import corn.uni.crazywell.data.dao.ScoreDao;
import corn.uni.crazywell.data.entities.ShowScoreEntity;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.Query;

/**
 * Created by blacksheep on 16/06/15.
 */
@Named
@Stateless
public class ScoreShowDao extends AbstractGenericDAO<ShowScoreEntity> implements ScoreDao{
    public ScoreShowDao(){
        super(ShowScoreEntity.class);
    }

    @Override
    public boolean isVoted(Bubble bubble) throws DAOException {
        try {
            String rq = "select score.value from ShowScoreEntity score where score.showId = " + bubble.getBody().get(2).toString() + " and score.uuid = " + bubble.getBody().get(1).toString();

            final Query query = entityManager.createQuery(rq);
            return (query.getSingleResult() == null) ? false : true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("CUSTOM - failed to get all result. See internal error");
        }
    }
}
