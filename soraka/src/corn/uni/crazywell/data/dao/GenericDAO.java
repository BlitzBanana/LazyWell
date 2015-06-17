package corn.uni.crazywell.data.dao;

import corn.uni.crazywell.common.exception.DAOException;

import java.util.List;

/**
 * Created by blacksheep on 29/05/15.
 */
public interface GenericDAO<T>{
    T find(Object id) throws DAOException;
    List<T> findAll() throws DAOException;
    boolean persist(T instance) throws DAOException;
    boolean delete(T instance) throws DAOException;
}
