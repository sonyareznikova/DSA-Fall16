import java.io.*;
import java.util.*;

/**
 * Created by Sonya on 23.09.16.
 */
public class WordCount {

    public static void main(String[] args) throws IOException {

        ArrayList<String> data = new ArrayList<>(); //for lines
        String[] doNotCount = {"a", "the", "in", "at", "to", "on", "not", "for", "'s", "'d", "'re", "is", "are", "am", "has", "i", "we", "you"};

        Scanner input = new Scanner(new BufferedReader(new FileReader("input.txt")));
        while (input.hasNextLine()) {
            data.add(input.nextLine());
        }
        MyHashTable<String, Integer> table = new MyHashTable<>(); //merged hash table

        /* iterating over arraylist of lines - splitting into words */
        for (int i = 0; i < data.size(); i++) {

            String noCapitals = data.get(i).toLowerCase();
            String noApData = noCapitals.replace("'", " '"); //no apostrophe data
            String[] splitData = noApData.split("[^a-z']+");

            MyHashTable<String, Integer> line = new MyHashTable<>();

            /* iterating over each line - adding words to the each-line-hashtables */
            for (int j = 0; j < splitData.length; j++) {

                if (line.get(splitData[j]) == null) {
                    line.put(splitData[j], 1);
                } else {
                    line.put(splitData[j], line.get(splitData[j]) + 1); // word frequency for a line
                }
            }

            /* for each key value pair in the line put it into the final hashtable */
            for (MyHashTable.HashEntry entry : line.entrySet()) {

                String key = (String) entry.getKey();
                int value = (int) entry.getValue();

                if (table.get(key) != null) {
                    value = (int) table.get(key) + value;
                }
                table.put(key, value);
            }
        }

        /* check for words(keys) that should be omitted */
        for(String s: doNotCount) {

            if (table.get(s) != null)
                table.remove(s);
        }

        /* find maximum values */
        String maxWord = "";
        int maxCount = 0;
        for (MyHashTable.HashEntry entry : table.entrySet()) {

            String key = (String) entry.getKey();
            int value = (int) entry.getValue();
            if (value > maxCount) {
                maxWord = key;
                maxCount = value;
            }
        }

        FileWriter writer = new FileWriter("output.txt");
        writer.write(maxWord + " " + maxCount);

        writer.close();
        input.close();
    }
}