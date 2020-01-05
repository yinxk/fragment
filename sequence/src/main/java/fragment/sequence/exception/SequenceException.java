package fragment.sequence.exception;

public class SequenceException extends RuntimeException {
    private static final long serialVersionUID = -7790206683303423580L;

    public SequenceException() {
        super();
    }

    public SequenceException(String message) {
        super(message);
    }

    public SequenceException(String message, Throwable cause) {
        super(message, cause);
    }
}
