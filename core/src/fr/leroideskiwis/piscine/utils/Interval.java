package fr.leroideskiwis.piscine.utils;

public class Interval {

    private final int max;
    private final int min;

    public Interval(int min, int max){
        this.min = min;
        this.max = max;
    }

    public boolean isBetween(int i){
        return i >= min && i <= max;
    }

}
