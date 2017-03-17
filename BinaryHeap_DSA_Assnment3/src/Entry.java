import java.util.Date;

/**
 * Created by Sonya on 24.10.16.
 */
public class Entry<K extends Comparable<K>> implements Comparable<Entry<K>>{

    private K contribution;
    private Date date;
    private String name;


    public Entry (K c, Date d, String n) {
        this.contribution = c;
        this.date = d;
        this.name = n;
    }

    public Entry() {
        this.contribution = null;
        this.date = null;
        this.name = null;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public K getContribution() {
        return contribution;
    }

    public void setContribution(K contribution) {
        this.contribution = contribution;
    }

    @Override
    public int compareTo(Entry anotherEntry) {
        int dif = contribution.compareTo((K) anotherEntry.contribution);
        if (dif != 0) return dif;
        else return 0;
    }
}
