package corn.uni.crazywell.business.services;

import corn.uni.crazywell.data.dao.GenericDAO;

/**
 * Created by blacksheep on 29/05/15.
 */
public abstract class AbstractGenericService<T> implements CrudService<T>{

    protected GenericDAO genericDAO;

    @Override
    public T find(Object id) {
        try{
            return (T)genericDAO.find(id);
        } catch(Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public boolean persist(Object instance) {
        return false;
    }

    @Override
    public boolean delete(Object instance) {
        return false;
    }

}
