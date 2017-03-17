/**
 * Created by Sonya on 19.10.16.
 */
public class Test {

    public static void main(String[] args) {

        AVLTree<String, Integer> tree = new AVLTree<>();


        tree.insert("a", 50);
        tree.insert("b", 40);
        tree.insert("c", 80);
        tree.insert("d", 100);
        tree.insert("e", 20);
        tree.insert("g", 60);
        tree.insert("h", 55);
        tree.insert("i", 58);
        tree.insert("j", 48);
        tree.insert("k", 47);
        tree.insert("l", 43);
        tree.insert("m", 28);
        tree.insert("n", 28);
        tree.insert("o", 28);
        tree.insert("p", 28);
        tree.insert("q", 28);
        tree.insert("r", 28);
        tree.insert("s", 28);
        tree.insert("t", 28);
        tree.insert("u", 28);
        tree.insert("v", 28);
        tree.insert("w", 28);
        tree.insert("x", 28);
        tree.insert("y", 28);
        tree.insert("z", 28);

        Node<String, Integer> s = tree.search("f");

        /*tree.traverse((key, value) -> {
            System.out.println(key + " " + value);
        }, "m", "p");

        tree.traverse((key, value) -> {
            System.out.println(key + " " + value);
        });*/


    /*CODE DUMP
    *public V delete(K key) {
        Node<K,V> toBeDeleted = search(key);
        //there's no such key
        if (key.compareTo(toBeDeleted.getEntry().getKey()) != 0) {
            return null;
        }
        Node<K,V> parent = toBeDeleted.getParent();
        Node<K,V> left = toBeDeleted.getLeft();
        Node<K,V> right = toBeDeleted.getRight();

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
        }
        else {
            Node<K,V> suc = successor(toBeDeleted);
            Node<K,V> pre = predecessor(toBeDeleted);

            if (left != null && right != null && parent.getLeft() == toBeDeleted) {
                parent.setLeft(suc);
                right.setParent(parent);
                toBeDeleted.setParent(null);
                size--;
            }
            else if (left != null && right != null && parent.getRight() == toBeDeleted) {
                parent.setRight(suc);
                suc.setParent(parent);
                toBeDeleted.setParent(null);
                size--;
            }
            else if (left != null) {
                parent.setRight(pre);
                pre.setParent(parent);
                toBeDeleted.setParent(null);
                size--;
            }
            else if (right != null) {
                parent.setLeft(pre);
                pre.setParent(parent);
                toBeDeleted.setParent(null);
                size--;
            }
        }

        rebalanceDelete(parent); //TODO: rebalance more up until the root
        return toBeDeleted.getEntry().getValue();
    }
     *
      * if (n == null) return null;
        Node<K,V> child = n.getLeft();

        if (child == null) {
            while (n.getParent() != null) {
                n = n.getParent();
            }
            return n;
        }

        if (key.compareTo(n.getEntry().getKey()) == 0) {

            while (child.getRight() != null) {
                child = child.getRight();
            }
            return child;
        }

        else if (key.compareTo(n.getEntry().getKey()) > 0) {
            child = predecessor(n.getRight(), key);
        }
        else if (key.compareTo(n.getEntry().getKey()) < 0) {
            child = predecessor(n.getLeft(), key);
        }
        return child;

          Node<K,V> predecessor = null;
        Node<K,V> current = root;

        if (root == null) return null;

        while (current != null && key.compareTo(current.getEntry().getKey()) == 0) {
            if (key.compareTo(current.getEntry().getKey()) < 0) {
                current = current.getLeft();
            }
            else {
                predecessor = current;
                current = current.getRight();
            }
        }

        if (current != null && current.getLeft() != null) {
            predecessor = findMax(current.getLeft());
        }
        return predecessor;

            public Node<K,V> findMax(Node<K,V> root) {
        if (root == null) return null;
        while (root.getRight() != null) root = root.getRight();
        return root;
    }*/



    }

}
