
// Name: Phi Long Bui, ID: 114555975, R-01
import java.io.Serializable;

/**
 * This class repersents a storm which contains a name,
 * precipitation, wind speed, and the date of the storm.
 * 
 * @author Name: Phi Long Bui
 */
public class Storm implements Serializable {
    private String name;
    private double precipitation;
    private double windspeed;
    private String Date;

    /**
     * This Constructor used to create a new Storm object
     * 
     * @param name
     *                      The given name of the storm
     * @param Date
     *                      The date the storm happens
     * @param windspeed
     *                      The windspeed of the storm
     * @param precipitation
     *                      The precipitation of the storm
     */
    public Storm(String name, String Date, double windspeed, double precipitation) {
        this.name = name;
        this.Date = Date;
        this.windspeed = windspeed;
        this.precipitation = precipitation;
    }

    /**
     * This method returns the name of the storm
     * 
     * @return
     *         The name of the storm
     */
    public String getName() {
        return name;
    }

    /**
     * This method return the precipitation of the storm
     * 
     * @return
     *         The precipitation of the storm
     */
    public double getPrecipitation() {
        return precipitation;
    }

    /**
     * This method return the wind speed of the storm
     * 
     * @return
     *         The windspeed of the storm
     */
    public double getWindspeed() {
        return windspeed;
    }

    /**
     * This method returns the date of the storm occurrence
     * 
     * @return
     *         The date of the storm occurrence
     */
    public String getDate() {
        return Date;
    }

    /**
     * This method sets the name of the storm to a given name
     * 
     * @param name
     *             The given name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method sets the the precitation of the storm to the given precitation
     * 
     * @param precipitation
     *                      The given precitation
     */
    public void setPrecipitation(double precipitation) {
        this.precipitation = precipitation;
    }

    /**
     * This method sets the storm windspeed to the given windspeed
     * 
     * @param windspeed
     */
    public void setWindspeed(double windspeed) {
        this.windspeed = windspeed;
    }

    /**
     * This methos sets the storm's date to the given date
     * 
     * @param Date
     *             The given date
     */
    public void setDate(String Date) {
        this.Date = Date;
    }
}
