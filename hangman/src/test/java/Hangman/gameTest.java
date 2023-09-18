package Hangman;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class gameTest {

    // @Test
    // @DisplayName("Test constructor")
    // void constructurTester(){

    // }

    @Test
	@DisplayName("Test guesses")
    void guessTester(){
        Game game = new Game("username");
        game.setWord("test");
        assertEquals("test", game.getWord());
        game.makeGuess("t");
        assertEquals("t _ _ t ", game.displayProgress());
        game.makeGuess("E");
        assertEquals("You guessed e", game.getInfoText());
        game.makeGuess("q");
        assertEquals("The letter q is not in the word", game.getInfoText());
        assertEquals(1, game.getAttempt());
        game.makeGuess("");
        assertEquals("You cant guess 'blank'", game.getInfoText());
        game.makeGuess("9");
        assertEquals("'9' is not a legal letter", game.getInfoText());
        game.makeGuess("å");
        assertEquals("'å' is not a legal letter", game.getInfoText());
        game.makeGuess("tEsT");
        assertEquals("", game.getInfoText());
        
    }
    @Test
    @DisplayName("Test load words")
    void loadWords(){
        WordList wL = new WordList();
        Assertions.assertTrue(wL.getNewWordList().size()!=0);
    }

}