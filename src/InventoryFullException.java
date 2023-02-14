/**
 * Exception thrown when an add operation is attempted on a full Inventory
 */
public class InventoryFullException extends Exception {
    /**
     * Constructs a new InventoryFullException
     *
     * @param s the exception message
     */
    public InventoryFullException(String s) {
        super(s);
    }
}