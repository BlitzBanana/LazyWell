package corn.uni.crazywell.common.dto.converter.impl;

import corn.uni.crazywell.common.dto.converter.DTOConverterLocal;
import corn.uni.crazywell.common.dto.impl.CoordinateDTO;
import corn.uni.crazywell.common.dto.impl.SessionDTO;
import corn.uni.crazywell.common.dto.impl.ShowDTO;
import corn.uni.crazywell.common.dto.impl.ShowScoreDTO;
import corn.uni.crazywell.common.exception.ConversionException;
import corn.uni.crazywell.common.exception.DAOException;
import corn.uni.crazywell.data.dao.AbstractGenericDAO;
import corn.uni.crazywell.data.dao.ListDao;
import corn.uni.crazywell.data.dao.impl.ShowDao;
import corn.uni.crazywell.data.entities.CoordinatesEntity;
import corn.uni.crazywell.data.entities.SessionEntity;
import corn.uni.crazywell.data.entities.ShowEntity;
import corn.uni.crazywell.data.entities.ShowScoreEntity;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by blacksheep on 16/06/15.
 */
@Named
@Stateless
public class ShowDTOConverter implements DTOConverterLocal<ShowEntity, ShowDTO> {

    @Inject private AbstractGenericDAO<CoordinatesEntity> coordinateDao;
    @Inject private AbstractGenericDAO<SessionEntity> sessionDao;
    @Inject private DTOConverterLocal<CoordinatesEntity, CoordinateDTO> coordinatesDTOConverter;
    @Inject private DTOConverterLocal<SessionEntity, SessionDTO> sessionDTOConverter;
    @Inject private DTOConverterLocal<ShowScoreEntity, ShowScoreDTO> scoreShowDTOConverter;
    @Inject private ListDAO showDao;


    @Override
    public void convert(final ShowEntity source, final ShowDTO target) throws ConversionException {
        //Internal conversion
        target.setActorNumber(source.getActorNumber());
        target.setCreationDate(source.getCreationDate());
        target.setDescription(source.getDescription());
        target.setId(source.getId());
        target.setImage(source.getImage());
        target.setName(source.getName());
        target.setPriority(source.getPriority());

        //Binded conversion
        convertCoordinateInternal(source, target);
        convertSessionInternal(source, target);
        convertShowScoreInternal(source, target);
    }

    private void convertCoordinateInternal(final ShowEntity source, final ShowDTO target) throws ConversionException {
        try {
            final CoordinatesEntity coordinatesEntity = coordinateDao.find(source.getCoordinateId());
            final CoordinateDTO coordinateDTO = new CoordinateDTO();
            coordinatesDTOConverter.convert(coordinatesEntity, coordinateDTO);
            target.setCoordinate(coordinateDTO);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ConversionException("CUSTOM - Cannot convert coordinate from the show because coordinates doesn't exist!");
        }
    }

    private void convertSessionInternal(final ShowEntity source, final ShowDTO target) throws ConversionException {
        try {
            final List<SessionEntity> sessionEntities = sessionDao.findAll();
            final List<SessionDTO> finalList = new ArrayList<>();
            SessionDTO sessionDTO;
            for(SessionEntity sessionEntity : sessionEntities){
                sessionDTO = new SessionDTO();
                sessionDTOConverter.convert(sessionEntity, sessionDTO);
                finalList.add(sessionDTO);
            }
            target.setSessions(finalList);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ConversionException("CUSTOM - Cannot convert session from the show because coordinates doesn't exist!");
        }
    }

    private void convertShowScoreInternal(final ShowEntity source, final ShowDTO target) throws ConversionException{
        try {
            //final double score = ((ShowDao)showDao).getAverrageOfAllScores(source.getId());
            final double score = showDao.getAverrageOfAllScores(source.getId());
            final ShowScoreDTO showScoreDTO = new ShowScoreDTO(0, score, 0, 0, null);
            target.setScores(showScoreDTO);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ConversionException("CUSTOM - Cannot convert session from the show because coordinates doesn't exist!");
        }
    }
}
