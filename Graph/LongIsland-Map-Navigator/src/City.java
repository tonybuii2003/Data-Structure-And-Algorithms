
// Name: Phi Long Bui, ID: 114555975, R-01
import java.util.HashMap;

/**
 * This class represents each vertex/node (City) of the graph
 * Each node needs to know what roads lead out of it,
 * and what the capacity of the roads is.
 * The city should implement comparable,
 * and the compare method should look at the name of the city only,
 * so we can print a list of cities alphabetically.
 * 
 * @author Phi Long Bui
 */
public class City implements Comparable<City> {
    private HashMap<String, Integer> neighbors = new HashMap<String, Integer>();
    private String name;
    private boolean discoverd, visited;

    /**
     * This Constructor used to create a new City object
     * 
     * @param name
     *             The name of the City
     */
    public City(String name) {
        this.name = name;
        this.neighbors = new HashMap<String, Integer>();
        this.visited = false;
        this.discoverd = false;
    }

    /**
     * This method compare the given city's first character to this city's first
     * character
     * 
     * @param o
     *          The given city
     * @return
     *         1 If this city is larger than the given City, -1 If smaller and 0 for
     *         equal
     */
    public int compareTo(City o) {
        if (this.getName().compareToIgnoreCase(o.getName()) > 0) {
            return 1;
        } else if (this.getName().compareToIgnoreCase(o.getName()) < 0) {
            return -1;
        }
        return 0;
    }

    /**
     * This method returns the name of the city
     * 
     * @return
     *         The name of the city
     */
    public String getName() {
        return name;
    }

    /**
     * This method returns whether the city is discovered
     * 
     * @return
     *         whether the city is discovered
     */
    public boolean getDiscovered() {
        return discoverd;
    }

    /**
     * This method returns whether the city is visited
     * 
     * @return
     *         whether the city is discovered
     */
    public boolean getVisited() {
        return visited;
    }

    /**
     * This method returns the city's neighbors as a hashmap
     * 
     * @return
     *         the city's neighbors
     */
    public HashMap<String, Integer> getNeighbors() {
        return neighbors;
    }

    /**
     * This method sets the discovered status to a given discovered
     * 
     * @param discoverd
     *                  given discovered
     */
    public void setDiscovered(boolean discoverd) {
        this.discoverd = discoverd;
    }

    /**
     * This method sets this city's visited status to a given status
     * 
     * @param visited
     *                The given status
     */
    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    /**
     * This method set the city's name to a given name
     * 
     * @param name
     *             The given name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method set the city's neighbors to a given neighbors
     * 
     * @param neighbors
     *                  The given neighbors
     */
    public void setNeighbors(HashMap<String, Integer> neighbors) {
        this.neighbors = neighbors;
    }

}