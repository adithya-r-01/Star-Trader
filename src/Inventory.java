import java.util.HashMap;

/**
 * Class used to describe the Ship's inventory. Uses a HashMap to back all operations.
 */
public class Inventory {
    private static final int DEFAULT_CAPACITY = 20;
    private HashMap<String, Integer> inventory;
    private int count;
    private int capacity;

    /**
     * Constructs a new Inventory
     *
     * @param inventory the backing HashMap
     * @param count     how many items are currently in the inventory
     * @param capacity  the max capacity of the inventory
     */
    private Inventory(HashMap<String, Integer> inventory, int count, int capacity) {
        this.inventory = inventory;
        this.count = count;
        this.capacity = capacity;
    }

    /**
     * Constructs a new Inventory
     *
     * @param capacity the max capacity of the inventory
     */
    public Inventory(int capacity) {
        this(new HashMap<>(), 0, capacity);
    }

    /**
     * Constructs a new inventory with a default capacity of 20.
     */
    public Inventory() {
        this(new HashMap<>(), 0, DEFAULT_CAPACITY);
    }

    /**
     * Adds a new Good to the inventory and increments count if the add is successful
     *
     * @param goodName the string name of the good
     * @throws InventoryFullException when the inventory is full
     */
    public void add(String goodName) throws InventoryFullException {
        if (count == capacity) {
            throw new InventoryFullException("Cannot add to a full inventory!");
        }

        inventory.merge(goodName, 1, Integer::sum);
        count++;
    }

    /**
     * Adds multiple Goods to the inventory
     *
     * @param goodName the string name of the Good
     * @param num      the number of Goods to add
     */
    public void add(String goodName, int num) {
        while (num != 0) {
            try {
                add(goodName);
                num--;
            } catch (InventoryFullException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    /**
     * Removes a Good from the Inventory
     *
     * @param goodName the string name of the Good
     * @throws IllegalStateException    when the Inventory is empty
     * @throws IllegalArgumentException when the Good is not in the Inventory
     */
    public void remove(String goodName) throws IllegalStateException, IllegalArgumentException {
        if (count == 0) {
            throw new IllegalStateException("Cannot remove from an empty inventory!");
        }

        Integer currVal = inventory.get(goodName);
        if (currVal == null) {
            throw new IllegalArgumentException(
                    "Cannot remove a good that is not in the inventory!");
        } else if (currVal == 1) {
            inventory.remove(goodName);
        } else {
            inventory.put(goodName, currVal - 1);
        }
        count--;
    }

    /**
     * Clears the Inventory
     */
    public void clear() {
        inventory = new HashMap<>();
        count = 0;
    }

    /**
     * Checks if the Inventory contains a Good
     *
     * @param goodName the string name of the Good
     * @return true or false
     */
    public boolean contains(String goodName) {
        return get(goodName) > 0;
    }

    /**
     * Gets the quantity of a Good in the inventory
     *
     * @param goodName the string name of the Good
     * @return quantity
     */
    public int get(String goodName) {
        return inventory.getOrDefault(goodName, 0);
    }

    /**
     * Checks if the Inventory is empty
     *
     * @return true or false
     */
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * Checks whether the Inventory is full
     *
     * @return true or false
     */
    public boolean isFull() {
        return count == capacity;
    }

    /**
     * Returns the max capacity of the Inventory
     *
     * @return capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Returns the number of items in the Inventory
     *
     * @return count
     */
    public int getCount() {
        return count;
    }
}
