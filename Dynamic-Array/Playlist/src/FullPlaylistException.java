// Name: Phi Long Bui, ID: 114555975, R-30
/**
 * This class represents an Exception when the playlist is full
 * 
 * @author Phi Long Bui
 */
public class FullPlaylistException extends Exception {
    /**
     * This is the Contructor for the Expection
     */
    public FullPlaylistException() {
        super("The playlist is full");
    }
}
