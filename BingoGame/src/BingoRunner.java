public class BingoRunner {
    public static void main(String[] args) {
    /*
          creates and executes a new BingoController that starts the game
          invokes run()
          includes a Thank you note for playing once the game exits (GOODBYEMESSAGE)
     */
        BingoController object = new BingoController();
        object.run();
        System.out.printf("%n" + ToolKit.GOODBYEMESSAGE + "%n");


    }
}
