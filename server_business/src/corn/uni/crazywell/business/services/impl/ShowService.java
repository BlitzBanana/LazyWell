package corn.uni.crazywell.business.services.impl;

import corn.uni.crazywell.business.services.AbstractGenericService;
import corn.uni.crazywell.data.dao.GenericDAO;
import corn.uni.crazywell.data.entities.ShowEntity;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by blacksheep on 16/06/15.
 */
@Named
@Stateless
public class ShowService extends AbstractGenericService<ShowEntity> {
    @Inject
    private GenericDAO<ShowEntity> showDAO;

    public ShowService() {
        super.genericDAO = this.showDAO;
    }

    @Override
    public List<ShowEntity> findAll() {
        return showDAO.findAll();
    }
}
