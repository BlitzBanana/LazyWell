package corn.uni.crazywell.common.dto.converter.impl;

import corn.uni.crazywell.common.dto.converter.DTOConverterLocal;
import corn.uni.crazywell.common.dto.impl.SessionDTO;
import corn.uni.crazywell.common.exception.ConversionException;
import corn.uni.crazywell.data.entities.SessionEntity;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.ejb.Stateless;
import javax.inject.Named;

/**
 * Created by blacksheep on 16/06/15.
 */
@Named
@Stateless
public class SessionDTOConverter implements DTOConverterLocal<SessionEntity, SessionDTO> {
    @Override
    public void convert(final SessionEntity source, final SessionDTO target) throws ConversionException {
        throw new NotImplementedException();
    }
}
