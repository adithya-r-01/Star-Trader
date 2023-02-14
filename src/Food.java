import java.util.Random;

/**
 * Class that describes Food, which is a Good
 */
public class Food extends Good {
    /**
     * Constructs a new instance of Food
     *
     * @param r the region in which the Food is sold
     */
    public Food(Region r) {
        super(105, "Food", new Random(), r, 0);
    }

    /**
     * Determines whether the Food is available in a Region
     *
     * @param r the Region
     * @return availableInRegion
     */
    public static boolean availableInRegion(Region r) {
        return Good.availableInRegion(r, 0);
    }
}
