package corn.uni.crazywell.data.dao.impl;

import corn.uni.crazywell.data.dao.AbstractGenericDAO;
import corn.uni.crazywell.data.dao.GenericDAO;
import corn.uni.crazywell.data.entities.ShowEntity;

import javax.ejb.Stateless;

/**
 * Created by blacksheep on 16/06/15.
 */
@Stateless
public class ShowDao extends AbstractGenericDAO<ShowEntity> implements GenericDAO<ShowEntity> {
    public ShowDao(){
        super(ShowEntity.class);
    }
}
