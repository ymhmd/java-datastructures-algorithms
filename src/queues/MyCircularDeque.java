package queues;

class MyCircularDeque {

    int maxSize;
    int size;
    Node head, tail;

    class Node {
        Node next;
        int value;
    }

    public MyCircularDeque(int k) {
        this.maxSize = k;
        size = 0;
    }

    /** Adds an item at the front of Deque. Return true if the operation is successful. */
    public boolean insertFront(int value) {
        if (isFull()) return false;
        Node node = new Node();
        node.value = value;
        if (isEmpty()) {
            head = tail = node;
        } else {
            node.next = head;
            head = node;
        }
        size++;
        return true;
    }

    /** Adds an item at the rear of Deque. Return true if the operation is successful. */
    public boolean insertLast(int value) {
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

    /** Deletes an item from the front of Deque. Return true if the operation is successful. */
    public boolean deleteFront() {
        if (isEmpty()) return false;

        head = head.next;
        size--;
        return true;
    }

    /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
    public boolean deleteLast() {
        if (isEmpty()) return false;

        Node prev= head, current = head;
        for (int i=0; i < size - 1; i++) {
            prev = current;
            current = current.next;
        }
        prev.next = null;
        tail = prev;
        size--;
        return true;
    }

    /** Get the front item from the deque. */
    public int getFront() {
        if (isEmpty()) return -1;

        return head.value;
    }

    /** Get the last item from the deque. */
    public int getRear() {
        if (isEmpty()) return -1;

        return tail.value;
    }

    /** Checks whether the circular deque is empty or not. */
    public boolean isEmpty() {
        return size < 1;
    }

    /** Checks whether the circular deque is full or not. */
    public boolean isFull() {
        return maxSize == size;
    }

    static public int shortestSubarray(int[] A, int K) {

        int shortest = Integer.MAX_VALUE;

        for (int i=0; i<A.length; i++) {
            if (A[i] == K) return 1;
            int sum = 0;
            int j = i;
            int shortestLength = 0;
            while (sum < K && j < A.length) {
                sum += A[j];
                j++;
                shortestLength++;
                if (sum >= K) {
                    shortest = Math.min(shortest, shortestLength);
                    break;
                }
            }
        }

        if (shortest == Integer.MAX_VALUE) return -1;
        return shortest;

    }


    public static void main(String[] args) {
        System.out.println(shortestSubarray(new int[]{17,85,93,-45,-21}, 150));

        System.out.println(shortestSubarray(new int[]{1, 2}, 4));
        System.out.println(shortestSubarray(new int[]{2,-1,2}, 3));
        System.out.println(shortestSubarray(new int[]{77,19,35,10,-14}, 19));

    }
}
