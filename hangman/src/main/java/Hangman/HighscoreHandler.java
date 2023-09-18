package Hangman;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HighscoreHandler {
    private String filename = "minegenkode\\src\\main\\java\\Hangman\\files\\highscores.txt";
    private List<String> highscores = new ArrayList<String>();
    public HighscoreHandler(){
        if(!(openFile())){
            createFile();
        }
    }
    
    public String getHighscores(){
        loadHighScores();
        String text="";
        for(String score:highscores){
            text+=score+'\n';
        }
        return text;
    }
    
    public void saveScore(String name, Integer score){
        updateHigscoreList(name, score);
        try{
            FileWriter fileWriter = new FileWriter(filename);
            for(String i: highscores){
                fileWriter.write(i+"\n");
            }
            fileWriter.close();
        }catch(IOException e){
            //skjer ikke 
        }
    }

    private Boolean openFile(){
        try{
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            System.out.println("file exist");
            scanner.close();
            return true;
        }catch(FileNotFoundException e){
            System.out.println("File dosent exist");
            return false;
        }
    }

    
    private void createFile(){
        String text = "Highscores!\n";
        try{
            File file = new File(filename);
            FileWriter writer = new FileWriter(file);
            writer.write(text);
            writer.close();
            System.out.println("File created");
        }catch(IOException e){
            System.out.println("File already exist");
        }
    }

    private void loadHighScores(){
        File file = new File(filename);
        try{
            Scanner reader = new Scanner(file);
            while(reader.hasNextLine()){
                highscores.add(reader.nextLine());

            }
            reader.close();
        }catch(FileNotFoundException e){
            //Skal ikke skje
        }
    }
    

    private void updateHigscoreList(String name, Integer score){
        loadHighScores();
        boolean added = false;
        for(int i=1;i<highscores.size();i++){
            String[] oldHighScore = highscores.get(i).split(":");
            if(Integer.parseInt(oldHighScore[2])<score && added==false){
                Integer place = i;
                String newScore = place+":"+name+":"+score; 
                highscores.add(i, newScore);
                added=true;
            }else if(added){
                String[] highscore = highscores.get(i).split(":");
                highscores.remove(highscores.get(i));
                Integer place = i;
                highscores.add(i, place+":"+highscore[1]+":"+highscore[2]);
            }
        }
        if(!(added)){
            Integer place = highscores.size();
            String newScore = place+":"+name+":"+score; 
            highscores.add(newScore);
            added=true;
        }
    }
    public static void main(String[] args) {
        HighscoreHandler h = new HighscoreHandler();
        h.loadHighScores();
        System.out.print(h.highscores);
        h.saveScore("Mathias",1);
        h.loadHighScores();
        System.out.print(h.highscores);
    }


}
