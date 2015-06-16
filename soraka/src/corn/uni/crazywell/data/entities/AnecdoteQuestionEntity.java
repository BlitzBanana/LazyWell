package corn.uni.crazywell.data.entities;

import javax.persistence.*;

/**
 * Created by blacksheep on 16/06/15.
 */
@Entity
@Table(name = "anecdote_question", schema = "", catalog = "lazywell")
public class AnecdoteQuestionEntity {
    private int id;
    private String textQuestion;
    private String textCorrection;
    private int showId;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "text_question")
    public String getTextQuestion() {
        return textQuestion;
    }

    public void setTextQuestion(String textQuestion) {
        this.textQuestion = textQuestion;
    }

    @Basic
    @Column(name = "text_correction")
    public String getTextCorrection() {
        return textCorrection;
    }

    public void setTextCorrection(String textCorrection) {
        this.textCorrection = textCorrection;
    }

    @Basic
    @Column(name = "show_id")
    public int getShowId() {
        return showId;
    }

    public void setShowId(int showId) {
        this.showId = showId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AnecdoteQuestionEntity that = (AnecdoteQuestionEntity) o;

        if (id != that.id) return false;
        if (showId != that.showId) return false;
        if (textQuestion != null ? !textQuestion.equals(that.textQuestion) : that.textQuestion != null) return false;
        if (textCorrection != null ? !textCorrection.equals(that.textCorrection) : that.textCorrection != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (textQuestion != null ? textQuestion.hashCode() : 0);
        result = 31 * result + (textCorrection != null ? textCorrection.hashCode() : 0);
        result = 31 * result + showId;
        return result;
    }
}
