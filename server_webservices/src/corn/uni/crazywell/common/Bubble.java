package corn.uni.crazywell.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thanith on 16/06/2015.
 */
public class Bubble implements Serializable {
    public Bubble()
    {
        body = new ArrayList<>();
    }

    private Process header;
    private List<Serializable> body;
    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<Serializable> getBody() {
        return body;
    }

    public void setBody(List<Serializable> body) {
        this.body = body;
    }

    public Process getHeader() {
        return header;
    }

    public void setHeader(Process header) {
        this.header = header;
    }

    public enum Process {
        process1,
        process2,
        process3
    }
}