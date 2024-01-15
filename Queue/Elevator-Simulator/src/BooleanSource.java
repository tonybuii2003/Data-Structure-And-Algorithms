// Name: Phi Long Bui, ID: 114555975, R-30
/**
 * This class has a double named probability as a member variable, a constructor
 * that accepts a double as a parameter as the value of this member variable,
 * and also a boolean method called requestArrived() that returns true a
 * percentage of the time equal to probability (and otherwise it returns false).
 * 
 * @author Phi Long Bui
 */
public class BooleanSource {
    private double probability;

    /**
     * This Contructor is for the BooleanSource
     * 
     * @param probability
     *                    The probability
     * @throws IllegalArgumentException
     *                                  If the probability is invalid
     */
    public BooleanSource(double probability) throws IllegalArgumentException {
        if (probability < 0.0 || probability > 1.0) {
            throw new IllegalArgumentException();
        }
        this.probability = probability;
    }

    /**
     * This method returns true a percentage of the time equal to probability (and
     * otherwise it returns false).
     * 
     * @return
     *         true a percentage of the time equal to probability (and otherwise it
     *         returns false)
     */
    public boolean requestArrived() {
        return (Math.random() < probability);
    }
}