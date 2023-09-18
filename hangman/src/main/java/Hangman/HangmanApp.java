package Hangman;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class HangmanApp extends Application{
    @Override
    public void start(final Stage primaryStage) throws Exception{
        primaryStage.setTitle("Hangman");
        primaryStage.setScene(new Scene(FXMLLoader.load(HangmanApp.class.getResource("test.fxml"))));
        primaryStage.show();
    }

    public static void main(String[] args) {
        HangmanApp.launch(args);
    }
   

} 