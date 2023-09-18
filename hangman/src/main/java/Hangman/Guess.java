package Hangman;

import java.util.Arrays;
import java.util.List;

public class Guess {
    public Game game;
    private List<Character> legalLetters = Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z');
    public String resultText;
    
    public Guess(Game game){
        this.game=game;
    }

    public String result(){
        return this.resultText; 
    }

    
    public void guessLetter(char text){
        char letter = Character.toLowerCase(text);
        if(checkLegalLetter(letter)==false){
            this.resultText =  "'"+letter+"'"+ " is not a legal letter";
        }else if(isGuessed(letter)){
            this.resultText = "Letter "+letter+" is already guessed";
        }else if(!(game.getWord().contains(Character.toString(letter)))){
            game.addGuessedLetter(letter);
            updateAttempts();
            this.resultText = "The letter "+letter+" is not in the word";
        }
        else{
            game.addGuessedLetter(letter);
            game.updateProgress(letter);
            this.resultText = "You guessed "+letter;
        }
    }

    public void updateAttempts(){
        game.updateAttempts();
    }

    
    public boolean checkLegalLetter(char c){
        if(legalLetters.contains(c)){
            return true;
        }else{
            return false;
        }
    }
    public boolean isGuessed(char letter){
        if(game.getGuessedLetters().contains(Character.toLowerCase(letter))){
            return true;
        }else{
            return false;
        }
    }








}
