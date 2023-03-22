package com.example.attackontitan;

import java.util.Random;

/** 2.3 Upper Part (Killing Priority) */
public class Titan implements Comparable<Titan> {
    Random r = new Random();
    private int height,legs,speed;
    private int risk=0;
    private  int random , randomType;
    private String pattern,climb;
    //Store the total number of  Titan
    private static int titanInt = 0;
    //Store the position index of Titan
    private int num;

    public Titan() {
        titanInt++;
        this.num = titanInt ;
        generateType();
    }

    /** Random Generate Height */
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

    /** Random Generate Legs */
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

    /** Random Generate Speed */
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


    /** Random Generate Pattern */
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

    /** Random Generate Climb */
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

    /** Getter and Setter method */
    public static void setTitanInt(int titanInt) {
        Titan.titanInt = titanInt;
    }

    public static int getTitanInt() {
        return titanInt;
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

    /** Random Generate Type of Titan */
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

    /** Display the random generated Titan */
    public String display() {
        if(titanInt >= 16){
            titanInt = 0;
            this.num=0;
        }
        String str = "";
        if (this.randomType == 0) {
            str = "Titan" + getNum() + ": Abnormal Titan Risk=" + risk;

        } else if (this.randomType == 1) {
            str = "Titan" + getNum() + ": Abnormal Titan Risk=" + risk;

        } else {
            str += String.format("Titan" + getNum() + ": Normal Titan (%dm, %d legs, %dms, %s, %s) Risk=%d", height, legs, speed, pattern, climb, risk);

        }
        return str;
    }

    /** Compare the risk of Titan */
    @Override
    public int compareTo(Titan o) {
        return Integer.compare(this.getRisk(), o.getRisk());
    }
}
