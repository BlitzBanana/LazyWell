package corn.uni.crazywell.data.dao.impl;

import corn.uni.crazywell.data.dao.AbstractGenericDAO;
import corn.uni.crazywell.data.entities.ShowEntity;

import javax.ejb.Stateless;
import javax.inject.Named;
import java.util.List;

/**
 * Created by blacksheep on 16/06/15.
 */
@Named("showDAO")
@Stateless
public class ShowDAO extends AbstractGenericDAO<ShowEntity> {
    public ShowDAO(){
        super(ShowEntity.class);
    }

    @Override
    public List<ShowEntity> findAll() {
        return null;
    }
}
