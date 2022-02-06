import java.util.Scanner;

/**
 * Console class reads inputs
*/
public class Console {
    
    private static final Scanner sc = new Scanner(System.in);
    
    /**
     * Reads string
     * @param prompt message intended to give user directions
     * @return line that's read in
    */
    public static String getLine(String prompt) {
        System.out.print(prompt);
        String s = sc.nextLine();        // read the whole line
        return s;
    }

    /**
     * Reads string
     * @param prompt message intended to give user directions
     * @return input string
    */
    public static String getString(String prompt) {
        System.out.print(prompt);
        String s = sc.next();        // read the first string on the line
        sc.nextLine();               // discard the rest of the line
        return s;
    }

    /**
     * Reads integer
     * @param prompt message intended to give user directions
     * @return input int
    */
    public static int getInt(String prompt) {
        int i = 0;
        boolean isValid = false;
        while (!isValid) {
            System.out.print(prompt);
            if (sc.hasNextInt()) {
                i = sc.nextInt();
                isValid = true;
            } else {
                System.out.println("Error! Invalid integer value. Try again.");
            }
            sc.nextLine();  // discard any other data entered on the line
        }
        return i;
    }

    /**
     * Reads integer
     * @param prompt message intended to give user directions
     * @param min minimum amount
     * @param max maximum amount
     * @return input int
    */
    public static int getInt(String prompt, int min, int max) {
        int i = 0;
        boolean isValid = false;
        while (!isValid) {
            i = getInt(prompt);
            if (i <= min) {
                System.out.println(
                        "Error! Number must be greater than " + min + ".");
            } else if (i >= max) {
                System.out.println(
                        "Error! Number must be less than " + max + ".");
            } else {
                isValid = true;
            }
        }
        return i;
    }

    /**
     * Reads double
     * @param prompt message intended to give user directions
     * @return input int
    */
    public static double getDouble(String prompt) {
        double d = 0;
        boolean isValid = false;
        while (!isValid) {
            System.out.print(prompt);
            if (sc.hasNextDouble()) {
                d = sc.nextDouble();
                isValid = true;
            } else {
                System.out.println("Error! Invalid decimal value. Try again.");
            }
            sc.nextLine();  // discard any other data entered on the line
        }
        return d;
    }

    /**
     * Reads integer
     * @param prompt message intended to give user directions
     * @param min minimum amount
     * @param max maximum amount
     * @return input double
    */
    public static double getDouble(String prompt, double min, double max) {
        double d = 0;
        boolean isValid = false;
        while (!isValid) {
            d = getDouble(prompt);
            if (d <= min) {
                System.out.println(
                        "Error! Number must be greater than " + min + ".");
            } else if (d >= max) {
                System.out.println(
                        "Error! Number must be less than " + max + ".");
            } else {
                isValid = true;
            }
        }
        return d;
    }
}