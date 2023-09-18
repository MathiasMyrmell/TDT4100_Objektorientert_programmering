package Hangman;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;
import javafx.scene.shape.QuadCurve;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;


public class HangmanController{

    private Game game;
    @FXML private Button newGame, guessButton;
    @FXML private TextField guess, userName;
    @FXML private TextArea guessedLetters, progress;
    @FXML private Text infoText, winnerText, wrongLetters, score, highscores;

    @FXML private Circle head, leftEye, rightEye;
    @FXML private Rectangle body, rightLeg, leftLeg, rightArm, leftArm;
    @FXML private QuadCurve mouth;
    


    @FXML void guess(){
        String guess = this.guess.getText();
            if(guess.length()!=game.getWord().length() && guess.length()>1){
                infoText.setText("The length of your guess does not have the same lenght as the word you are trying to find, or is not a single letter");
            }else{
                game.makeGuess(guess);
                infoText.setText(game.getInfoText());
                roundUpdate();
            }

    }

    @FXML void roundUpdate(){
        wrongLetters.setText("Wrong letters: "+ Integer.toString(game.getAttempt())+"/7");
        progress.setText(game.displayProgress());
        guessedLetters.setText(game.displayGuessedWords());
        if(game.checkFinished()){
            won();
        }
        else if(game.checkLost()){
            updateFigure();
            lost();
        }
        else{
            updateFigure();
        }
    }

    @FXML void won(){
        guessButton.setDisable(true);
        guess.setDisable(true);
        winnerText.setText("Congratz! You won!");
        score.setText("Score: "+game.getScore());
        wrongLetters.setText("Wrong letters: "+Integer.toString(game.getAttempt()));
    }
    @FXML void lost(){
        guessButton.setDisable(true);
        guess.setDisable(true);
        winnerText.setText("You lost :(");
    }

    @FXML void startNewGame(){
        this.game = new Game(userName.getText());
        guessButton.setDisable(false);
        guess.setDisable(false);
        winnerText.setText("");
        score.setText("");
        progress.setText(game.displayProgress());
        updateFigure();
        guessedLetters.setText(game.displayGuessedWords());
        wrongLetters.setText("Wrong letters: "+ Integer.toString(game.getAttempt())+"/9");
        clearText();
    }

    @FXML void updateFigure(){
        if(game.getAttempt()==0){
            body.setVisible(false);
            head.setVisible(false);
            mouth.setVisible(false);
            rightEye.setVisible(false);
            leftEye.setVisible(false);
            rightArm.setVisible(false);
            leftArm.setVisible(false);
            rightLeg.setVisible(false);
            leftLeg.setVisible(false);
        }else if(game.getAttempt()==1){
            head.setVisible(true);
        }else if(game.getAttempt()==2){
            body.setVisible(true);
        }else if(game.getAttempt()==3){
            rightArm.setVisible(true);
        }else if(game.getAttempt()==4){
            leftArm.setVisible(true);
        }else if(game.getAttempt()==5){
            rightLeg.setVisible(true);
        }else if(game.getAttempt()==6){
            leftLeg.setVisible(true);
        }else if(game.getAttempt()==7){
            rightEye.setVisible(true);
            leftEye.setVisible(true);
            mouth.setVisible(true);
        }
    }

    @FXML void clearText(){
        infoText.setText("");
        guess.clear();
    }

    @FXML void highscore(){
        HighscoreHandler hh = new HighscoreHandler();
        highscores.setText(hh.getHighscores());
    }
}