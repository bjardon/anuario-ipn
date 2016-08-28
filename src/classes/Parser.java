package classes;

/**
 * Created by root on 10/05/15.
 */
public class Parser {

    /**
     * Checks if a given number as a String can be safely converted to a number format
     *
     * @param s the number as a String
     * @return <code>true</code> if the number can be converted, <code>false</code> otherwise
     */
    public Boolean isNumber(String s) {
        try {
            Integer.parseInt(s);
            Long.parseLong(s);
            Float.parseFloat(s);
            Double.parseDouble(s);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Checks if a given number as a String can be safely converted to an Integer
     *
     * @param s the number as a String
     * @return <code>true</code> if the number can be converted, <code>false</code> otherwise
     */
    public Boolean isInt(String s) {
        try {
            Integer.parseInt(s);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

}
