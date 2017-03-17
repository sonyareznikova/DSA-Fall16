import java.io.*;
import java.util.Scanner;

/**
 * Created by Sonya on 18.10.16.
 */
public class FrequencyCalculator {

    public static void main(String[] args) throws IOException {

        StringBuilder allChars = new StringBuilder();   //for all characters
        Scanner input = new Scanner(new BufferedReader(new FileReader("input.txt")));
        while (input.hasNext()) {
            allChars.append(input.next());
        }

        StringBuilder charsToRemove = new StringBuilder(":;<=>?@[\\]^_`");

        AVLTree<Character, Integer> tree = new AVLTree<>();

        //inserting all elements in a tree
        for (int i = 0; i < allChars.length(); i++) {
            //lowercasing
            char ch = allChars.charAt(i);
            allChars.setCharAt(i, Character.toLowerCase(ch));

            Character current = allChars.charAt(i);

            Node<Character, Integer> n = tree.search(current);
            if (n != null && current.compareTo(n.getEntry().getKey()) == 0) {
                int count = n.getEntry().getValue();
                count++;
                tree.insert(current, count);
            }
            else tree.insert(current, 1);
        }

        //deleting unnecessary elements
        for (int i = 0; i < charsToRemove.length(); i++) {
            tree.delete(charsToRemove.charAt(i));
        }
        FileWriter writer = new FileWriter("output.txt");

        StringBuilder result = new StringBuilder();
        //printing
        tree.traverse((key, value) -> {
            result.append(key + ":" + value + " ");}, '0', 'z');

        String res = result.toString().trim();
        writer.write(res);

        writer.close();
    }
}
