package corn.uni.crazywell.business.tasks.impl;

import corn.uni.crazywell.business.tasks.ReturnableTask;
import corn.uni.crazywell.common.dto.DTO;
import corn.uni.crazywell.common.exception.TaskFailedException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Named;
import java.util.List;

/**
 * Created by blacksheep on 16/06/15.
 */
@Named("anecdoteTask")
@Stateless
public class GetAnecdoteTask implements ReturnableTask{
    @Override
    public List<? extends DTO> run() throws TaskFailedException {
        throw new NotImplementedException();
    }
}
