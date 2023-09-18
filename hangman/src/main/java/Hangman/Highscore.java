package Hangman;

public class Highscore {
    
    private Integer highscore;
    private Clock clock;
    
    public Highscore(){
        this.clock=new Clock();
    }
    
    public void setEndTime(){
        this.clock.setEndTime();
    }
    
    public Integer getHighscore(){
        double timeDifference = this.clock.getTimeDifference();
        calculateHighscore(timeDifference);
        if(this.highscore<0){
            return 0;
        }else{
            return this.highscore;
        }
    }
    

    private void calculateHighscore(double timeDifference){
        Integer time = 20000;
        if(timeDifference<=0){
            this.highscore=0;
        }else{
            this.highscore=(int)(time-timeDifference*1000);
        }
    }

}
