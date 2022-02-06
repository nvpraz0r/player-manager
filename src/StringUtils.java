/**
  * Utility class
 */
public class StringUtils {

    /**
     * Add space to parameter
     * @param s string to be modified
     * @param length length of space added to string
     * @return formatted string
     * @return string
    */
    public static String padWithSpaces(String s, int length) {
        if (s.length() < length) {
            StringBuilder sb = new StringBuilder(s);
            while (sb.length() < length) {
                sb.append(" ");
            }
            return sb.toString();
        } else {
            return s.substring(0, length);
        }
    }
}