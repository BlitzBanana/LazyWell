package corn.uni.crazywell.common.dto.converter.impl;

import corn.uni.crazywell.common.dto.converter.DTOConverterLocal;
import corn.uni.crazywell.common.dto.impl.CoordinateDTO;
import corn.uni.crazywell.common.dto.impl.RestaurantDTO;
import corn.uni.crazywell.common.dto.impl.RestaurantScoreDTO;
import corn.uni.crazywell.common.exception.ConversionException;
import corn.uni.crazywell.common.exception.DAOException;
import corn.uni.crazywell.data.dao.AbstractGenericDAO;
import corn.uni.crazywell.data.dao.ListDAO;
import corn.uni.crazywell.data.entities.CoordinatesEntity;
import corn.uni.crazywell.data.entities.RestaurantEntity;
import corn.uni.crazywell.data.entities.RestaurantScoreEntity;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by blacksheep on 16/06/15.
 */
@Named
@Stateless
public class RestaurantDTOConverter implements DTOConverterLocal<RestaurantEntity, RestaurantDTO> {

    @Inject private AbstractGenericDAO<CoordinatesEntity> coordinateDao;
    @Inject private DTOConverterLocal<CoordinatesEntity, CoordinateDTO> coordinatesDTOConverter;
    @Inject private DTOConverterLocal<RestaurantScoreEntity, RestaurantScoreDTO> scoreShowDTOConverter;
    @Inject private ListDAO restaurantDao;


    @Override
    public void convert(RestaurantEntity source, RestaurantDTO target) throws ConversionException {
        //Internal conversion
        target.setMenu(source.getMenu());
        target.setDescription(source.getDescription());
        target.setId(source.getId());
        target.setName(source.getName());

        //Binded conversion
        convertCoordinateInternal(source, target);
        convertRestaurantScoreInternal(source, target);
    }

    private void convertCoordinateInternal(final RestaurantEntity source, final RestaurantDTO target) throws ConversionException {
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
    private void convertRestaurantScoreInternal(final RestaurantEntity source, final RestaurantDTO target) throws ConversionException{
        try {
            //final double score = ((ShowDao)showDao).getAverrageOfAllScores(source.getId());
            final double score = restaurantDao.getAverrageOfAllScores(source.getId());
            final RestaurantScoreDTO restaurantScoreDTO = new RestaurantScoreDTO(0, score, 0, 0, null);
            target.setScores(restaurantScoreDTO);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ConversionException("CUSTOM - Cannot convert session from the show because coordinates doesn't exist!");
        }
    }

}
