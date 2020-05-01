package com.example.pigdice;

import java.io.Serializable;

public class Level implements Serializable {
    int compScore;
    int holdValue;
    int levelScore;
    int reRollPercent;

    public int getHoldValue() {
        return this.holdValue;
    }

    public void setHoldValue(int i) {
        this.holdValue = i;
    }

    public int getCompScore() {
        return this.compScore;
    }

    public void setCompScore(int i) {
        this.compScore = i;
    }

    public int getLevelScore() {
        return this.levelScore;
    }

    public void setLevelScore(int i) {
        this.levelScore = i;
    }

    public int getRerollPercent() {
        return this.reRollPercent;
    }

    public void setRerollPercent(int i) {
        this.reRollPercent = i;
    }

    public Level(int i, int i2, int i3, int i4) {
        this.holdValue = i;
        this.compScore = i2;
        this.levelScore = i3;
        this.reRollPercent = i4;
    }
}
