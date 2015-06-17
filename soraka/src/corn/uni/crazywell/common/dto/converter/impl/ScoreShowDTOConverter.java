package corn.uni.crazywell.common.dto.converter.impl;

import corn.uni.crazywell.common.dto.converter.DTOConverterLocal;
import corn.uni.crazywell.common.dto.impl.ShowScoreDTO;
import corn.uni.crazywell.common.exception.ConversionException;
import corn.uni.crazywell.data.entities.ShowScoreEntity;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.ejb.Stateless;
import javax.inject.Named;

/**
 * Created by blacksheep on 16/06/15.
 */
@Named
@Stateless
public class ScoreShowDTOConverter implements DTOConverterLocal<ShowScoreEntity, ShowScoreDTO> {
    @Override
    public void convert(ShowScoreEntity source, ShowScoreDTO target) throws ConversionException {
        throw new NotImplementedException();
    }
}
