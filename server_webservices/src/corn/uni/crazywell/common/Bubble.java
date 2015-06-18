package corn.uni.crazywell.common;

import corn.uni.crazywell.common.dto.DTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thanith on 16/06/2015.
 */
public class Bubble implements Serializable {

    public Bubble(){
        body = new ArrayList<>();
    }

    private Process header;

    public List<Object> getBody() {
        return body;
    }

    public void setBody(List<Object> body) {
        this.body = body;
    }

    private List<Object> body;

    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Process getHeader() {
        return header;
    }

    public void setHeader(Process header) {
        this.header = header;
    }

    public enum Process {
        GET_SHOWS,
        GET_BEST_SCHEDULE,
        GET_ANECDOTES,
        GET_RESTAURANTS,
        GET_SHOPS,
        CHECK_UPDATE,
        SET_EVAL
    }
}