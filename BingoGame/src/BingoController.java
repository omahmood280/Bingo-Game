import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class BingoController {


    private final String[] mainMenuItems = {"Exit",
            "Play bingo",
            "Set number separator",
            "Create a bingo card",
            "List existing cards",
            "Set bingo card size"};

    /*
          Constants attached to mainMenuItems
     */

    private final String OPTION_EXIT = "0";
    private final String OPTION_PLAY = "1";
    private final String OPTION_SEPARATOR = "2";
    private final String OPTION_CREATE_CARD = "3";
    private final String OPTION_LIST_CARDS = "4";
    private final String OPTION_SIZE = "5";

    /*
         default size of rows / columns as specified in the Defaults class
           */

    private int currentRowSize = Defaults.DEFAULT_NUMBER_OF_ROWS;
    private int currentColumnSize = Defaults.DEFAULT_NUMBER_OF_COLUMNS;

    /*
          an ArrayList of BingoCard cards
     */

    ArrayList<BingoCard> cards = new ArrayList<>();


    //implement code here



    public int getCurrentRowSize() {

        return currentRowSize;
    }

    public void setCurrentRowSize(int currentRowSize) {

        this.currentRowSize = currentRowSize;
    }

    public int getCurrentColumnSize() {

        return currentColumnSize;
    }

    public void setCurrentColumnSize(int currentColumnSize) {

        this.currentColumnSize = currentColumnSize;
    }

    /*
          adds a new BingoCard card
     */

    public void addNewCard(BingoCard card) {
        cards.add(card);
    }



    public void setSize() {


        setCurrentRowSize(parseInt(ToolKit.getInputForMessage(
                "Enter the number of rows for the card")));

        setCurrentColumnSize(parseInt(ToolKit.getInputForMessage(
                "Enter the number of columns for the card")));


        System.out.printf("The bingo card size is set to %d rows X %d columns%n",
                getCurrentRowSize(),
                getCurrentColumnSize());
    }

    /*
           ensures that the correct amount of numbers are entered
           prints a message that shows the numbers entered using ToolKit.printArray(numbers)
           Creates a card, sets the Card Numbers and adds the card as a BingoCard
     */
    public void createCard() {

        /*
              calculates how many numbers are required to be entered based on the number of rows / columns
         */
        int numbersRequired = currentColumnSize * currentRowSize;

        String[] numbers;

        boolean correctAmountOfNumbersEntered;

        do {
            numbers = ToolKit.getInputForMessage(
                            String.format(
                                    "Enter %d numbers for your card (separated by " +
                                            "'%s')",
                                    numbersRequired,
                                    Defaults.getNumberSeparator()))
                    .trim()
                    .split(Defaults.getNumberSeparator());
        /*
              verifies if the correctAmountOfNumbersEntered is true or false depending on the numbersRequired calculation
         */
            correctAmountOfNumbersEntered = false; //changes according to calculation inserted here


            if (numbers.length == numbersRequired) {
                correctAmountOfNumbersEntered = true;
            } else {
                System.out.printf("Try again: you entered %d numbers instead of %d%n", numbers.length, numbersRequired);
            }
        /*
              verifies whether the numbers entered is not correct and prints an appropriate message
         */

        } while (!correctAmountOfNumbersEntered);

        /*
              prints an appropriate message using ToolKit.printArray() to show the numbers entered
         */

        System.out.println("You entered"); //insert code here
        System.out.println(ToolKit.printArray(numbers));

                /*
             creates a new bingo card then sets up the card numbers and adds the card to the Array List
         */
        BingoCard bingoCard = new BingoCard(currentRowSize, currentColumnSize);
        bingoCard.setCardNumbers(numbers);
        addNewCard(bingoCard);

    }

    /*
         this method goes with printCardAsGrid() seen below
         when option 4 is chosen to list existing cards it prints each card accordingly
         for example, it shows the following
         Card 0 numbers:
         1  2
         3  4 (check with expected output files)
    */
    public void listCards() {
        /*
              Finds all cards to be printed accordingly
         */
        for (int i = 0; i < cards.size(); i++) {

            System.out.println("Card " + i + " numbers:");
            printCardAsGrid(cards.get(i).getCardNumbers());


        }


    }

    /*
     This is a follow on method from listCards() and ensures that the grid structure is printed
     */

    public void printCardAsGrid(String numbers) {

        numbers = numbers.trim();

        int counter = 0;
        String[] numSplit = numbers.split(Defaults.getNumberSeparator());

        for (int i = 0; i < numSplit.length; i++) {

            counter = counter + 1;

            System.out.print(numSplit[i]);

            if (counter != getCurrentColumnSize()) { //this is so when it reaches the end of the first row it doesn't print the separator
                System.out.print(Defaults.getNumberSeparator());

            } else {
                counter = 0; //start of a new row
                System.out.println();
            }
        }


    }


    /*
          uses ToolKit.getInputForMessage to enter a new separator
          print a message saying what the new separator is
     */
    public void setSeparator() {
        String sep = ToolKit.getInputForMessage("Enter the new separator");


        Defaults.setNumberSeparator(sep);

        System.out.printf("Separator is %s %n", Defaults.getNumberSeparator());
    }

    /*
         resets all BingoCards using resetMarked (to false)
     */
    public void resetAllCards() {
        //insert code here
        for (int i = 0; i < cards.size(); i++) {
            cards.get(i).resetMarked();
        }
    }

    /*
          marks off a number that was called when it equals one of the numbers on the BingoCard
     */
    public void markNumbers(int number) {
        //insert code here


        for (int i = 0; i < cards.size(); i++) {
            System.out.println("Checking card " + i + " for " + number);
            cards.get(i).markNumber(number);


        }
    }


    /*
          makes use of isWinner() to determine who the winner is
         Returns the index of who the winner is
     */
    public int getWinnerId() {
        //insert code here
        int returnValue = Defaults.NO_WINNER;
        boolean winnerFound = false;
        for (int i = 0; i < cards.size() && !winnerFound; i++) {
            if (cards.get(i).isWinner()) {
                returnValue = i;
                winnerFound = true;


            }

        }
        return returnValue;
    }

    /*
          Note: The game will not end until there is a winning sequence
     */

    public void play() {
        System.out.println("Eyes down, look in!");
        resetAllCards();

        boolean weHaveAWinner;
        do {
            markNumbers(parseInt(
                    ToolKit.getInputForMessage("Enter the next number")
                            .trim()));

            int winnerID = getWinnerId();
            weHaveAWinner = winnerID != Defaults.NO_WINNER;
            if (weHaveAWinner)
                System.out.printf("And the winner is card %d%n", winnerID);
        } while (!weHaveAWinner);
    }

    public String getMenu(String[] menuItems) {

    /*
        Prints a numbered menu
        menuText is returned
     */

        StringBuilder menuText = new StringBuilder();
        menuText.append(" " + OPTION_EXIT + ": ").append(menuItems[0]).append("\n ")
                .append(OPTION_PLAY).append(": ").append(menuItems[1]).append("\n ")
                .append(OPTION_SEPARATOR).append(": ").append(menuItems[2]).append("\n ")
                .append(OPTION_CREATE_CARD).append(": ").append(menuItems[3]).append("\n ")
                .append(OPTION_LIST_CARDS).append(": ").append(menuItems[4]).append("\n ")
                .append(OPTION_SIZE).append(": ").append(menuItems[5]);

        return menuText.toString();
    }

    /*
          completes the menu using switch to call the appropriate method calls
     */
    public void run() {

        boolean finished = false;
        do {
            switch (ToolKit.getInputForMessage(getMenu(mainMenuItems))) {
                //insert code here
                case "0":

                    finished = true;
                    break;

                case "1":
                    play();
                    break;

                case "2":
                    setSeparator();
                    break;

                case "3":
                    createCard();
                    break;

                case "4":
                    listCards();
                    break;

                case "5":
                    setSize();
                    break;

            }
        } while (!finished);

    }
}

