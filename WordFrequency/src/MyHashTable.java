import java.util.ArrayList;

/**
 * Created by Sonya on 23.09.16.
 */

public class MyHashTable<K, V> {

    private final static int CAPACITY = 1009;
    private int size = 0; //number of HashEntries
    private HashEntry<K,V>[] table;

    public class HashEntry<K,V> {

        private K key;
        private V value;
        private boolean isDefunct = false;

        HashEntry(K k, V v) {
            this.key = k;
            this.value = v;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
        public boolean isDefunct() {
            return isDefunct;
        }
        public void makeDefunct() {
            this.key = null;
            this.value = null;
            isDefunct = !isDefunct;
        }
    }

    /* constructors */

    public MyHashTable() {
        this(CAPACITY);
    }

    public MyHashTable(int s) {
        table = new HashEntry[s];
        for (int i = 0; i < s; i++) {
            table[i] = null;
        }
    }
    /*returns number of elements*/
    public int size() { return size; }

    /* inserting values into the hash tables */
    public void put(K key, V value) {

        if (key == null) {
            throw new NullPointerException();
        }

        int hash = Math.abs(key.hashCode()) % CAPACITY;
        int jumps = 0;

        while ( true ) {
            if (jumps == CAPACITY)
                return;

            int hashDouble = 39 - Math.abs(key.hashCode()) % 39 + 1; //39 is a prime less than size
            int index = (hash + jumps * hashDouble) % CAPACITY;

            if (table[index] == null || table[index].isDefunct || table[index].getKey().equals(key)) {

                if (table[index] == null || table[index].isDefunct)
                    size++;
                table[index] = new HashEntry<K, V>(key, value);
                return;
            }
            jumps++;
        }
    }

    /* getting elements from the hashtable*/
    public V get(K key) {

        int hash = Math.abs(key.hashCode()) % CAPACITY;
        int jumps = 0; //number of jumps over the array

        while ( true ) {
            if (jumps == CAPACITY)
                return null;
            int hashDouble = 39 - Math.abs(key.hashCode()) % 39 + 1; //39 is a prime less than size
            int index = (hash + jumps * hashDouble) % CAPACITY;

            if (table[index] == null)
                return null;
            else if (table[index].getKey() != null && table[index].getKey().equals(key))
                return table[index].getValue();

            jumps++;
        }
    }

    /*removing values from the hashtable*/
    public void remove(K key) {

        int hash = Math.abs(key.hashCode()) % CAPACITY;
        int jumps = 0;

        while ( true ) {
            if (jumps == CAPACITY)
                return;

            int hashDouble = 39 - Math.abs(key.hashCode()) % 39 + 1;
            int index = (hash + jumps * hashDouble) % CAPACITY;

            if (table[index] != null && !table[index].isDefunct && table[index].getKey().equals(key)) {
                table[index].makeDefunct();
                size--;
                return;
            }
            else if (table[index] == null) {
                return;
            }
                jumps++;
        }
    }

    /*to iterate over the hashtable*/
    public Iterable<HashEntry<K, V>> entrySet() {
        ArrayList<HashEntry<K, V>> buffer = new ArrayList<>();
        for (int h = 0; h < CAPACITY; h++) {
            if (table[h] != null && !table[h].isDefunct) {
                    buffer.add(table[h]);
            }
        }
        return buffer;
    }
}
