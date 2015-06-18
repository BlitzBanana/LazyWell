package corn.uni.crazywell.data.dao;

import corn.uni.crazywell.common.exception.DAOException;

import javax.ejb.Local;

/**
 * Created by blacksheep on 18/06/15.
 */
@Local
public interface ShowDao {
    double getAverrageOfAllScores(int id) throws DAOException;
}
