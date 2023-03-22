package com.example.attackontitan;

public class AOTCharacter {
    private String name;
    private int height, weight, strength, agility, intelligence, coordination, leadership;

    public AOTCharacter(String name, int height, int weight, int strength, int agility, int intelligence, int coordination, int leadership) {
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.strength = strength;
        this.agility = agility;
        this.intelligence = intelligence;
        this.coordination = coordination;
        this.leadership = leadership;
    }

    public String toString() {
        return "Name: " + name +
                "\nHeight: " + height + "cm" +
                "\nWeight: " + weight + "kg" +
                "\nStrength: " + strength +
                "\nAgility: " + agility +
                "\nIntelligence: "  + intelligence +
                "\nCoordination: " + coordination +
                "\nLeadership: " + leadership + "\n";
    }

    public String getName() {
        return name;
    }

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

    public int getStrength() {
        return strength;
    }

    public int getAgility() {
        return agility;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getCoordination() {
        return coordination;
    }

    public int getLeadership() {
        return leadership;
    }
}
