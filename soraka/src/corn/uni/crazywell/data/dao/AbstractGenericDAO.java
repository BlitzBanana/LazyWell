package corn.uni.crazywell.data.dao;

import corn.uni.crazywell.common.exception.DAOException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jboss.seam.microcontainer.HibernateFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by blacksheep on 29/05/15.
 */
public abstract class AbstractGenericDAO<T> implements GenericDAO<T> {
    private Class<T> clazz;


    @PersistenceContext
    private EntityManager entityManager;

    public AbstractGenericDAO(final Class<T> clazz){
        this.clazz = clazz;
    }

    public AbstractGenericDAO(){
    }

    @Override
    public T find(final Object id) throws DAOException{
        T result = getEntityManager().find(clazz, id);
        if(result != null) {
            return result;
        } else {
            throw new DAOException("CUSTOM - Object cannot be found");
        }
    }

    @Override
    public boolean persist(final T instance) throws DAOException {
        try{
            getEntityManager().persist(instance);
            return true;
        } catch (Exception ex) {
            throw new DAOException("CUSTOM - Failed to persist instance from DAO");
        }
    }

    @Override
    public boolean delete(final T instance) throws DAOException {
        try{
            getEntityManager().remove(instance);
            return true;
        } catch (Exception ex) {
            throw new DAOException("CUSTOM - Failed to persist instance from DAO");
        }
    }

    @Override
    public List<T> findAll() throws DAOException {
        List<T> result;
        try{
            Session session = new HibernateFactory().getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("from " + clazz.getName());
            result = query.list();
            transaction.commit();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("CUSTOM failed to get all result. See internal error.");
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
