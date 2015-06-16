package corn.uni.crazywell.data.dao;

import java.util.List;

/**
 * Created by blacksheep on 29/05/15.
 */
public interface GenericDAO<T>{
    T find(Object id) throws Exception;
    List<T> findAll();
    boolean persist(T instance);
    boolean delete(T instance);
}
