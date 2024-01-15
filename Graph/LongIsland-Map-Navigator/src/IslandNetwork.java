
// Name: Phi Long Bui, ID: 114555975, R-01
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import big.data.DataSource;

/**
 * This class repersents a network as a graph of City
 * 
 * @author Phi Long Bui
 */
public class IslandNetwork {
    private HashMap<String, City> graph = new HashMap<String, City>();
    private int flow = 0;
    private int countPath = 0;
    static String[] roads;

    /**
     * This contructor creates the network object
     * 
     * @param graph
     *              The network's graph
     */
    public IslandNetwork(HashMap<String, City> graph) {
        this.graph = graph;
    }

    /**
     * This method returns the network's graph
     * 
     * @return
     *         The network's graph
     */
    public HashMap<String, City> getGraph() {
        return graph;
    }

    /**
     * This method sets the current network to the
     * 
     * @param graph
     *              The given graph
     */
    public void setGraph(HashMap<String, City> graph) {
        this.graph = graph;
    }

    /**
     * This method return a list of city's name in alphabetical arder
     * 
     * @param key
     *            The given set of city's name
     * @return
     *         a list of city's name in alphabetical arder
     */
    public static String[] sort(Set<String> key) {
        String[] city = new String[key.size()];
        int count = 0;
        for (String i : key) {
            city[count++] = i;
        }
        int i, j;
        for (i = 0; i <= city.length - 2; i++) {
            for (j = i + 1; j <= city.length - 1; j++) {
                if (city[j].toLowerCase().charAt(0) < city[i].toLowerCase().charAt(0)) {
                    String tmp = city[i];
                    city[i] = city[j];
                    city[j] = tmp;
                }
            }
        }
        return city;
    }

    /**
     * This method loads the file from the given URL location
     * 
     * @param url
     *            the given URL
     * @return
     *         The Island network from the file
     */
    public static IslandNetwork loadFromFile(String url) {
        HashMap<String, City> tmpGraph = new HashMap<String, City>();
        DataSource ds = DataSource.connectXML(url);
        ds.load();

        String cityNamesStr = ds.fetchString("cities");

        String[] cityNames = cityNamesStr.substring(1, cityNamesStr.length() - 1)
                .replace("\"", "")
                .split(",");

        String roadNamesStr = ds.fetchString("roads");
        String[] roadNames = roadNamesStr.substring(2, roadNamesStr.length() - 2)
                .split("\",\"");
        System.out.println("\nMap loaded.");
        for (String i : cityNames) {
            tmpGraph.put(i, new City(i));
        }
        IslandNetwork network = new IslandNetwork(tmpGraph);
        System.out.printf("\nCities:\n---------------------\n");
        for (String i : IslandNetwork.sort(network.getGraph().keySet())) {
            System.out.printf("%s\n", i);
        }
        int count = 0;
        System.out.printf("\n%-34s %s\n", "Road", "Capacity");
        System.out.print("----------------------------------------------\n");
        roads = new String[roadNames.length];
        for (String i : roadNames) {
            roads[count++] = i;
            String[] roads = i.split(",");
            network.getGraph().get(roads[0]).getNeighbors().put(roads[1], Integer.parseInt(roads[2]));
            System.out.printf("%-37s %d\n", String.format("%s to %s", roads[0], roads[1]), Integer.parseInt(roads[2]));
        }
        return network;

    }

    /**
     * This method resets the graph capacity.
     */
    public void resetCapacity() {
        for (String i : roads) {
            String[] roads = i.split(",");
            graph.get(roads[0]).getNeighbors().put(roads[1], Integer.parseInt(roads[2]));
        }
    }

    /**
     * This method prints the route of the paths and the maximum flow of the graph
     * 
     * @param from
     *             The starting city
     * @param to
     *             The destination
     */
    public void maxFlow(String from, String to) {
        int maxFlow = 0;
        countPath = 0;
        List<Integer> capacity = new ArrayList<Integer>();
        resetVisited();
        resetCapacity();
        System.out.println("Routing:");
        while (DFSMaxFlow(graph.get(from), from, to, capacity) > 0) {
            maxFlow += flow;
        }
        if (countPath != 0) {
            System.out.printf("Maximum Flow: %d\n", maxFlow);
        } else {
            System.out.println("No route available!");
        }
    }

    /**
     * This method update the path's compacity with the minimum flow
     * 
     * @param visited
     *                The path
     * @param minFlow
     *                The minimum flow
     */
    public void updatePath(List<String> visited, int minFlow) {
        int i = 0;
        int j = 1;
        int tmp = 0;
        while (j != visited.size()) {
            if (graph.get(visited.get(i)).getNeighbors().size() != 0) {
                tmp = graph.get(visited.get(i)).getNeighbors().get(visited.get(j));
                graph.get(visited.get(i)).getNeighbors().replace(visited.get(j), tmp - minFlow);

            }
            j += 1;
            i += 1;
        }
    }

    /**
     * This method gets the minimum capacity of the path
     * 
     * @param capacity
     *                 The capacity
     * @return
     *         the minimum capacity of the path
     */
    public int minFlow(List<Integer> capacity) {
        int result = Integer.MAX_VALUE;
        for (int i = 1; i < capacity.size(); i++) {
            if (capacity.get(i) != 0) {
                result = Math.min(result, capacity.get(i));
            }
        }
        return result;
    }

