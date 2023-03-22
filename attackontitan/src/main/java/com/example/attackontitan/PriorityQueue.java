package com.example.attackontitan;

import java.util.LinkedList;

public class PriorityQueue {
    private LinkedList<Titan> list = new LinkedList<>();

    public PriorityQueue() {
    }

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

    public Titan poll() {
        if (!list.isEmpty()) //Must check if the list is empty or not
            return list.removeFirst();
        return null;
    }

    public Titan getElement(int i) {
        if (i < 0 || i >= getSize())
            return null;
        return list.get(i);
    }

    public Titan peek() {
        if (!list.isEmpty())
            return list.getFirst();
        return null;
    }


    public int getSize() {
        return list.size();
    }

    public boolean contains(Titan e) {
        return list.contains(e);
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public String distanceMoved() {
        int distance = 0;
        for (int i = 0; i < list.size(); i++) {
            if (i == 0) {
                distance += list.get(0).getNum();
            } else {
                distance += Math.abs(list.get(i).getNum() - list.get(i - 1).getNum());
            }

        }
        return "Total distance moved : " + distance;
    }

    public String toString() {
        StringBuilder s = new StringBuilder("Sequence to be killed : ");

        for (int i = 0; i < list.size(); i++) {
            if (i == list.size() - 1) {
                s.append("Titan ").append(list.get(i).getNum());
            } else {
                s.append("Titan ").append(list.get(i).getNum()).append(" --> ");
            }
        }
        return s + "\n" + distanceMoved();
    }
}
