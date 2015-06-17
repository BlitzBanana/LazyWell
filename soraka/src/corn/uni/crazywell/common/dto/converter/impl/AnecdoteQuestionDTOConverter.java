package corn.uni.crazywell.common.dto.converter.impl;

import corn.uni.crazywell.common.dto.converter.DTOConverterLocal;
import corn.uni.crazywell.common.dto.impl.AnecdoteQuestionDTO;
import corn.uni.crazywell.common.exception.ConversionException;
import corn.uni.crazywell.data.entities.AnecdoteQuestionEntity;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by blacksheep on 16/06/15.
 */
public class AnecdoteQuestionDTOConverter implements DTOConverterLocal<AnecdoteQuestionEntity, AnecdoteQuestionDTO> {
    @Override
    public void convert(AnecdoteQuestionEntity source, AnecdoteQuestionDTO target) throws ConversionException {
        throw new NotImplementedException();
    }
}
