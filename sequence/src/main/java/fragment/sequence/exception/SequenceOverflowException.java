package fragment.sequence.exception;

public class SequenceOverflowException extends SequenceException {
    private static final long serialVersionUID = 7540944556741216508L;

    public SequenceOverflowException() {
        super();
    }

    public SequenceOverflowException(String message) {
        super(message);
    }

    public SequenceOverflowException(String message, Throwable cause) {
        super(message, cause);
    }
}
