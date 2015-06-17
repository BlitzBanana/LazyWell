package corn.uni.crazywell.data.dao.impl;

import corn.uni.crazywell.data.dao.AbstractGenericDAO;
import corn.uni.crazywell.data.entities.CoordinatesEntity;

import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;
import javax.inject.Named;

/**
 * Created by blacksheep on 17/06/15.
 */
@Named
@Stateless
public class CoordinateDao extends AbstractGenericDAO<CoordinatesEntity> {
    public CoordinateDao(){
        super(CoordinatesEntity.class);
    }
}
