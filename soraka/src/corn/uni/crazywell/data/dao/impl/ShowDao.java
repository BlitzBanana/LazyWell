package corn.uni.crazywell.data.dao.impl;

import corn.uni.crazywell.common.dto.converter.DTOConverterLocal;
import corn.uni.crazywell.common.dto.converter.impl.ShowDTOConverter;
import corn.uni.crazywell.common.dto.impl.ShowDTO;
import corn.uni.crazywell.common.exception.DAOException;
import corn.uni.crazywell.data.dao.AbstractGenericDAO;
import corn.uni.crazywell.data.dao.GenericDAO;
import corn.uni.crazywell.data.dao.ShowDaoLocal;
import corn.uni.crazywell.data.entities.ShowEntity;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by blacksheep on 16/06/15.
 */
@Named("showDao")
@Stateless
public class ShowDAO extends AbstractGenericDAO<ShowEntity> implements GenericDAO<ShowEntity>, ShowDaoLocal {
    public ShowDAO(){
        super(ShowEntity.class);
    }

    public double getAverrageOfAllScores(final int id) throws DAOException {
        double result;
        try{
            final Query query = entityManager.createQuery("select avg(score.value) from ShowScoreEntity score where score.showId = :idShow");
            query.setParameter("idShow", id);
            return (query.getSingleResult() == null) ? 0 : (double)query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("CUSTOM failed to get all result. See internal error.");
        }
    }

    @Override
    public List<ShowDTO> getAvailableActivities(final int minuteBeforeNextBreak, final int walkingTime, final DTOConverterLocal<ShowEntity, ShowDTO> showDTOConverter) throws DAOException {
        try {
            final Query query = entityManager.createQuery("from ShowEntity show having show.duration < :minutesBeforeNextBreak");
            query.setParameter("minutesBeforeNextBreak", minuteBeforeNextBreak + walkingTime);
            final List<ShowEntity> shows = query.getResultList();
            final List<ShowDTO> showsDTO = new ArrayList<>();
            ShowDTO showDTO;
            for(ShowEntity entity : shows){
                showDTO = new ShowDTO();
                showDTOConverter.convert(entity, showDTO);
                showsDTO.add(showDTO);
            }
            return showsDTO;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("CUSTOM failed to get all result. See internal error.");
        }
    }
}
