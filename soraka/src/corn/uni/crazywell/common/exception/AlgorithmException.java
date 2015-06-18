package corn.uni.crazywell.common.exception;

/**
 * Created by blacksheep on 18/06/15.
 */
public class AlgorithmException extends Exception {
    public AlgorithmException() {
    }

    public AlgorithmException(String message) {
        super(message);
    }

    public AlgorithmException(String message, Throwable cause) {
        super(message, cause);
    }

    public AlgorithmException(Throwable cause) {
        super(cause);
    }

    public AlgorithmException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
