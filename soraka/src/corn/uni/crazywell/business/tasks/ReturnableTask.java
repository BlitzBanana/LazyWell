package corn.uni.crazywell.business.tasks;

import corn.uni.crazywell.common.dto.DTO;
import corn.uni.crazywell.common.exception.TaskFailedException;

import java.util.List;

/**
 * Created by blacksheep on 16/06/15.
 */
public interface ReturnableTask {
    List<? extends DTO> run() throws TaskFailedException;
}
