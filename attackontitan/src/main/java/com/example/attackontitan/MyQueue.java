package com.example.attackontitan;

public class MyQueue<E> {
    private java.util.LinkedList<E> list;

    public MyQueue(E[] e){
        this.list =new java.util.LinkedList<>();
        for(int i=0;i<e.length;i++){
            list.add(e[i]);
        }
    }

    public MyQueue() {
        this.list =new java.util.LinkedList<>();

    }

    public void enqueue(E e){
        list.addLast(e);
    }

    public E dequeue(){
        if(!isEmpty()){
            return list.removeFirst();
        }
        return null;

    }

    public E getElement(int i){
        if(!isEmpty()){
            return list.get(i);
        }
        return null;
    }

    public E peek(){
        if(!isEmpty()){
            return list.getFirst();
        }
        return null;
    }

    public int getSize(){
        return list.size();
    }

    public boolean contains(E e){
        return list.contains(e);
    }

    public boolean isEmpty(){
        return getSize()==0;
    }

    @Override
    public String toString() {
        return list.toString() ;
    }
}
