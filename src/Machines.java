import java.util.Random;

/**
 * Class that describes Machines, which are a Good
 */
public class Machines extends Good {
    /**
     * Constructs a new instance of Machines
     *
     * @param r the region in which the machines are being sold
     */
    public Machines(Region r) {
        super(690, "Machines", new Random(), r, 4);
    }

    /**
     * Determines whether the Machines are available in a Region
     *
     * @param r the Region
     * @return availableInRegion
     */
    public static boolean availableInRegion(Region r) {
        return Good.availableInRegion(r, 4);
    }
}
