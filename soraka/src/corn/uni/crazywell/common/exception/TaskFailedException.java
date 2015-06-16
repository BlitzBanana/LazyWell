package corn.uni.crazywell.common.exception;

/**
 * Created by blacksheep on 16/06/15.
 */
public class TaskFailedException extends Exception {
    public TaskFailedException() {
    }

    public TaskFailedException(String message) {
        super(message);
    }

    public TaskFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public TaskFailedException(Throwable cause) {
        super(cause);
    }

    public TaskFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
