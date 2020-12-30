package heaps;

import java.util.NoSuchElementException;

public class MaxHeap extends Heap {

    public MaxHeap(int maxSize) {
        super(maxSize);
    }

    public void insert(int element) {
        int currentElementIndex = size;
        array[currentElementIndex] = element;
        size++;
        int parentIndex = getParentIndex(size);
        while (parentIndex >= 0 && (array[parentIndex] < array[currentElementIndex])) {
            int temp = array[currentElementIndex];
            array[currentElementIndex] = array[parentIndex];
            array[parentIndex] = temp;

            currentElementIndex = parentIndex;
            parentIndex = getParentIndex(currentElementIndex);
        }
    }

    public void heapify(int i) {
        if (isLeaf(i)) return;

        if (array[i] < array[getLeftNode(i)]) {
            int temp = array[i];
            array[i] = array[getLeftNode(i)];
            array[getLeftNode(i)] = temp;
            heapify(getLeftNode(i));
        }

        if (array[i] < array[getRightNode(i)]) {
            int temp = array[i];
            array[i] = array[getRightNode(i)];
            array[getRightNode(i)] = temp;
            heapify(getRightNode(i));
        }
    }

    public int getRoot() {
        if (size == 0) throw new NoSuchElementException();
        int root = array[0];
        size--;
        array[0] = array[size];
        heapify(0);
        return root;
    }
}
