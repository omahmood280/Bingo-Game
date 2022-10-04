import java.util.Scanner;

public class ToolKit {
    private static final Scanner stdIn = new Scanner(System.in);


    public static final String GOODBYEMESSAGE = "Thank you for playing";

    public static String getInputForMessage(String message) {

        System.out.println(message);

        return stdIn.nextLine();
    }

    public static String printArray(String[] array) {
        StringBuilder sb = new StringBuilder();
    /*
        Uses a loop to print the numbers out once a user has inputted the BingoCard numbers, separated by commas (trims leading / trailing spaces)
        return as a sb.toString()
   */
        for (int i = 0; i < array.length; i++) {

            sb.append(array[i]);
            if (i != array.length - 1) { //this loop is so a comma isn't printed at the end of the last number
                sb.append(", ");
            }

        }
        return sb.toString().trim();

    }
}
