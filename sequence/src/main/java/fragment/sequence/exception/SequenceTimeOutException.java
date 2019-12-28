package fragment.sequence.exception;

public class SequenceTimeOutException extends SequenceException {
    private static final long serialVersionUID = 4756302432026204740L;

    public SequenceTimeOutException() {
        super();
    }

    public SequenceTimeOutException(String message) {
        super(message);
    }

    public SequenceTimeOutException(String message, Throwable cause) {
        super(message, cause);
    }
}
