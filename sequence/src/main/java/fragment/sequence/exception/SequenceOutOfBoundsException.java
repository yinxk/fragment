package fragment.sequence.exception;

public class SequenceOutOfBoundsException extends SequenceException {
    private static final long serialVersionUID = 7540944556741216508L;

    public SequenceOutOfBoundsException() {
        super();
    }

    public SequenceOutOfBoundsException(String message) {
        super(message);
    }

    public SequenceOutOfBoundsException(String message, Throwable cause) {
        super(message, cause);
    }
}
