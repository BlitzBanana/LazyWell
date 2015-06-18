package corn.uni.crazywell.data.dao;

import corn.uni.crazywell.common.Bubble;
import corn.uni.crazywell.common.exception.DAOException;

import javax.ejb.Local;

/**
 * Created by Thanith on 18/06/2015.
 */
@Local
public interface ScoreDao {
    boolean isVoted(Bubble bubble) throws DAOException;
}
