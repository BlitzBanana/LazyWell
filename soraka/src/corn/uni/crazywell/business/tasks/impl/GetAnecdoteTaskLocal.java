package corn.uni.crazywell.business.tasks.impl;

import corn.uni.crazywell.business.tasks.ReturnableTaskLocal;
import corn.uni.crazywell.common.Bubble;
import corn.uni.crazywell.common.dto.DTO;
import corn.uni.crazywell.common.exception.TaskFailedException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.ejb.Stateless;
import javax.inject.Named;
import java.util.List;

/**
 * Created by blacksheep on 16/06/15.
 */

@Stateless
public class GetAnecdoteTaskLocal implements ReturnableTaskLocal {
    @Override
    public List<? extends DTO> run(Bubble bubble) throws TaskFailedException {
        throw new NotImplementedException();
    }
}
