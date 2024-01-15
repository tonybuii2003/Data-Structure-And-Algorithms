
// Name: Phi Long Bui, ID: 114555975, R-01
import java.util.Comparator;

/**
 * /**
 * This class repersents a Comparator for the storm's windspeed
 * which implements the Comparator interface
 */
public class WindSpeedComparator implements Comparator {
    /**
     * This method compare 2 storm's windspeed
     * 
     * @return
     *         1 is o1 > o2, -1 otherwise, 0 when equal
     */
    public int compare(Object o1, Object o2) {
        if (((Storm) o1).getWindspeed() > ((Storm) o2).getWindspeed()) {
            return 1;
        } else if ((((Storm) o1).getWindspeed() < ((Storm) o2).getWindspeed())) {
            return -1;
        }
        return 0;
    }

}
