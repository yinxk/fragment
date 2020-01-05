package fragment.sequence.exception;

public class SequenceBufferInitException extends SequenceException {
    private static final long serialVersionUID = -261017865150453162L;

    public SequenceBufferInitException() {
        super();
    }

    public SequenceBufferInitException(String message) {
        super(message);
    }

    public SequenceBufferInitException(String message, Throwable cause) {
        super(message, cause);
    }
}
