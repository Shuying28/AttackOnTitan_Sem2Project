package com.example.attackontitan;

import java.util.LinkedList;

/** 2.3 Upper Part (Killing Priority) */
public class PriorityQueue extends Queue{
    //Variable to store Soldier distance moved
    private int distance;

    //Default constructor
    public PriorityQueue() {
        //list.clear();
    }

    /** Add an element to the list and rearrange them according to their priority
     * higher danger risk --> higher priority
     * lower danger risk --> lower priority */
    public void offer(Titan e) {
        boolean added = false;
        for (int i = 0; i < list.size(); i++) {
            if (e.compareTo(list.get(i)) > 0 || e.compareTo(list.get(i)) == 0) {
                list.add(i, e);
                added = true;
                break;
            }
        }
        if (!added) {
            list.addLast(e);
        }
    }

    /** Remove the higher priority element and
     *  return the object that is contained in the removed node. */
    public Titan poll() {
        if (!list.isEmpty()) //Must check if the list is empty or not
            return list.removeFirst();
        return null;
    }


}
