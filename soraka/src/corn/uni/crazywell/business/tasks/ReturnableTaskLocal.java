package corn.uni.crazywell.business.tasks;

import corn.uni.crazywell.common.dto.DTO;
import corn.uni.crazywell.common.exception.TaskFailedException;

import javax.ejb.Local;
import javax.ejb.Remote;
import java.io.Serializable;
import java.util.List;

/**
 * Created by blacksheep on 16/06/15.
 */
@Local
public interface ReturnableTaskLocal extends Serializable{
    List<? extends DTO> run() throws TaskFailedException;
}
