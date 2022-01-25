package mastermind;

public class CodeMaker {
    private String secretCode = "";
    public CodeMaker(String code){
        secretCode = code;

    }
    public void checkGuess(Guess guess){
        //For loop that checks every index of the guess and compares it with the secret code
            for (int i = 0; i < secretCode.length(); i ++) {
                //CHecks if the index is in the right position and is equal to the secret code
                if (guess.completeGuess.charAt(i) == secretCode.charAt(i)) {
                    guess.setCorrectPositions(guess.getCorrectPositions() + 1);
                }
                //Checks to see if the index is in the secret code but at the wrong position
                else if (secretCode.contains(String.valueOf(guess.completeGuess.charAt(i)))) {
                    guess.setWrongPositions(guess.getWrongPositions() + 1);
                }
            }

    }

    public String getSecretCode() {
        return secretCode;
    }
}
