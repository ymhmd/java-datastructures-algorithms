package heaps;

public class Heap  {
    protected int[] array;
    protected int size;

    public Heap(int maxSize) {
        array = new int[maxSize];
        size = 0;
    }

    public int getRightNode(int i) {
        return (2 * i + 2);
    }

    public int getLeftNode(int i) {
        return (2 * i + 1);
    }

    public int getParentIndex(int i) {
        return ((i - 1) / 2);
    }

    public boolean isLeaf(int i) {
        return ((2 * i + 1) > size) || ((2 * i + 2) > size);
    }

}
