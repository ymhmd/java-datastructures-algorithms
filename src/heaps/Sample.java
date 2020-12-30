package heaps;


public class Sample {
    public static void main(String[] args) {
        MinHeap minHeap = new MinHeap(10);
        minHeap.insert(4);
        minHeap.insert(3);
        minHeap.insert(1);
        minHeap.insert(2);
        minHeap.insert(5);
        System.out.println(minHeap.getRoot());
        minHeap.insert(-2);
        System.out.println(minHeap.getRoot());

        MaxHeap maxHeap = new MaxHeap(10);
        maxHeap.insert(1);
        maxHeap.insert(2);
        maxHeap.insert(3);
        maxHeap.insert(4);
        System.out.println(maxHeap.getRoot());
    }
}
