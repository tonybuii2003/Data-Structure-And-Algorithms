// Name: Phi Long Bui, ID: 114555975, R-01
/**
 * This is a class repersents a an object is constructed with a probability p
 * between 0 and 1 and then the occurs() method returns true p*100% of the time.
 * 
 * @author Phi Long Bui
 */
public class BooleanSource {
    private double probability;

    /**
     * This Constructor used to create a new BooleanSource object
     * 
     * @param p
     *          the given probability
     * @throws IllegalArgumentException
     *                                  if the given probability is not beteen 0 and
     *                                  1
     */
    public BooleanSource(double p) throws IllegalArgumentException {
        if (p < 0.0 || p > 1.0) {
            throw new IllegalArgumentException();
        }
        this.probability = p;
    }

    /**
     * This method decides whether a passenger come to the station depend on the
     * probability.
     * 
     * @return
     *         true if the random number is less then the probability
     *         false otherwise
     */
    public boolean occurs() {
        return (Math.random() < probability);
    }
}
