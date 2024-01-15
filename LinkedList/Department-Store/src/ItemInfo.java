// Name: Phi Long Bui, ID: 114555975, R-30
/**
 * This class contains various information about a specific item that can or has
 * been sold in a given department store. Include the product's name(String) and
 * price(a positive double) as fields
 * 
 * @author Phi Long Bui
 */
public class ItemInfo {
    private String location, currentLocation, name, rfidTagNumber;
    private double price;

    /**
     * This Contructor is for Item info
     * 
     * @param name
     *                      name of the item
     * @param rfidTagNumber
     *                      rfid of the item
     * @param location
     *                      location of the item
     * @param price
     *                      price of the item
     */
    public ItemInfo(String name, String rfidTagNumber, String location, double price) {
        this.name = name;
        this.price = price;
        this.rfidTagNumber = rfidTagNumber;
        this.location = location;
    }

    /**
     * This method gets the name of the item
     * 
     * @return
     *         The name of the item
     */
    public String getName() {
        return name;
    }

    /**
     * This method gets the price of the item
     * 
     * @return
     *         The price of the item
     */
    public double getPrice() {
        return price;
    }

    /**
     * This method gets the rfid of the item
     * 
     * @return
     *         the rfid of the item
     */
    public String getRfidTagNumber() {
        return rfidTagNumber;
    }

    /**
     * This method gets the location of the item
     * 
     * @return
     *         the location of the item
     */
    public String getLocation() {
        return location;
    }

    /**
     * This method gets the current locaiton of the item
     * 
     * @return
     *         the current location of the item
     */
    public String getCurrentLocaion() {
        return currentLocation;
    }

    /**
     * This method sets the item name to the given name
     * 
     * @param name
     *             the given name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method sets the item price to the given price
     * 
     * @param price
     *              The given price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * This method set the rfdi to the given rfid
     * 
     * @param rfidTagNumber
     *                      The given rfid
     */
    public void setRfiNumber(String rfidTagNumber) {
        this.rfidTagNumber = rfidTagNumber;
    }

    /**
     * This method sets the item location to the given location
     * 
     * @param location
     *                 the given location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * This method sets the item location to the given location
     * 
     * @param location
     *                 The given location
     */
    public void setCurrentLocation(String location) {
        this.currentLocation = location;
    }
}