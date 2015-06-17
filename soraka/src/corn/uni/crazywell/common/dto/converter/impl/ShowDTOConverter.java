package corn.uni.crazywell.common.dto.converter.impl;

import corn.uni.crazywell.common.dto.converter.DTOConverterLocal;
import corn.uni.crazywell.common.dto.impl.CoordinateDTO;
import corn.uni.crazywell.common.dto.impl.ShowDTO;
import corn.uni.crazywell.common.exception.ConversionException;
import corn.uni.crazywell.common.exception.DAOException;
import corn.uni.crazywell.data.dao.AbstractGenericDAO;
import corn.uni.crazywell.data.dao.impl.CoordinateDao;
import corn.uni.crazywell.data.entities.CoordinatesEntity;
import corn.uni.crazywell.data.entities.ShowEntity;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by blacksheep on 16/06/15.
 */
@Named
@Stateless
public class ShowDTOConverter implements DTOConverterLocal<ShowEntity, ShowDTO> {

    @Inject private AbstractGenericDAO<CoordinatesEntity> coordinateDao;
    @Inject private DTOConverterLocal<CoordinatesEntity, CoordinateDTO> coordinateConverter;

    @Override
    public void convert(final ShowEntity source, final ShowDTO target) throws ConversionException {
        //Internal conversion
        target.setActorNumber(source.getActorNumber());
        target.setCreationDate(source.getCreationDate());
        target.setDescription(source.getDescription());
        target.setEndDate(source.getEndDate());
        target.setId(source.getId());
        target.setImage(source.getImage());
        target.setName(source.getName());
        target.setPriority(source.getPriority());
        target.setStartDate(source.getStartDate());
        //Binded conversion
        convertCoordinateInternal(source, target);
    }

    private void convertCoordinateInternal(final ShowEntity source, final ShowDTO target) throws ConversionException {
        try {
            final CoordinatesEntity coordinatesEntity = coordinateDao.find(source.getCoordinateId());
            final CoordinateDTO coordinateDTO = new CoordinateDTO();
            coordinateConverter.convert(coordinatesEntity, coordinateDTO);
            target.setCoordinate(coordinateDTO);
        } catch (DAOException e) {
            throw new ConversionException("CUSTOM - Cannot convert coordinate from the show because coordinates doesn't exist!");
        }
    }
}
