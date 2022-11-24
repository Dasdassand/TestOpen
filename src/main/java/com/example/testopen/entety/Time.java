package com.example.testopen.timer;

public class Time {
    private int hour;
    private int minute;
    private int second;

    public Time(int minute){
        this.hour = (minute - minute % 60)/60;
        this.minute = minute - hour*60;
        this.second = 1;
    }

    public String getCurrentTime(){
        return hour + ":" + minute + ":" + second;
    }

    public void oneSecondPassed(){
        second--;
        if(second == 0 && minute > 0 || hour > 0){
            minute--;
            second = 60;
            if(minute == 0 && hour > 0){
                hour--;
                minute = 59;
            }else minute = 0;
        }
    }
}
