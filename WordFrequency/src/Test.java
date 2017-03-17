import sun.jvm.hotspot.ui.SourceCodePanel;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

/**
 * Created by Sonya on 24.09.16.
 */
public class Test {

    public static void main(String[] args) throws FileNotFoundException {


        MyHashTable<String, Integer> table = new MyHashTable<>();
        System.out.println(table.size());
        table.put("key", 8);
        table.put("key", 10);
        table.put("haha", 10);
        table.put("maxim", 22);
        table.put("lol", 1);
        table.put("innopolis", 1675809374);
        table.remove("innopolis");
        table.remove("maxim");
        table.remove("innopolis");
        table.remove("lol");
        Integer g = table.get("lol");
        System.out.println(g);
        System.out.println(table.size());







        /*ArrayList<String> data = new ArrayList<>(); //for lines

        Scanner input = new Scanner(new BufferedReader(new FileReader("input.txt")));
        while( input.hasNextLine() ) {
            data.add(input.nextLine());
        }
        for (int i = 0; i < data.size(); i++) {
            String newData = data.get(i).replace("'", " '");
            String[] line = newData.split("\\W+");
            for (int j = 0; j < line.length; j++) {

                System.out.println(line[j]);
            }
        }*/

        /*public void put(K k, V v) {

            if (k == null) {
                throw new NullPointerException();
            }

            int hash = k.hashCode() % SIZE;
            int jumps = 0;

            while ( table[hash] != null ) {
                int hashDouble = 39 - k.hashCode() % 39 + 1; //39 is a prime less than size
                hash = (hash + hashDouble) % SIZE;
                if (jumps == SIZE) {
                    return;
                }
                jumps++;
            }
            if (table[hash] == null || table[hash].getDefunct()) {
                n++;
            }
            boolean isDef = false;
            table[hash] = new HashEntry(k, v, isDef);

        }

    }
    public class HashEntry<K,V> {
        /* attributes */
        /*private K key;
        private V value;
        private boolean isDefunct;

        /* constructor */
        /*HashEntry(K k, V v, boolean def) {
            this.key = k;
            this.value = v;
            this.isDefunct = def;
        }*/

        /* getters */
        /*public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
        public boolean isDefunct() {
            return isDefunct;
        }
        public void makeDefunct() {
            this.isDefunct = true;
        }*/
    }

        /*public void remove(K k) {

            int hash = k.hashCode() % SIZE;
            int jumps = 0;

            while ((table[hash] != null && table[hash].getKey() != k) || table[hash] == null) {
                int hashDouble = 39 - k.hashCode() % 39 + 1;
                hash = (hash + hashDouble) % SIZE;
                if (jumps == SIZE) return;
                jumps++;
            }
            table[hash].makeDefunct();
            n--; */
        }


