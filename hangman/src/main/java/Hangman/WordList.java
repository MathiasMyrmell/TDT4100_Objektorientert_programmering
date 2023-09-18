package Hangman;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WordList{

    
    public List<String> currentWords = new ArrayList<>();
    private String fileName = "minegenkode\\src\\main\\java\\Hangman\\files\\words.txt";
    
    public WordList(){
        readFromFile(fileName);
    }

    public List<String> getNewWordList(){
        return currentWords;
    }

    public void addNewWord(String word){
        writeToFile(fileName, word);
    }


    public void readFromFile(String filename){
        try{
            File file = new File(filename);
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()){
                String word = myReader.nextLine();
                currentWords.add(word);
            }
            myReader.close();
        }catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


    public void writeToFile(String filename, String word){
        readFromFile(filename);
        try{
            FileWriter myWriter = new FileWriter(filename);
            currentWords.add(word);
            for(String s:currentWords){
                myWriter.write(s+"\n");
            }
            myWriter.close();
        }
        catch(IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
    }

    public static void main(String[] args) {
        WordList w = new WordList();
        w.writeToFile("C:\\Users\\mathi\\TDT4100\\students\\minegenkode\\src\\main\\java\\Hangman\\words.txt", "Haaaalo");
        //w.readFromFile("C:\\Users\\mathi\\TDT4100\\students\\minegenkode\\src\\main\\java\\Hangman\\words.txt");

    }
}