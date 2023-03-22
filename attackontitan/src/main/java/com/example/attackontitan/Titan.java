package com.example.attackontitan;

import java.util.Random;

public class Titan implements Comparable<Titan> {
    Random r = new Random();

    private int height,legs,speed;
    private int risk=0;
    private  int random , randomType;
    private String pattern,climb;
    private static int titanNo = 0;
    private int num;

    public Titan() {
        titanNo++;
        this.num = titanNo ;
        generateType();
    }

    public void randomHeight() {
        random = r.nextInt(30) + 1;
        this.height = random;
        if (height > 20) {
            this.risk += 3;
        } else if (height > 10) {
            this.risk += 2;
        } else {
            this.risk += 1;
        }
    }

    public void randomLegs() {
        random = r.nextInt(5);  //0-4
        while (random % 2 == 1) {
            random = r.nextInt(5);
        }
        this.legs = random;
        if (legs == 4) {
            this.risk += 3;
        } else if (legs == 2) {
            this.risk += 2;
        } else {
            this.risk += 1;
        }
    }

    public void randomSpeed() {
        random = r.nextInt(30) + 1;
        this.speed = random;
        if (speed > 20) {
            this.risk += 3;
        } else if (speed > 10) {
            this.risk += 2;
        } else {
            this.risk += 1;
        }
    }


    public void randomPattern() {
        random = r.nextInt(3);
        switch (random) {
            case 0 -> {
                this.pattern = "Not repeated pattern";
                this.risk += 3;
            }
            case 1 -> {
                this.pattern = "Repeated pattern";
                this.risk += 2;
            }
            case 2 -> {
                this.pattern = "Normal pattern";
                this.risk += 1;
            }
        }
    }

    public void randomClimb() {
        random = r.nextInt(2);
        switch (random) {
            case 0 -> {
                this.climb = "Can climb";
                this.risk += 3;
            }
            case 1 -> {
                this.climb = "Cannot climb";
                this.risk += 1;
            }
        }
    }

    public static void setTitanNo(int titanNo) {
        Titan.titanNo = titanNo;
    }

    public int getNum() {
        return num;
    }

    public int getHeight() {
        return height;
    }

    public int getLegs() {
        return legs;
    }

    public int getSpeed() {
        return speed;
    }

    public int getRisk() {
        return this.risk;
    }

    public String getPattern() {
        return pattern;
    }

    public String getClimb() {
        return climb;
    }

    public void generateType() {
        this.randomType = r.nextInt(3);
        if (randomType == 0) {
            //Abnormal
            this.risk = 15;
        } else if (randomType == 1) {
            //Nine titan
            this.risk = 19;
        } else {
            //Normal
            randomHeight();
            randomLegs();
            randomSpeed();
            randomPattern();
            randomClimb();
        }
    }

    public String toString() {
        String str = "";
        if (this.randomType == 0) {
            str = "Titan" + this.num + ": Abnormal Titan Risk=" + risk;

        } else if (this.randomType == 1) {
            str = "Titan" + this.num + ": Abnormal Titan Risk=" + risk;

        } else {
            str += String.format("Titan" + this.num + ": Normal Titan (%dm, %d legs, %dms, %s, %s) Risk=%d", height, legs, speed, pattern, climb, risk);

        }
        return str;
    }

    @Override
    public int compareTo(Titan o) {
        return Integer.compare(this.getRisk(), o.getRisk());
    }
}
