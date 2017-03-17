import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/**
 * Created by Sonya on 24.10.16.
 */
public class Test {

    public static void main(String[] args) {


        SimpleDateFormat date = new SimpleDateFormat();
        String line = "2016-10-18T12:00 - 2016-10-18T14:00";
        ParsePosition p = new ParsePosition(0);
        Date d = date.parse(line, p);
        System.out.println(d);

    }

}
