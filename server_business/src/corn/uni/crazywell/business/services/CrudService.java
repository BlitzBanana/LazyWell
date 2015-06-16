package corn.uni.crazywell.business.services;

import java.util.List;

/**
 * Created by blacksheep on 29/05/15.
 */
public interface CrudService <T>{
    T find(Object id);
    List<T> findAll();
    boolean persist(T instance);
    boolean delete(T instance);
}
