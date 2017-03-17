import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

/**
 * Created by Sonya on 24.10.16.
 */
public class TopContributor {

    public static void main(String[] args) throws IOException, ParseException {

        //read all lines into one arraylist
        ArrayList<String> lines = new ArrayList<>();
        Scanner input = new Scanner(new BufferedReader(new FileReader("input.txt")));
        while (input.hasNextLine()) {
            lines.add(input.nextLine());
        }

        //arraylist for all max contributors
        ArrayList<Entry<Integer>> forOutput = new ArrayList<>();

        //heap for all contributors
        BinaryHeap<Entry<Integer>> contributors = new BinaryHeap<>();

        //array of the first line with the boss' times
        String[] bossElements = lines.get(0).split("\\s+");
        //parsing boss' times
        String dateFormat = "yyyy-MM-dd'T'HH:mm";
        SimpleDateFormat boss1 = new SimpleDateFormat(dateFormat);
        SimpleDateFormat boss2 = new SimpleDateFormat(dateFormat);
        Date bossStart = boss1.parse(bossElements[1]);
        Date bossFinish = boss2.parse(bossElements[3]);
        Date bossTime = bossStart;


        for (int i = 1; i < lines.size(); i++) {

                //parsing names, contributions and date-times
                String[] theGenerous = lines.get(i).split("\\s+");

                String firstName = theGenerous[0];
                String lastName = theGenerous[1];
                lastName = lastName.substring(0, lastName.length() - 1);    //getting rid of a colon
                String name = firstName + " " + lastName;

                int money = Integer.parseInt(theGenerous[2]);

                SimpleDateFormat t = new SimpleDateFormat(dateFormat);
                Date time = t.parse(theGenerous[4]);

                //if the boss stops looking but transactions are still happening, stop looking
                if (time.compareTo(bossFinish) > 0) {
                    break;
                }

                //creating an entry with the current contributor data
                Entry<Integer> ent = new Entry<Integer>(money, time, name);

                while (time.compareTo(bossTime) > 0 && time.compareTo(bossFinish) <= 0) {
                    Entry<Integer> max = contributors.peek();
                    Entry<Integer> maxForOutput = new Entry<Integer>(max.getContribution(), bossTime, max.getName());
                    forOutput.add(maxForOutput);

                    //incrementing the value of bossStart
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(bossTime);
                    calendar.add(Calendar.HOUR_OF_DAY, 1);
                    bossTime = calendar.getTime();
                }
                //adding current contributor to the heap
                contributors.add(ent);

        }

        //to make sure that if there are no new transaction but the boss is still checking - that we include the last ones
        while (bossTime.compareTo(bossFinish) <= 0 ) {
            Entry<Integer> max = contributors.peek();
            Entry<Integer> maxForOutput = new Entry<Integer>(max.getContribution(), bossTime, max.getName());
            forOutput.add(maxForOutput);

            //incrementing the value of bossStart
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(bossTime);
            calendar.add(Calendar.HOUR_OF_DAY, 1);
            bossTime = calendar.getTime();
        }


        FileWriter writer = new FileWriter("output.txt");

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < forOutput.size(); i++) {
            Entry<Integer> e = forOutput.get(i);
            SimpleDateFormat outDate = new SimpleDateFormat(dateFormat);
            String date = outDate.format(e.getDate());
            String outputLine = date + ": " + e.getName() + "\n";
            result.append(outputLine);
        }
        writer.write(result.toString());
        writer.close();
    }
}
