package com.example.testopen.entety;

public class Time {
    private int minute;
    private int second;

    public Time(int minute){
        this.minute = minute ;
        this.second = 1;
    }

    public String getCurrentTime(){
        return  minute + ":" + second;
    }

    public void oneSecondPassed(){
        second--;
        if (second == 0 && minute > 0){
            second = 60;
            minute--;
        }

    }
}
