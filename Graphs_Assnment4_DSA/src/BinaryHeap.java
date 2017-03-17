import java.util.ArrayList;
/**
 * Created by Sonya on 24.10.16.
 */
public class BinaryHeap<T extends Comparable<T>> {
    //MinHeap

    private int size;
    private ArrayList<T> entries = new ArrayList<>();

    public BinaryHeap() {
        this.size = 0;
    }

    public boolean isEmpty() {
        return this.entries.size() == 0;
    }

    public int size() {
        return this.size;
    }

    public int getIndexOf(T element) {
        return entries.indexOf(element);
    }

    public void add(T element) {
        //bottom level - adding to the last element in an array
        entries.add(element);
        size++;

        int index = entries.size() - 1;

        if (index == 0) return;

        if (entries.get(index).compareTo(entries.get((index - 1)/2)) > 0) {
            return;
        }
        else {
            heapUp();
        }
    }

    public T delete() {
        if (this.isEmpty()) return null;

        T result = entries.get(0);
        //swapping the first and the last elements
        swap(0, entries.size() - 1);
        //setting the last element to null
        entries.remove(entries.size() - 1);
        size--;

        heapDown();

        return result;
    }

    //getting the minimum element
    public T peek() {
        if (this.isEmpty()) return null;
        else return entries.get(0);
    }

    //going up the heap to fix the order
    public void heapUp() {
        int index = entries.size() - 1;
        while (index != 0 && entries.get(index).compareTo(entries.get((index - 1)/2)) < 0) {
            swap(index, (index - 1)/2);
            index = (index - 1)/2;
        }
    }

    public void heapUp(int index) {
        while (index != 0 && entries.get(index).compareTo(entries.get((index - 1)/2)) < 0) {
            swap(index, (index - 1)/2);
            index = (index - 1)/2;
        }
    }

    //going down the heap to fix the order
    public void heapDown() {
        int index = 0;
        int indexBiggerChild = 0;

        while (index != entries.size() - 1 && (2 * index + 1) < entries.size() && entries.get(2*index + 1) != null) {
            indexBiggerChild = 2*index + 1;

            //checking if another child is a bigger child
            if ((2*index + 2) < entries.size() && entries.get(2*index + 2) != null && entries.get(indexBiggerChild).compareTo(entries.get(2*index + 2)) > 0) {
                indexBiggerChild = 2*index + 2;
            }

            //comparing the moving item and its bigger child
            if (entries.get(index).compareTo(entries.get(indexBiggerChild)) > 0) swap(index, indexBiggerChild);
            else break;

            index = indexBiggerChild;
        }
    }

    //swapping two elements
    public void swap(int index1, int index2) {
        T temp = entries.get(index2);
        entries.set(index2, entries.get(index1));
        entries.set(index1, temp);
    }

}
