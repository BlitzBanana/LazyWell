package corn.uni.crazywell.data.dao;

import corn.uni.crazywell.common.dto.converter.DTOConverterLocal;
import corn.uni.crazywell.common.dto.converter.impl.ShowDTOConverter;
import corn.uni.crazywell.common.dto.impl.ShowDTO;
import corn.uni.crazywell.common.exception.DAOException;
import corn.uni.crazywell.data.entities.ShowEntity;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by blacksheep on 18/06/15.
 */
@Local
public interface ShowDaoLocal extends GenericDAO<ShowEntity> {
    List<ShowDTO> getAvailableActivities(final int minuteBeforeNextBreak, final int walkingTime, final DTOConverterLocal<ShowEntity, ShowDTO> showDTOConverter) throws DAOException;
}
