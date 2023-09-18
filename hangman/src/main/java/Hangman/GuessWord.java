package Hangman;

import java.util.ArrayList;
import java.util.List;
 
public class GuessWord extends Guess{
    
    private List<Character> illegalLetters = new ArrayList<Character>();

    public GuessWord(String guess, Game game){
        super(game);
        guessWord(guess);
    }

    
    public void guessWord(String text){
        String word = text.toLowerCase();
        if(isLegalWord(word)){
            if(game.getWord().equals(word)){
                game.setEndTime();
                char[] letters = word.toCharArray();
                for(char i: letters){
                    game.updateProgress(i);
                }
                resultText = "";
                
            }
            else{
                updateAttempts();
                System.out.print("gjettet feil ord");
                resultText =  "'"+word+"' was not the correct word...";
            }
        }else{
            System.out.print("ulovlig ord");
            resultText = "'"+word+"' is not a legal word...";
        }
    }

    

    public boolean isLegalWord(String word){
        char[] letters = word.toCharArray();
        for(char i: letters){
            if(!(checkLegalLetter(i))){
                illegalLetters.add(i);
            }
        }
        if(illegalLetters.size()>0){
            return false;
        }else{
            return true;
        }
    }


}
