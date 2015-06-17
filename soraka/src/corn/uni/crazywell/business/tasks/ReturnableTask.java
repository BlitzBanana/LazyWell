package corn.uni.crazywell.business.tasks;

import corn.uni.crazywell.common.dto.DTO;
import corn.uni.crazywell.common.exception.TaskFailedException;

import javax.ejb.Remote;
import java.io.Serializable;
import java.util.List;

/**
 * Created by blacksheep on 16/06/15.
 */
@Remote
public interface ReturnableTask extends Serializable{
    List<? extends DTO> run() throws TaskFailedException;
}
