package com.example.attackontitan;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/** 2.5 Basic Feature */
public class MyHashMap<K, V> {

    /** Define the maximum hash-table size. 1 << 30 is same as 2^30 */
    private static int MAXIMUM_CAPACITY = 1 << 30;

    /** Current hash-table capacity. Capacity is a power of 2 */
    private int capacity;

    /** The number of entries in the map */
    private int size = 0;

    /** Hash table is an array with each cell being an arraylist */
    ArrayList<Entry<K,V>>[] table;

    /** Construct a map with the specified initial capacity and load factor */
    public MyHashMap(int capacity) {
        if (capacity > MAXIMUM_CAPACITY)
            this.capacity = MAXIMUM_CAPACITY;
        table = new ArrayList[capacity];
    }

    /** Return true if the specified key is in the map */
    public boolean containsKey(K key) {
        return get(key) != null;
    }


    /** Return the value that matches the specified key */
    public V get(K key) {
        int bucketIndex = hash(key.hashCode());
        if (table[bucketIndex] == null)
            return null;
        ArrayList<Entry<K, V>> bucket = table[bucketIndex];
        for (Entry<K, V> entry : bucket)
            if (entry.getKey().equals(key))
                return entry.getValue();
            return null;
    }

     /** Add an entry (elementKey, elementValue) into the map */
    public V put(K elementKey, V elementValue) {
        if (get(elementKey) != null) {
            int bucketIndex = hash(elementKey.hashCode());
            ArrayList<Entry<K, V>> bucket = table[bucketIndex];
            for (Entry<K, V> entry : bucket)
                if (entry.getKey().equals(elementKey)) {
                    // Replace old elementValue with new elementValue
                    V oldValue = entry.getValue();
                    // Replace old elementValue for the elementKey
                    entry.value = elementValue;
                    return oldValue;
                }
        }

        int bucketIndex = hash(elementKey.hashCode());

        // Create a linked list for the bucket if not already created
        if (table[bucketIndex] == null) {
            table[bucketIndex] = new ArrayList<>();
        }

        // Add a new entry (elementKey, elementValue) to hashTable[index]
        table[bucketIndex].add(new Entry<K, V>(elementKey, elementValue));

        // Increase size
        size++;
        return elementValue;
    }

    /** Return the number of entries in this map */
    public int size() {
        return size;
    }

    /** Hash function */
    private int hash(int hashCode) {
        return supplementalHash(hashCode) & (capacity - 1);
    }

    /** Ensure the hashing is evenly distributed */
    private static int supplementalHash(int h) {
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }


    class Entry<K, V> {
        K key;
        V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }
}
