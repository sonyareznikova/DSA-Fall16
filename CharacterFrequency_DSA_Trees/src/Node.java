import java.util.AbstractMap;

/**
 * Created by Sonya on 18.10.16.
 */
public class Node<K,V> {

    private AbstractMap.SimpleEntry<K,V> entry;
    private Node<K,V> left, right, parent;
    private int balance;

    public Node(AbstractMap.SimpleEntry<K,V> e, Node<K,V> l, Node<K,V> r, Node<K,V> p, int b) {
        this.entry = e;
        this.left = l;
        this.right = r;
        this.parent = p;
        this.balance = b;
    }

    //constructor for a Node with only key-value known
    public Node(K key, V value) {
        this.entry = new AbstractMap.SimpleEntry<K, V>(key, value);
        this.right = null;
        this.left = null;
        this.parent = null;
        this.balance = 0;
    }

    public boolean leaf() {
        if (this.left == null && this.right == null) {
            return true;
        }
        else return false;
    }

    public AbstractMap.SimpleEntry<K, V> getEntry() {
        return entry;
    }

    public void setEntry(AbstractMap.SimpleEntry<K, V> entry) {
        this.entry = entry;
    }

    public Node<K, V> getLeft() {
        return left;
    }

    public void setLeft(Node<K, V> left) {
        this.left = left;
    }

    public Node<K, V> getRight() {
        return right;
    }

    public void setRight(Node<K, V> right) {
        this.right = right;
    }

    public Node<K, V> getParent() {
        return parent;
    }

    public void setParent(Node<K, V> parent) {
        this.parent = parent;
    }
    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
