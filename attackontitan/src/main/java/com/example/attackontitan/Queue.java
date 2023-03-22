package com.example.attackontitan;

import java.util.LinkedList;

/** 2.3 Upper Part (Killing Priority) */
public class Queue{
    protected LinkedList<Titan> list = new LinkedList<>();
    //Variable to store Soldier distance moved
    private int distance;

    //Default constructor
    public Queue(){
    }

    /** Add an element to the end of the list */
    public void enqueue(Titan e){
        list.addLast(e);
    }

    /** Remove the first element and
     *  return the object that is contained in the removed node. */
    public Titan dequeue(){
        if(!isEmpty()){
            return list.removeFirst();
        }
        return null;

    }

    /** Return the element at the specified index */
    public Titan getElement(int i){
        if(!isEmpty()){
            return list.get(i);
        }
        return null;
    }

    /** Return the element at the first index */
    public Titan peek(){
        if(!isEmpty()){
            return list.getFirst();
        }
        return null;
    }

    /** Return the size of the list */
    public int getSize(){
        return list.size();
    }

    /** Return true if this list contains the element e */
    public boolean contains(Titan e){
        return list.contains(e);
    }

    /** Return true if this list is empty */
    public boolean isEmpty(){
        return getSize()==0;
    }

    /** Return the soldier's total distance moved
     * First index = 0 (Starting point)
     * Calculate the distance moved in terms of
     * the difference in index position of the titans in the input.*/
    public int distanceMoved() {
        distance=0;
        for (int i = 0; i < list.size(); i++) {
            if (i == 0) {
                distance += list.get(0).getNum();
            } else {
                distance += Math.abs(list.get(i).getNum() - list.get(i - 1).getNum());
            }
        }
        return  distance;
    }

    /** Return the sequence to be killed */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("");

        for (int i = 0; i < list.size(); i++) {
            //move to next line
            if(i%6==0){
                s.append("\n");
            }
            if (i == list.size() - 1) {
                s.append("Titan ").append(list.get(i).getNum());
            } else {
                s.append("Titan ").append(list.get(i).getNum()).append(" --> ");
            }
        }
        return s + "\n" ;

    }
}