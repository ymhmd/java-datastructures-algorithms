package queues;


public class MyCircularQueue {
    int maxSize;
    int size;
    Node head, tail;

    class Node {
        Node next;
        int value;
    }

    public MyCircularQueue(int k) {
        this.maxSize = k;
        size = 0;
    }

    public boolean enQueue(int value) {
        if (isFull()) return false;
        Node node = new Node();
        node.value = value;
        if (isEmpty()) {
            head = tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
        size++;
        return true;
    }

    public boolean isEmpty() {
        return size < 1;
    }

    public boolean deQueue() {
        if (isEmpty()) return false;
        head = head.next;
        size--;
        return true;
    }

    public int Front() {
        if(isEmpty()) return -1;

        return head.value;
    }

    public int Rear() {
        if(isEmpty()) return -1;

        return tail.value;
    }

    public boolean isFull() {
        return maxSize == size;
    }


}
