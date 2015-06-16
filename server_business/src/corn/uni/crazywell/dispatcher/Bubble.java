package corn.uni.crazywell.dispatcher;

import java.io.Serializable;

/**
 * Created by Thanith on 16/06/2015.
 */
public class Bubble implements Serializable {
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    private String body;

    public Process getProcess() {
        return process;
    }

    public void setProcess(Process process) {
        this.process = process;
    }

    private Process process;

    public enum Process {
        process1,
        process2,
        process3
    }
}
