package corn.uni.crazywell.data.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by blacksheep on 29/05/15.
 */
public abstract class AbstractGenericDAO<T> implements GenericDAO<T> {
    private Class<T> clazz;


    @PersistenceContext
    private EntityManager entityManager;

    public AbstractGenericDAO(Class<T> clazz){
        this.clazz = clazz;
    }

    public AbstractGenericDAO(){
    }

    @Override
    public T find(Object id) throws Exception{
        T result = getEntityManager().find(clazz, id);
        if(result != null) {
            return result;
        } else {
            throw new Exception();
        }
    }

    @Override
    public boolean persist(T instance) {
        try{
            getEntityManager().persist(instance);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public boolean delete(T instance) {
        try{
            getEntityManager().remove(instance);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    /*Getters and setters*/
    public Class<T> getClazz() {
        return clazz;
    }

    public void setClazz(Class<T> clazz) {
        this.clazz = clazz;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
