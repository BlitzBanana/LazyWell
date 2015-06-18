package corn.uni.crazywell.common.dto.converter.impl;

import corn.uni.crazywell.common.dto.converter.DTOConverterLocal;
import corn.uni.crazywell.common.dto.impl.*;
import corn.uni.crazywell.common.exception.ConversionException;
import corn.uni.crazywell.common.exception.DAOException;
import corn.uni.crazywell.data.dao.AbstractGenericDAO;
import corn.uni.crazywell.data.dao.ListDao;
import corn.uni.crazywell.data.dao.impl.ShowDao;
import corn.uni.crazywell.data.entities.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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
public class ShopDTOConverter implements DTOConverterLocal<ShopEntity, ShopDTO> {

    @Inject
    private AbstractGenericDAO<CoordinatesEntity> coordinateDao;
    @Inject private DTOConverterLocal<CoordinatesEntity, CoordinateDTO> coordinatesDTOConverter;
    @Inject private DTOConverterLocal<ShopScoreEntity, ShopScoreDTO> scoreShowDTOConverter;
    @Inject private ListDAO shopDao;


    @Override
    public void convert(final ShopEntity source, final ShopDTO target) throws ConversionException {
        //Internal conversion
        target.setDescription(source.getDescription());
        target.setId(source.getId());
        target.setName(source.getName());

        //Binded conversion
        convertCoordinateInternal(source, target);
        convertShopScoreInternal(source, target);
    }

    private void convertCoordinateInternal(final ShopEntity source, final ShopDTO target) throws ConversionException {
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

    private void convertShopScoreInternal(final ShopEntity source, final ShopDTO target) throws ConversionException{
        try {
            //final double score = ((ShowDao)showDao).getAverrageOfAllScores(source.getId());
            final double score = shopDao.getAverrageOfAllScores(source.getId());
            final ShopScoreDTO shopScoreDTO = new ShopScoreDTO(0, score, 0, 0, null);
            target.setScores(shopScoreDTO);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ConversionException("CUSTOM - Cannot convert session from the show because coordinates doesn't exist!");
        }
    }
}
