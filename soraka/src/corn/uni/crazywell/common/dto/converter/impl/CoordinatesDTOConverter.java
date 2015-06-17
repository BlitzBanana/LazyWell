package corn.uni.crazywell.common.dto.converter.impl;

import corn.uni.crazywell.common.dto.converter.DTOConverterLocal;
import corn.uni.crazywell.common.dto.impl.CoordinateDTO;
import corn.uni.crazywell.data.entities.CoordinatesEntity;

import javax.ejb.Stateless;
import javax.inject.Named;

/**
 * Created by blacksheep on 16/06/15.
 */
@Named
@Stateless
public class CoordinatesDTOConverter implements DTOConverterLocal<CoordinatesEntity, CoordinateDTO> {
    @Override
    public void convert(final CoordinatesEntity source, final CoordinateDTO target) {
        target.setId(source.getId());
        target.setLatitude(source.getLatitude());
        target.setLongitude(source.getLongitude());
    }
}
