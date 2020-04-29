package com.example.pigdice;

import java.util.Random;

public class Dice {
    public static final int HOLD = 3;
    private static final int MAX = 6;
    private static final int MIN = 1;
    public static final int ROLL = 1;
    public static final int SHAKE = 2;
    private int die1;
    private int die2;
    private Random rand = new Random();

    public int sumNumbers(int i, int i2) {
        return i + i2;
    }

    public Dice(int i, int i2) {
        this.die1 = i;
        this.die2 = i2;
    }

    public int rollDie1() {
        this.die1 = this.rand.nextInt(6) + 1;
        return this.die1;
    }

    public int rollDie2() {
        this.die2 = this.rand.nextInt(6) + 1;
        return this.die2;
    }

    public int getDie1() {
        return this.die1;
    }

    public void setDie1(int i) {
        this.die1 = i;
    }

    public int getDie2() {
        return this.die2;
    }

    public void setDie2(int i) {
        this.die2 = i;
    }
}
