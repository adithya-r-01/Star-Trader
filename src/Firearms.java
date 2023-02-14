import java.util.Random;

/**
 * Class that describes Firearms, which are a Good
 */
public class Firearms extends Good {
    /**
     * Constructs a new instance of Firearms
     *
     * @param r the region in which the Firearms are sold
     */
    public Firearms(Region r) {
        super(725, "Firearms", new Random(), r, 3);
    }

    /**
     * Determines whether the Firearms are available in a Region
     *
     * @param r the Region
     * @return availableInRegion
     */
    public static boolean availableInRegion(Region r) {
        return Good.availableInRegion(r, 3);
    }
}
