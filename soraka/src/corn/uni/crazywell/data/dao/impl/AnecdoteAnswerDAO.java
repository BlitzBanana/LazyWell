package corn.uni.crazywell.data.dao.impl;

import corn.uni.crazywell.data.dao.AbstractGenericDAO;
import corn.uni.crazywell.data.entities.AnecdoteAnswerEntity;

/**
 * Created by blacksheep on 16/06/15.
 */
public class AnecdoteAnswerDAO extends AbstractGenericDAO<AnecdoteAnswerEntity> {
    public AnecdoteAnswerDAO() {
        super(AnecdoteAnswerEntity.class);
    }
}
