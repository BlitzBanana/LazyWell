package corn.uni.crazywell.common.dto.converter.impl;

import corn.uni.crazywell.common.dto.DTO;
import corn.uni.crazywell.common.dto.converter.DTOConverterLocal;
import corn.uni.crazywell.common.dto.impl.AnecdoteAnswerDTO;
import corn.uni.crazywell.common.exception.ConversionException;
import corn.uni.crazywell.data.entities.AnecdoteAnswerEntity;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by blacksheep on 16/06/15.
 */
public class AnecdoteAnswerDTOConverter implements DTOConverterLocal<AnecdoteAnswerEntity, AnecdoteAnswerDTO> {
    @Override
    public void convert(AnecdoteAnswerEntity source, AnecdoteAnswerDTO target) throws ConversionException {
        throw new NotImplementedException();
    }
}
