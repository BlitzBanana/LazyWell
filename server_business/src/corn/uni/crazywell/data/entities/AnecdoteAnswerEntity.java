package corn.uni.crazywell.data.entities;

import javax.persistence.*;

/**
 * Created by blacksheep on 16/06/15.
 */
@Entity
@Table(name = "anecdote_answer", schema = "", catalog = "lazywell")
public class AnecdoteAnswerEntity {
    private int id;
    private String textAnswer;
    private int anecdoteQuestionId;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "text_answer")
    public String getTextAnswer() {
        return textAnswer;
    }

    public void setTextAnswer(String textAnswer) {
        this.textAnswer = textAnswer;
    }

    @Basic
    @Column(name = "anecdote_question_id")
    public int getAnecdoteQuestionId() {
        return anecdoteQuestionId;
    }

    public void setAnecdoteQuestionId(int anecdoteQuestionId) {
        this.anecdoteQuestionId = anecdoteQuestionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AnecdoteAnswerEntity that = (AnecdoteAnswerEntity) o;

        if (id != that.id) return false;
        if (anecdoteQuestionId != that.anecdoteQuestionId) return false;
        if (textAnswer != null ? !textAnswer.equals(that.textAnswer) : that.textAnswer != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (textAnswer != null ? textAnswer.hashCode() : 0);
        result = 31 * result + anecdoteQuestionId;
        return result;
    }
}
