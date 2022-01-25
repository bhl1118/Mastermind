package mastermind;

import java.util.Scanner;

/**
 * The main class for the Mastermind game.  The secret code can be provided
 * as the command line argument, otherwise it will be randomly generated.
 *
 * @author RIT CS
 * @author Brandon Lu
 */
public class MasterMind {
    /** the number of digits in the secret code */
    public final static int SECRET_CODE_LENGTH = 4;
    /** the minimum digit in the code */
    public final static int MIN_CODE_DIGIT = 1;
    /** the maximum digit in the code */
    public final static int MAX_CODE_DIGIT = 8;
    /** the number of turns in the game */
    private final static int MAX_TURNS = 10;


    /**
     * Check that the digits in a code are all in the valid range.
     *
     * @param code the code to check
     * @rit.pre code is of the correct length
     * @return whether the code is valid or not
     */
    //Secret code variable
    public static String secret = "";
    public static Guesses guesses;
    public static CodeMaker cMaker;

    public MasterMind(String secretNumber){
        //Constuctor initializes the secret code, CodeMaker, and guesses
         secret = secretNumber;
         cMaker = new CodeMaker(secret);
          guesses = new Guesses();
    }

    public static boolean codeInRange(String code) {
        for (int i = 0; i < code.length(); ++i) {
            if (Character.getNumericValue(code.charAt(i)) < MIN_CODE_DIGIT ||
                    Character.getNumericValue(code.charAt(i)) > MAX_CODE_DIGIT) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check that there are no duplicates in the code.
     * @param code the code to check
     * @rit.pre code is of the correct length
     * @rit.pre code has all digits in valid range
     * @return whether the code has no duplicates (true) or does have duplicates (false)
     */
    public static boolean codeNoDuplicates(String code) {
        return code.length() == code.chars().distinct().count();
    }

    /**
     * The main method verifies the command line code (if present) and
     * then plays the game.
     *
     */
    public static void playGame(){
        //Iniitalizes the user guess, turn number, and score
        String guess = "";
        int turn = 0;
        float score = 0;
        Scanner key = new Scanner(System.in);
        //FIX SCORE

        System.out.println("Welcome to Mastermind!");
        System.out.println("Enter your 4 digit guesses (q to quit)");

        while (turn < MAX_TURNS){
            System.out.print("? ");
            guess = key.next();
            //Checks if the user inputted "q" to quit
            if (guess.equals("q")){
                break;
            }
            //Checks to make sure the guess is valid
            if (!codeInRange(guess)){
                System.out.println("Guess must have all digits between "+ MIN_CODE_DIGIT + " and "+ MAX_CODE_DIGIT);
            }
            else if (!codeNoDuplicates(guess)){
                System.out.println("Guess cannot have duplicate digits!");
            }
            else if(guess.length()!=SECRET_CODE_LENGTH){
                System.out.println("Guess must be four digits!");
            }
            else{
                //Creates a Guess object and passes the user guess
                Guess g = new Guess(guess);

                //Adds the guess to the list of guesses and checks the guess
                guesses.addGuess(g);
                cMaker.checkGuess(g);
                guesses.displayGuesses();
                //Calculates the score of the user guess
                score += (g.getCorrectPositions()*2) + g.getWrongPositions();
                //Increments the turn number
                turn++;
                //Checks if the user guessed the secret code
                if (guess.equals(secret)){
                    System.out.println("You won!");
                    break;
                }
            }
        }
        //Output if the user lost the game (did not guess the secret code)
        if (!guess.equals(secret)){
            System.out.println("You lost! The secret code was: " + secret);
        }
        //Outputs the user score
        System.out.println("Code breaker score: " + score);
    }


    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java Mastermind secret-number");
            return;
        } else {
            if (args[0].length() != SECRET_CODE_LENGTH) {
                System.out.println("Secret number " + args[0] +
                        " is not length " + SECRET_CODE_LENGTH);
                return;
            } else if (!codeInRange(args[0])) {
                System.out.println("Secret number " + args[0] +
                        " is invalid.  Expect all digits to be between " +
                        MIN_CODE_DIGIT + " and " +
                        MAX_CODE_DIGIT);
                return;
            } else if (!codeNoDuplicates(args[0])) {
                System.out.println("Secret number " + args[0] +
                        " is invalid.  Has duplicate digits.");
                return;
            }
        }
        //Creates a MasterMind object and passes it the secret code
        MasterMind game = new MasterMind(args[0]);
        //Plays the game
        game.playGame();
    }
}
