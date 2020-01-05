package fragment.sequence.exception;

public class SequenceNotFoundException extends SequenceException {
    private static final long serialVersionUID = -7790206683303423580L;

    public SequenceNotFoundException() {
        super();
    }

    public SequenceNotFoundException(String message) {
        super(message);
    }

    public SequenceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
