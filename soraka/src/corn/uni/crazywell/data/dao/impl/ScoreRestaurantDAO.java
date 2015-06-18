package corn.uni.crazywell.data.dao.impl;

import corn.uni.crazywell.common.Bubble;
import corn.uni.crazywell.common.exception.DAOException;
import corn.uni.crazywell.data.dao.AbstractGenericDAO;
import corn.uni.crazywell.data.dao.GenericDAO;
import corn.uni.crazywell.data.dao.ScoreDAO;
import corn.uni.crazywell.data.entities.ShowEntity;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.Query;

/**
 * Created by Thanith on 18/06/2015.
 */
@Named("scoreRestaurantDao")
@Stateless
public class ScoreRestaurantDAO extends AbstractGenericDAO<ShowEntity> implements GenericDAO<ShowEntity>, ScoreDAO {
    @Override
    public boolean isVoted(Bubble bubble) throws DAOException {
        try{
            final Query query = entityManager.createQuery("select score.value from RestaurantScoreEntity score where score.restaurantId = :idRestaurant and score.uuid = :uuid");
            query.setParameter("idRestaurant", bubble.getBody().get(2).toString());
            query.setParameter("uuid", bubble.getBody().get(1).toString());
            return (query.getSingleResult() == null) ? true : false;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("CUSTOM failed to get all result. See internal error.");
        }
    }
}
