package corn.uni.crazywell.data.dao.impl;

import corn.uni.crazywell.data.dao.AbstractGenericDAO;
import corn.uni.crazywell.data.entities.CoordinatesEntity;

import javax.ejb.Stateless;
import javax.inject.Named;

/**
 * Created by blacksheep on 17/06/15.
 */
@Named
@Stateless
public class CoordinateDAO extends AbstractGenericDAO<CoordinatesEntity> {
    public CoordinateDAO(){
        super(CoordinatesEntity.class);
    }
}
