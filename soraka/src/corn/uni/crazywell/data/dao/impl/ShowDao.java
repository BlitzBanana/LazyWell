package corn.uni.crazywell.data.dao.impl;

import corn.uni.crazywell.common.exception.DAOException;
import corn.uni.crazywell.data.dao.AbstractGenericDAO;
import corn.uni.crazywell.data.dao.GenericDAO;
import corn.uni.crazywell.data.entities.ShowEntity;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by blacksheep on 16/06/15.
 */
@Named("showDao")
@Stateless
public class ShowDao extends AbstractGenericDAO<ShowEntity> implements GenericDAO<ShowEntity>, corn.uni.crazywell.data.dao.ShowDao {
    public ShowDao(){
        super(ShowEntity.class);
    }

    public double getAverrageOfAllScores(int id) throws DAOException {
        double result;
        try{
            StringBuilder sb = new StringBuilder("select avg(score.value) from ShowScoreEntity score where score.showId =");
            sb.append(id);
            final Query query = entityManager.createQuery(sb.toString());
            return (query.getSingleResult() == null) ? 0 : (double)query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("CUSTOM failed to get all result. See internal error.");
        }
    }

}
