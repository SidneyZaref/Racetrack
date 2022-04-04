package edu.psu.ist311;

public class Runner implements Comparable<Runner>{

    public int timeInMin;
    public String initials;

    public Runner(int mins, String initials){
        this.timeInMin = mins;
        this.initials = initials;
    }
    @Override
    public int compareTo(Runner o) {
        return this.timeInMin - o.timeInMin;
    }

    public int getTimeInMinutes() {
        return timeInMin;
    }

    @Override
    public String toString() {
        return "(" + initials + ":" + timeInMin + ")";
    }
}
