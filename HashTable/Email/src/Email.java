
//Name: Phi Long Bui ID: 114555975 R-30
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * This class is for the email Object. 
 * 
 * @author Phi Long Bui
 */
public class Email implements Serializable {
    private String to, cc, bcc, subject, body;
    private GregorianCalendar timestamp;

    /**
     * This Contructor is for Email
     * 
     * @param to
     *                The String literal which stores the “to” field.
     * @param cc
     *                The String literal which stores the “cc” field.
     * @param bcc
     *                The String literal which stores the “bcc” field.
     * @param subject
     *                The String literal which stores the “subject” field.
     * @param body
     *                The String literal which stores all of the text in the email’s
     *                body.
     */
    public Email(String to, String cc, String bcc, String subject, String body) {
        this.to = to;
        this.cc = cc;
        this.bcc = bcc;
        this.body = body;
        this.subject = subject;
        this.timestamp = new GregorianCalendar(TimeZone.getTimeZone("GMT-4"));
    }

    /**
     * This method gets the String literal which stores the “to” field.
     * 
     * @return
     *         The String literal which stores the “to” field.
     */
    public String getTo() {
        return to;
    }

    /**
     * This method gets the String literal which stores the “cc” field.
     * 
     * @return
     *         The String literal which stores the “cc” field.
     */
    public String getCC() {
        return cc;
    }

    /**
     * This method gets the String literal which stores the “bcc” field.
     * 
     * @return
     *         The String literal which stores the “bcc” field.
     */
    public String getBcc() {
        return bcc;
    }

    /**
     * This method gets the String literal which stores the “subject” field.
     * 
     * @return
     *         The String literal which stores the “subject” field.
     */
    public String getSubject() {
        return subject;
    }

    /**
     * This method gets the String literal which stores all of the text in the
     * email’s body.
     * 
     * @return
     *         The String literal which stores all of the text in the email’s body.
     */
    public String getBody() {
        return body;
    }

    /**
     * This method gets the current time in HH:mma d/M/yyyy format
     * 
     * @return
     */
    public String getTime() {
        SimpleDateFormat tmp = new SimpleDateFormat("HH:mma d/M/yyyy");

        return String.format("%s", tmp.format(timestamp.getTime()));
    }

    /**
     * This method sets the current String literal which stores the “to” field to
     * the given one
     * 
     * @param to
     *           The given String literal which stores the “to” field.
     */
    public void setTo(String to) {
        this.to = to;
    }

    /**
     * This method sets the current String literal which stores the “cc” field to
     * the given one
     * 
     * @param cc
     *           The given String literal which stores the “cc” field.
     */
    public void setCC(String cc) {
        this.cc = cc;
    }

    /**
     * This method sets the current String literal which stores the “bcc” field to
     * the given one
     * 
     * @param cc
     *           The given String literal which stores the “bcc” field.
     */
    public void setBcc(String bcc) {
        this.bcc = bcc;
    }

    /**
     * This method sets the current String literal which stores all of the text in
     * the email’s body to the given one
     * 
     * @param body
     *             The given String literal which stores all of the text in the
     *             email’s body.
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * This method sets the current String literal which stores the “subject” field
     * to a given one
     * 
     * @param subject
     *                The given String literal which stores the “subject” field.
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * This is the toString method
     * 
     * @return a String for the Email
     */
    public String toString() {
        String str = "";
        str += String.format("To: %s\n", to);

        str += String.format("CC: %s\n", cc);

        str += String.format("BCC: %s\n", bcc);

        str += String.format("Subject: %s\n", subject);

        str += String.format("%s\n", body);
        return str;
    }

}
