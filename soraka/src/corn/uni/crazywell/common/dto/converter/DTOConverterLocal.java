package corn.uni.crazywell.common.dto.converter;

import corn.uni.crazywell.common.dto.DTO;
import corn.uni.crazywell.common.exception.ConversionException;

import javax.ejb.Local;

/**
 * Created by blacksheep on 16/06/15.
 */
@Local
public interface DTOConverterLocal<S, T extends DTO> {
    void convert(S source, T target) throws ConversionException;
}
