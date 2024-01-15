import java.util.Comparator;

// Name: Phi Long Bui, ID: 114555975, R-01
/**
 * This class repersents a Comparator for the storm's precipitation
 * which implements the Comparator interface
 */
public class PrecipitationComparator implements Comparator {
    /**
     * This method compare 2 storm's precipitation
     * 
     * @return
     *         1 is o1 > o2, -1 otherwise, 0 when equal
     */
    public int compare(Object o1, Object o2) {
        if (((Storm) o1).getPrecipitation() > ((Storm) o2).getPrecipitation()) {
            return 1;
        } else if ((((Storm) o1).getPrecipitation() < ((Storm) o2).getPrecipitation())) {
            return -1;
        }
        return 0;
    }

}
