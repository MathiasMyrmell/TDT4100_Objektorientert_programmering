package Hangman;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;   

public class Clock {
    private DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm:ss:ms");
    private LocalDateTime startTime;
    private LocalDateTime endTime;


    public Clock(){
        this.startTime=LocalDateTime.now();
    }

    public void setEndTime(){
        this.endTime=LocalDateTime.now();
    }

    public double getTimeDifference(){
        double startTime = calculateTime(this.getStartTime());
        double endTime = calculateTime(this.getEndTime());
        return endTime-startTime;
    }

    private String getStartTime(){
        return format.format(this.startTime);
    }

    private String getEndTime(){
        return format.format(this.endTime);
    }

    private double calculateTime(String time){
        double hourToSeconds = Double.parseDouble(time.split(":")[0])*360;
        double minutesToSeconds = Double.parseDouble(time.split(":")[1])*60;
        double secounds = Double.parseDouble(time.split(":")[2]);
        double milliseconds = Double.parseDouble("0."+time.split(":")[3]);
        double totalSeconds = hourToSeconds+minutesToSeconds+secounds+milliseconds;
        return totalSeconds;
    }



    public static void main(String[] args) {
        Clock c = new Clock();
        System.out.println(c.getStartTime());
        c.setEndTime();
        System.out.println(c.getEndTime());
        System.out.println(c.getTimeDifference());
        System.out.println(c.calculateTime(c.getEndTime()));
    }

}
