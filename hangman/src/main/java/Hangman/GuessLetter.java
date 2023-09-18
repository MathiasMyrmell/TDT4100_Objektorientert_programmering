package Hangman;

public class GuessLetter extends Guess{

    public GuessLetter(char guess, Game game){
        super(game);
        guessLetter(guess);
    }
}
