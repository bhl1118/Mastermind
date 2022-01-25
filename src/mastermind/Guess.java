package mastermind;


public class Guess {
    public String completeGuess = "";
    private int correctPositions = 0;
    private int wrongPositions = 0;
    private int guessNum = 1;
    private static int count=1;

    public Guess(String guess) {
        completeGuess = guess;
        guessNum = count++;
    }

    //Returns the number of correct positions
    public int getCorrectPositions() {
        return correctPositions;
    }

    //Sets the number of correct positions
    public void setCorrectPositions(int correctPositions) {
        this.correctPositions = correctPositions;
    }

    //returns the number of wrong positions
    public int getWrongPositions() {
        return wrongPositions;
    }

    //Sets the number of wrong positions
    public void setWrongPositions(int wrongPositions){
        this.wrongPositions = wrongPositions;
    }

    @Override
    public String toString() {
        //Initializes the string values of the right and wrong positions
        String wrong = String.valueOf(getWrongPositions());
        String right = String.valueOf(getCorrectPositions());

        //String representation of the guess
        String s = "Guess #" + guessNum + ": " + completeGuess + " (B:" + right + " W:" + wrong + ")";
        //returns the string
        return s;
    }

}
