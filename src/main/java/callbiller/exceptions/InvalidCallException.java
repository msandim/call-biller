package callbiller.exceptions;

/**
 * Class that represents an Exception thrown when an invalid call is parsed with
 * CallRecord::parse.
 */
public class InvalidCallException extends Exception {
    private static final long serialVersionUID = -6923549002021081558L;

    /**
     * Constructor for InvalidCallException.
     * 
     * @param s Call record that was being parsed
     */
    public InvalidCallException(String s) {
        super(s);
    }
}