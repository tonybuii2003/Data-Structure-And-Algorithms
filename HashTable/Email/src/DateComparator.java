
//Name: Phi Long Bui ID: 114555975 R-30
import java.util.Comparator;

/**
 * This class is for the date comparator
 * 
 * @author Phi Long Bui
 */
public class DateComparator implements Comparator<Object> {
    /**
     * This method compare the date of Object 1 to Object 2
     * 
     * @return
     *         An int bigger than 0 If Object 1 bigger than Object 2, An int smaller
     *         than 0 If Object 1 smaller than Object 2, 0 if equals
     */
    public int compare(Object o1, Object o2) {
        return ((Email) o1).getTime().compareTo(((Email) o2).getTime());
    }
}