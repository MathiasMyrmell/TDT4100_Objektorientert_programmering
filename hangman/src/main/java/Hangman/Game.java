package Hangman;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class Game{


    private List<String> words = new ArrayList<String>();
    private List<Character> guessedLetters = new ArrayList<Character>();
    private List<Character> progress = new ArrayList<Character>();
    private List<Character> legalUserNameCharacters = Arrays.asList('0','1','2','3','4','5','6','7','8','9','a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z');

    private String word;
    private Integer attempts=0;
    private Highscore highscore;
    private Integer score = 0;
    private String userName;
    private String guessInfoText;

    public Game(String userName){
        checkLegalUserName(userName);
        if(userName.isEmpty()){
            this.userName="NoName";
        }else{
            this.userName = userName;
        }
        initializeNewGame();
    }

    private void checkLegalUserName(String userName){
        char[] obj = userName.toCharArray();
        for(char c:obj){
            if(!(legalUserNameCharacters.contains(Character.toLowerCase(c)))){
                throw new IllegalArgumentException("Illegal username. Only letters and numbers are allowed.");
            }
        }
    }

    public void makeGuess(String text){
        Guess guess;
        try{
            if(text.length()<1){
                throw new IllegalArgumentException("You cant guess 'blank'");
            }else if(text.length()>1){
                guess = new GuessWord(text, this);
            }else{
                guess = new GuessLetter(text.charAt(0), this);
            }
            this.guessInfoText = guess.result();
        }catch (IllegalArgumentException e) {
            this.guessInfoText = "You cant guess 'blank'";
        }
        
    }

    public String getInfoText(){
        return this.guessInfoText;
    }

    public void initializeNewGame(){
        loadWords();
        this.highscore=new Highscore();
        setWord(getRandomWord());
        System.out.println(word);
    }

    public void setWord(String word){
        this.word = word;
        progress.clear();
        for(int i=0;i<word.length();i++){
            progress.add('_');
        }
    }

    public String getUserName(){
        return this.userName;
    }

    public void loadWords(){
        WordList w = new WordList();
        words=w.getNewWordList();
    }

    public String getRandomWord(){
        Random rand = new Random();
        System.out.println(words.size());
        int upperbound = words.size()-1;
        System.out.print(upperbound);
        int random = rand.nextInt(upperbound);
        return words.get(random);
    }

    public void saveScore(Integer score){
        HighscoreHandler hh = new HighscoreHandler();
        hh.saveScore(this.userName, this.score);
    }

    public void setEndTime(){
        this.highscore.setEndTime();
    }
    
    public boolean checkFinished(){
        if(progress.contains('_')){
            return false;
        }else{
            this.score = this.highscore.getHighscore();
            saveScore(this.score);
            return true;
        }
    }

    public boolean checkLost(){
        if(attempts==7){
            return true;
        }else{
            return false;
        }
    }

    public String displayProgress(){
        String string="";
        for(Character letter:progress){
            string+=letter+" ";
        }
        return string;
    }

    public String displayGuessedWords(){
        String string="";
        for(Character c:guessedLetters){
            string+=c+" ";
        }
        return string;
    }

    public int getAttempt(){
        return this.attempts;
    }

    public String getWord(){
        return this.word;
    }

    public Integer getScore(){
        return this.score;
    }

    public void addGuessedLetter(char c){
        char [] sortedList = new char[guessedLetters.size()+1];
        for(int i=0; i< guessedLetters.size();i++){
            sortedList[i] = guessedLetters.get(i);
        }
        sortedList[sortedList.length-1] = c;
        Arrays.sort(sortedList);
        guessedLetters.clear();
        for(char i : sortedList){
            guessedLetters.add(i);
        }
    }

    public List<Character> getGuessedLetters(){
        return this.guessedLetters;
    }

    public void updateAttempts(){
        this.attempts+=1;
    }


    public void updateProgress(char c){
        for(int i=0;i<progress.size();i++){
            if(word.charAt(i)==c){
                progress.remove(i);
                progress.add(i, c);
            }
        }
    }
}