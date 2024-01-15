// Name: Phi Long Bui, ID: 114555975, R-30
/**
 * This class contains information about a particular audio file.
 * It should have member variables for the title and artist (both strings)
 * as well as two member variables for the song's length in minutes and seconds
 * (both ints).
 * 
 * @author Phi Long Bui
 */
public class SongRecord implements Cloneable {
    private int minutes, seconds;
    private String artist, title;

    /**
     * This Contructor creates a SongRecord Object
     * 
     * @param title
     *                The title of the song
     * @param artist
     *                The artist name
     * @param minutes
     *                The minutes unit of the song
     * @param seconds
     *                The seconds unit of the song
     */
    public SongRecord(String title, String artist, int minutes, int seconds) {
        this.title = title;
        this.artist = artist;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    /**
     * This method returns the tilte of the song
     * 
     * @return
     *         The title of the song
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method returns the artist of the song
     * 
     * @return
     *         The artist of the song
     */
    public String getArtist() {
        return artist;
    }

    /**
     * This method returns the minutes unit of the song
     * 
     * @return
     *         The minutes unit of the song
     */
    public int getMinutes() {
        return minutes;
    }

    /**
     * This method returns the seconds unit of the song
     * 
     * @return
     *         The seconds unit of the song
     */
    public int getSeconds() {
        return seconds;
    }

    /**
     * This method set the current title to the given title
     * 
     * @param title
     *              The given title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * This method sets the current artist to the given artist
     * 
     * @param artist
     *               The given artist
     */
    public void setArtists(String artist) {
        this.artist = artist;
    }

    /**
     * This method sets the current minutes to the given minutes
     * 
     * @param minutes
     *                The given minutes
     */
    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    /**
     * This method sets the current seconds to the given seconds
     * 
     * @param seconds
     */
    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    /**
     * This method clones this Song
     * 
     * @return
     *         The clone of this song
     */
    public Object clone() {
        SongRecord clone = null;
        try {
            clone = (SongRecord) super.clone();

        } catch (CloneNotSupportedException e) {
            System.out.println(e.getMessage());
        }

        return clone;
    }

    /**
     * This method compares two object
     * 
     * @return
     *         True If all of the object's property are equal
     */
    public boolean equals(Object obj) {
        if (obj instanceof SongRecord) {
            return (((SongRecord) obj).title.equals(title))
                    && (((SongRecord) obj).artist.equals(artist))
                    && (((SongRecord) obj).minutes == minutes)
                    && (((SongRecord) obj).seconds == seconds);
        }
        return false;
    }

    /**
     * This method returns the information about the audio file on a single line
     * 
     * @return the information about the audio file on a single line
     */
    public String toString() {
        String str = "";
        if (seconds > 10)
            str += String.format("%-16s%-16s%s:%s", title, artist,
                    minutes, seconds);
        else
            str += String.format("%-16s%-16s%s:0%s", title, artist,
                    minutes, seconds);
        return str;
    }
}
