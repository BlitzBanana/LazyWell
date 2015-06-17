package corn.uni.crazywell.data.dao.impl;

import corn.uni.crazywell.data.dao.AbstractGenericDAO;
import corn.uni.crazywell.data.entities.AnecdoteQuestionEntity;

/**
 * Created by blacksheep on 16/06/15.
 */
public class AnecdoteQuestionDAO extends AbstractGenericDAO<AnecdoteQuestionEntity> {
    public AnecdoteQuestionDAO() {
        super(AnecdoteQuestionEntity.class);
    }
}
