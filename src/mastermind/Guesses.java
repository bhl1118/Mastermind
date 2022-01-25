package mastermind;

import java.util.ArrayList;

public class Guesses {
public ArrayList<Guess> guesses;
//Initializes the guesses
public Guesses(){
    guesses = new ArrayList<>();
}
//Adds a guess to the list of guesses
public void addGuess(Guess guess){
    guesses.add(guess);
}

//Displays a string representation of all of the guesses
public void displayGuesses(){
    for (int i = 0; i < guesses.size(); i++){
        System.out.println(guesses.get(i));
        guesses.get(i).toString();
    }
}

}
