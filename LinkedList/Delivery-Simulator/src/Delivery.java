// Name: Phi Long Bui, ID: 114555975, R-01
/**
 * This class repersents a delivery which contains the source,
 * destination, and instruction for a delivery
 * 
 * @author Name: Phi Long Bui
 */
public class Delivery {
    private String source, dest, instruction;

    /**
     * This Constructor used to create a new Delivery object
     * By default, the source, destination, and instruction is set to null
     */
    public Delivery() {
        this.source = null;
        this.dest = null;
        this.instruction = null;
    }

    /**
     * This method return the source of the delivery
     * 
     * @return
     *         the source of the delivery
     */
    public String getSource() {
        return this.source;
    }

    /**
     * This method return the destination of the delivery
     * 
     * @return
     *         the destination of the delivery
     */
    public String getDest() {
        return this.dest;
    }

    /**
     * This method return the instruction of the delivery
     * 
     * @return
     *         the instruction of the delivery
     */
    public String getInstruction() {
        return this.instruction;
    }

    /**
     * This method sets this delivery source to a given source
     * 
     * @param source
     *               The given source
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * This method sets this delivery destination to a given destination
     * 
     * @param dest
     *             The given destination
     */
    public void setDest(String dest) {
        this.dest = dest;
    }

    /**
     * This method sets this delivery instruction to a given intruction
     * 
     * @param instruction
     *                    The given intruction
     */
    public void setIntruction(String instruction) {
        this.instruction = instruction;
    }

    /**
     * This method returns the delivery the source, destination, and instruction
     * in string format
     * 
     * @return
     *         The string of the source, destination, and instruction for a delivery
     */
    public String toString() {
        String str = String.format("|To: %-23s|From: %-23s|%n", dest, source);
        str += String.format("|Intruction: %-45s|%n", instruction);
        return str;
    }
}
