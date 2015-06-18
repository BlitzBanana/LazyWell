package corn.uni.crazywell.data.dao.impl;

import corn.uni.crazywell.common.exception.DAOException;
import corn.uni.crazywell.data.dao.AbstractGenericDAO;
import corn.uni.crazywell.data.dao.GenericDAO;
import corn.uni.crazywell.data.dao.ListDAO;
import corn.uni.crazywell.data.entities.ShopEntity;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.Query;

/**
 * Created by blacksheep on 16/06/15.
 */
@Named("shopDao")
@Stateless
public class ShopDAO extends AbstractGenericDAO<ShopEntity> implements GenericDAO<ShopEntity>, ListDAO {
    public ShopDAO(){
        super(ShopEntity.class);
    }

    @Override
    public double getAverrageOfAllScores(int id) throws DAOException {
        double result;
        try{
            StringBuilder sb = new StringBuilder("select avg(score.value) from ShopScoreEntity score where score.shopId =");
            sb.append(id);
            final Query query = entityManager.createQuery(sb.toString());
            return (query.getSingleResult() == null) ? 0 : (double)query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("CUSTOM failed to get all result. See internal error.");
        }
    }

}
