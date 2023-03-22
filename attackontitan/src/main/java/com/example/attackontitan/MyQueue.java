package com.example.attackontitan;

/** 2.3 Lower Part (Hamiltonian cycle) */
public class MyQueue<E> {
    private java.util.LinkedList<E> list =new java.util.LinkedList<>();

    //Default constructor
    public MyQueue() {
    }

    /** Add an element to the end of the list */
    public void enqueue(E e){
        list.addLast(e);
    }

    /** Remove the first element and
     *  return the object that is contained in the removed node. */
    public E dequeue(){
        if(!isEmpty()){
            return list.removeFirst();
        }
        return null;

    }

    /** Return the element at the specified index */
    public E getElement(int i){
        if(!isEmpty()){
            return list.get(i);
        }
        return null;
    }

    /** Return the element at the first index */
    public E peek(){
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
    public boolean contains(E e){
        return list.contains(e);
    }

    /** Return true if this list is empty */
    public boolean isEmpty(){
        return getSize()==0;
    }

    /** Display the list */
    @Override
    public String toString() {
        return list.toString() ;
    }
}
