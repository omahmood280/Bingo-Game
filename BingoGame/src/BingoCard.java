import java.util.Arrays;

public class BingoCard {
    /*
      The two arrays are private and their structure is NEVER exposed to another
      class, which is why the getCardNumbers returns a String that needs
      further processing.

     It is good programming practice to hide data structures (information hiding).
     */
    private int[][] numbers;
    private boolean[][] markedOff;


    private int numberOfRows;
    private int numberOfColumns;

    public BingoCard(int numberOfRows, int numberOfColumns) {
        setNumberOfRows(numberOfRows);
        setNumberOfColumns(numberOfColumns);

        numbers = new int[numberOfRows][numberOfColumns];
        markedOff = new boolean[numberOfRows][numberOfColumns];
        resetMarked();
    }

    public void resetMarked() {
    /*
          Resets the data structure to be entirely false.
     */

        markedOff = new boolean[numberOfRows][numberOfColumns];


    }


    public int getNumberOfRows() {

        return numberOfRows;
    }

    public void setNumberOfRows(int numberOfRows) {
        this.numberOfRows = numberOfRows;
    }

    public int getNumberOfColumns() {

        return numberOfColumns;
    }

    public void setNumberOfColumns(int numberOfColumns) {

        this.numberOfColumns = numberOfColumns;

    }

    public String getCardNumbers() {
    /*
        flattens the numbers array into a single string with each number separated by the currently required separator,
        but with no leading or trailing copies of that separator.
        For example if the separator were currently a single space,
        then no extra spaces are before the first number nor after the last number.
     */


        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers[i].length; j++) {
                sb.append(numbers[i][j]);
                sb.append(Defaults.getNumberSeparator());
            }

    /*
          all the cards are stored as a grid ([][] numbers) of rows / columns, so for example, numbers 3 4 5 6 will be
          printed as follows:
          3  4
          5  6
     */

        }


        return sb.toString();
    }

    public void setCardNumbers(String[] numbersAsStrings) {
    /*
          the array of strings [] numbersAsStrings is cast to an integer as [] numbersList, for I
          set the grid from this list
     */

        int[] numbersList =
                Arrays.stream(numbersAsStrings).mapToInt(Integer::parseInt).toArray();

        for (int j = 0; j < numberOfRows; j++) {
            for (int i = 0; i < numberOfColumns; i++) {
                numbers[j][i] = numbersList[j * numberOfColumns + i];

            }
        }

    /*
         the goal of this method is to get the numbers entered into the [][] numbers format
     */


    }

    public void markNumber(int number) {
    /*
          made use of the [][] markedOff to mark off numbers from [][] numbers as they match
          if they are not matching, an appropriate message is printed
     */
        boolean marked = false;

        for (int j = 0; j < numberOfRows; j++) {
            for (int i = 0; i < numberOfColumns; i++) {
                if (numbers[j][i] == (number)) {
                    System.out.println("Marked off " + number);
                    markedOff[j][i] = true;
                    marked = true;
                    i = numberOfColumns;
                    j = numberOfRows;

                }
            }

        }

        if (!marked) {

            System.out.println("Number " + number + " not on this card");


        }
    }

    public boolean isWinner() {
    /*
          checks if there is a winner or not
          all elements of [][] markedOff are marked off to have a winner (full house)
     */

        int counter = 0;
        int counterMarked = 0;
        for (int j = 0; j < numberOfRows; j++) {
            for (int i = 0; i < numberOfColumns; i++) {
                counter = counter + 1; //keeps track of how many numbers have been checked
                if (markedOff[j][i]) {
                    counterMarked = counterMarked + 1; //keeps track of how many numbers have been marked

                }

            }
        }

        // if at the end when all numbers have been checked and all were marked, it will return true
        return counter == counterMarked;
    }
}


