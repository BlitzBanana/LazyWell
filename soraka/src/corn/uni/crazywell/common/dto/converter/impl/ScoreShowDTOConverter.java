package corn.uni.crazywell.common.dto.converter.impl;

import corn.uni.crazywell.common.dto.converter.DTOConverterLocal;
import corn.uni.crazywell.common.dto.impl.ShowScoreDTO;
import corn.uni.crazywell.common.exception.ConversionException;
import corn.uni.crazywell.data.entities.ShowScoreEntity;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by blacksheep on 16/06/15.
 */
public class ScoreShowDTOConverter implements DTOConverterLocal<ShowScoreEntity, ShowScoreDTO> {
    @Override
    public void convert(ShowScoreEntity source, ShowScoreDTO target) throws ConversionException {
        throw new NotImplementedException();
    }
}