    /**
     * This method run the helper method and return the flow of the path
     * 
     * @param source
     *                    The starting/current city
     * @param from
     *                    The name of the starting city
     * @param destination
     *                    The destination
     * @param capacity
     *                    The capacity
     * @return
     *         The flow of the path
     */
    public int DFSMaxFlow(City source, String from, String destination, List<Integer> capacity) {
        flow = 0;
        List<String> visited = new ArrayList<String>();
        helperMaxFlow(source, from, destination, capacity, visited);

        return flow;
    }

    /**
     * This method helps traverse the graph using DFS
     * and find a path and compute the flow of the path.
     * 
     * @param from
     *                    The starting/current city
     * @param source
     *                    The name of the starting city
     * @param destination
     *                    The destination
     * @param capacity
     *                    The capacity
     * @param visited
     *                    The list of visited cities
     * @return
     *         The false when there is no path true otherwise
     */
    public boolean helperMaxFlow(City from, String source, String destination, List<Integer> capacity,
            List<String> visited) {
        if (from.getVisited()) {
            return false;
        }

        if (from.getName().equals(destination)) {
            graph.get(source).setVisited(false);
            visited.add(from.getName());

            countPath += 1;
            for (String i : visited) {

                if (i.equals(destination)) {
                    System.out.printf("%s: ", i);
                } else {
                    System.out.printf("%s->", i);
                }
            }

            flow = minFlow(capacity);
            System.out.printf("%s\n", flow);
            updatePath(visited, flow);
            return true;
        } else {
            from.setVisited(true);
            visited.add(from.getName());

        }
        for (String i : IslandNetwork.sort(from.getNeighbors().keySet())) {
            capacity.add(from.getNeighbors().get(i));
            if (helperMaxFlow(graph.get(i), source, destination, capacity, visited)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This methods returns all reachable City from the given source
     * 
     * @param from
     *             The given source
     * @return
     *         all reachable City from the given source
     * @throws Exception
     *                   If the given source don't have any neighbors
     */
    public List<String> dfs(String from) throws Exception {
        List<String> list = new ArrayList<String>();
        if (graph.get(from).getNeighbors().size() == 0) {
            throw new Exception(from + " has no neighbors");
        } else {
            HashMap<String, City> tmp = graph;

            helperDFS(from, tmp.get(from), list, tmp);
        }
        return list;
    }

    /**
     * This helper class to helps traverse the graph using DFS
     * 
     * @param source
     *               The name of the starting point of the city
     * @param from
     *               The starting city
     * @param list
     *               Visited City
     * @param graph
     *               The city network
     * @return
     */
    public boolean helperDFS(String source, City from, List<String> list, HashMap<String, City> graph) {
        if (!from.getName().equals(source)) {
            if (list.contains(from.getName())) {
                return false;
            }
            list.add(from.getName());
        }
        for (String i : IslandNetwork.sort(from.getNeighbors().keySet())) {
            if (helperDFS(source, graph.get(i), list, graph)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method sets every City visited status to false
     */
    public void resetVisited() {
        for (City i : graph.values()) {
            i.setVisited(false);
        }
    }

    /**
     * This method returns the next node to travel or the smallest neighbor.
     * 
     * @param current
     *                The current city
     * @param cost
     *                the cost to travel to each city
     * @return
     *         the next node to travel or the smallest neighbor.
     */
    public String getMinVex(City current, HashMap<String, Integer> cost) {
        int minNum = Integer.MAX_VALUE;
        String minVex = "";
        for (String i : cost.keySet()) {
            if (minNum > cost.get(i)
                    && !graph.get(i).getVisited()) {
                minNum = cost.get(i);
                minVex = i;
            }
        }
        return minVex;
    }

    /**
     * This method prints the shortest path from the starting city to the
     * destination and the cost of the path
     * 
     * @param from
     *             The starting city
     * @param to
     *             The destination
     */
    public void djikstra(String from, String to) {
        resetVisited();
        HashMap<String, Integer> cost = new HashMap<String, Integer>();
        HashMap<String, City> paths = new HashMap<String, City>();
        for (String i : graph.keySet()) {
            cost.put(i, Integer.MAX_VALUE);
            paths.put(i, new City(i));
        }
        cost.replace(from, 0);
        while (!graph.get(to).getVisited()) {
            String minVex = getMinVex(graph.get(from), cost);
            graph.get(minVex).setVisited(true);
            for (String neighbor : graph.get(minVex).getNeighbors().keySet()) {
                if (!graph.get(neighbor).getVisited()
                        && graph.get(minVex).getNeighbors().get(neighbor) != 0
                        && cost.get(minVex) + graph.get(minVex).getNeighbors().get(neighbor) < cost.get(neighbor)) {
                    cost.replace(neighbor, cost.get(minVex) + graph.get(minVex).getNeighbors().get(neighbor));
                    HashMap<String, Integer> prev = new HashMap<String, Integer>();
                    prev.put(minVex, cost.get(minVex));
                    paths.get(neighbor).setNeighbors(prev);
                }
            }
        }
        List<String> result = new ArrayList<String>();
        paths.get(to).setVisited(true);
        int vexInt = Integer.MAX_VALUE;
        String vexStr = to;
        while (!paths.get(from).getVisited()) {
            for (String i : paths.get(vexStr).getNeighbors().keySet()) {
                if (vexInt > paths.get(vexStr).getNeighbors().get(i)) {
                    vexInt = paths.get(vexStr).getNeighbors().get(i);
                    vexStr = i;
                }
            }
            paths.get(vexStr).setVisited(true);
            result.add(vexStr);
        }
        System.out.println("");
        while (!result.isEmpty()) {
            System.out.printf("%s->", result.remove(result.size() - 1));
        }
        System.out.printf("%s", to);
        System.out.printf("\nCost: %d\n", cost.get(to));
    }
}
