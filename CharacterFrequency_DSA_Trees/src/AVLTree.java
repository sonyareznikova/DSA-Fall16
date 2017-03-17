import java.util.function.BiConsumer;

/**
 * Created by Sonya on 18.10.16.
 */
public class AVLTree<K extends Comparable<K>, V> {

    private Node<K,V> root;
    private int size;

    public AVLTree() {
        this.root = null;
        this.size = 0;
    }
    public AVLTree(Node<K,V> r) {
        this.root = r;
        this.size = 1;
    }

    public int size() {
        return this.size;
    }

    public int height(Node<K,V> n) {
        if (n == null) return -1;
        return 1 + Math.max(height(n.getLeft()), height(n.getRight()));
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public Node<K,V> getRoot() {
        return this.root;
    }

    //searching the tree
    public Node<K,V> search(K key) {
        return search(this.root, key);
    }

    public Node<K,V> search(Node<K,V> p, K key) {
        if (p == null) return null;

        if (p.leaf()) {
            return p;
        }
        else if (key.compareTo(p.getEntry().getKey()) == 0) {
            return p;
        }
        else if (key.compareTo(p.getEntry().getKey()) < 0) {
            if (p.getLeft() == null) return p;
            else return search(p.getLeft(), key);
        }
        else if (key.compareTo(p.getEntry().getKey()) > 0) {
            if (p.getRight() == null) return p;
            else return search(p.getRight(), key);
        }
        return null;
    }

    //inserting
    public void insert(K key, V value) {
        Node<K,V> newChild = new Node<K, V>(key, value);

        if (this.root == null) {
            this.root = newChild;
            size++;
            return;
        }

        Node<K,V> newParent = search(key);
        newChild.setParent(newParent);

        if (key.compareTo(newParent.getEntry().getKey()) > 0) {
            newParent.setRight(newChild);
            size++;
        }
        else if (key.compareTo(newParent.getEntry().getKey()) < 0) {
            newParent.setLeft(newChild);
            size++;
        }
        else if (key.compareTo(newParent.getEntry().getKey()) == 0) {
            newParent.getEntry().setValue(value);
        }
        rebalanceInsert(newChild);
    }

    //delete
    public V delete(K key) {
        Node<K,V> toBeDeleted = search(key);
        Node<K,V> r;    //to store the nodes depending on how many children a toBeDeleted node has

        //there's no such key
        if (key.compareTo(toBeDeleted.getEntry().getKey()) != 0) {
            return null;
        }

        Node<K,V> parent = toBeDeleted.getParent();
        V value = toBeDeleted.getEntry().getValue();

        if (toBeDeleted.leaf()) {
            if (key.compareTo(parent.getEntry().getKey()) < 0) {
                parent.setLeft(null);
                toBeDeleted.setParent(null);
                size--;
            }
            else if (key.compareTo(parent.getEntry().getKey()) > 0) {
                parent.setRight(null);
                toBeDeleted.setParent(null);
                size--;
            }
            rebalanceUntilRoot(parent);
            return value;
        }
        else if (toBeDeleted.getLeft() == null || toBeDeleted.getRight() == null) {
            if (parent == null) {
                this.root = null;
                toBeDeleted = null;
                size--;
                return value;
            }
            r = toBeDeleted;
        }
        else {
            r = successor(toBeDeleted);
            toBeDeleted.setEntry(r.getEntry());
            size--;
        }

        Node<K,V> p;  //for reconnecting

        if (r.getLeft() != null) p = r.getLeft();
        else p = r.getRight();

        if (p != null) p.setParent(r.getParent());

        if (r.getParent() == null) {
            this.root = p;
        }
        else {
            if (r == r.getParent().getLeft()) {
                r.getParent().setLeft(p);
            }
            else r.getParent().setRight(p);
        }

        rebalanceUntilRoot(parent);
        r = null;
        return value;
    }

    //restoring the height balance of the tree
    public void rebalanceInsert(Node<K,V> n) {
        Node<K,V> unbalancedNode = findUnbalanced(n);
        if (unbalancedNode == null) return;
        Node<K,V> firstTaller = findTallerChild(unbalancedNode);
        Node<K,V> secondTaller = findTallerChild(firstTaller);
        restructure(unbalancedNode, firstTaller, secondTaller);
    }

    public void rebalanceDelete(Node<K,V> n) {
        Node<K,V> unbalancedNode = findUnbalanced(n);
        if (unbalancedNode == null) return;
        Node<K,V> firstTaller = findTallerChild(unbalancedNode);
        Node<K,V> secondTaller = findTallerChild(firstTaller);

        //checking if both subtrees of the first greater subtree are of the same height
        if (secondTaller == null) {
            if (unbalancedNode.getLeft() == firstTaller) secondTaller = firstTaller.getLeft();
            else secondTaller = firstTaller.getRight();
        }
        restructure(unbalancedNode, firstTaller, secondTaller);
    }

    public void rebalanceUntilRoot(Node<K,V> n) {
        if (this.root != n) rebalanceDelete(n.getParent());
    }

    //finding first unbalanced node
    public Node<K,V> findUnbalanced(Node<K,V> n) {
        while (n != null) {
            setBalance(n);
            if (n.getBalance() > 1) return n;
            n = n.getParent();
        }
        return null;
    }

    //setting balance property in a node
    public void setBalance(Node<K,V> n) {
        n.setBalance(Math.abs(height(n.getLeft()) - height(n.getRight())));
    }

    //finding bigger subtrees of the unbalanced node
    public Node<K,V> findTallerChild(Node<K,V> n) {
        if (height(n.getLeft()) == height(n.getRight())) return null;
        else if (height(n.getLeft()) > height(n.getRight())) return n.getLeft();
        else return n.getRight();
    }

    //trinode restructuring
    public Node<K,V> restructure(Node<K,V> z, Node<K,V> y, Node<K,V> x) {
        //temp nodes for restructuring
        Node<K,V> a = new Node<K, V>(null, null);
        Node<K,V> b = new Node<K, V>(null, null);
        Node<K,V> c = new Node<K, V>(null, null);

        //four cases of prior node arrangement
        if (z.getRight() == y && y.getLeft() == x) {
            a = z; b = x; c = y;
        }
        else if (z.getRight() == y && y.getRight() == x) {
            a = z; b = y; c = x;
        }
        else if (z.getLeft() == y && y. getLeft() == x) {
            a = x; b = y; c = z;
        }
        else if (z.getLeft() == y && y.getRight() == x) {
            a = y; b = x; c = z;
        }
        //changing the root
        if (z == this.root) {
            this.root = b;
            b.setParent(null);
        }
        //reconnecting with unbalanced node's parent
        else {
            if (z.getParent().getLeft() == z) makeLeftChild(z.getParent(), b);
            else makeRightChild(z.getParent(), b);
        }
        //reconnecting nodes among each other
        if (b.getLeft() != x && b.getLeft() != y) makeRightChild(a, b.getLeft());
        if (b.getRight() != x && b.getRight() != y) makeLeftChild(c, b.getRight());
        makeLeftChild(b, a);
        makeRightChild(b, c);

        return b;
    }

    //reconnecting children & parents
    public void makeLeftChild(Node<K,V> n1, Node<K,V> n2) {
        if (n2 == null) n1.setLeft(null);
        else {
            n1.setLeft(n2);
            n2.setParent(n1);
        }
    }
    public void makeRightChild(Node<K,V> n1, Node<K,V> n2) {
        if (n2 == null) n1.setRight(null);
        else {
            n1.setRight(n2);
            n2.setParent(n1);
        }
    }

    //predecessor
    public Node<K,V> predecessor(Node<K,V> n) {

        if (n.getLeft() != null) {
            Node<K,V> left = n.getLeft();
            while (left.getRight() != null) {
                left = left.getRight();
            }
            return left;
        }
        else {
            Node<K,V> parent = n.getParent();
            while (parent != null && n == parent.getLeft()) {
                n = parent;
                parent = n.getParent();
            }
            return parent;
        }
    }

    //successor
    public Node<K,V> successor(Node<K,V> n) {

        if (n.getRight() != null) {
            Node<K,V> right = n.getRight();
            while (right.getLeft() != null) {
                right = right.getLeft();
            }
            return right;
        }
        else {
            Node<K,V> parent = n.getParent();
            while (parent != null && n == parent.getRight()) {
                n = parent;
                parent = n.getParent();
            }
            return parent;
        }
    }

    //traverse
    public void traverse(BiConsumer<K,V> visitor) {
        traverse(visitor, this.root);
    }

    private void traverse(BiConsumer<K,V> visitor, Node<K, V> n) {
        if (n != null) {
            traverse(visitor, n.getLeft());
            visitor.accept(n.getEntry().getKey(), n.getEntry().getValue());
            traverse(visitor, n.getRight());
        }
    }

    //traversal from K to K
    public void traverse(BiConsumer<K,V> visitor, K from, K to) {
        traverse(visitor, this.root, from, to);
    }

    private void traverse(BiConsumer<K,V> visitor, Node<K,V> n, K from, K to) {
        if (n != null) {
            traverse(visitor, n.getLeft(), from, to);
            if (from.compareTo(n.getEntry().getKey()) <= 0 && to.compareTo(n.getEntry().getKey()) >= 0) {
                visitor.accept(n.getEntry().getKey(), n.getEntry().getValue());
            }
            traverse(visitor, n.getRight(), from, to);
        }
    }
}
