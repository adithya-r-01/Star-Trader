/**
 * This Exception describes the situation when it is attempted to initialize
 * more than 10 Region objects.
 *
 * @author jrollins33
 * @version M4
 */
public class MaximumRegionsException extends Exception {
    public MaximumRegionsException() {
        super();
    }

    public MaximumRegionsException(String message) {
        super(message);
    }
}
