package leetcode;

public class A641MyCircularDeque {


    public static void main(String[] args) {
        A641MyCircularDeque obj = new A641MyCircularDeque(3);
        System.out.println(obj.insertLast(1));            // 返回 true
        System.out.println(obj.insertLast(2));       // 返回 true
        System.out.println(obj.insertFront(3));            // 返回 true
        System.out.println(obj.insertFront(4));            // 已经满了，返回 false
        System.out.println(obj.getRear());        // 返回 2
        System.out.println(obj.isFull());                // 返回 true
        System.out.println(obj.deleteLast());            // 返回 true
        System.out.println(obj.insertFront(4));            // 返回 true
        System.out.println(obj.getFront());        // 返回 4
    }

    private final int[] data;
    private final int capacity;
    private int head;
    private int tail;
    private int size;

    public A641MyCircularDeque(int k) {
        data = new int[k];
        head = 0;
        tail = 0;
        size = 0;
        capacity = k;
    }

    public boolean insertFront(int value) {
        if (isFull()) {
            return false;
        }
        boolean isEmpty = isEmpty();
        head = (head - 1 + capacity) % capacity;
        data[head] = value;
        if (isEmpty) {
            tail = head;
        }
        size++;
        return true;
    }

    public boolean insertLast(int value) {
        if (isFull()) {
            return false;
        }
        boolean isEmpty = isEmpty();
        tail = (tail + 1) % capacity;
        data[tail] = value;
        if (isEmpty) {
            head = tail;
        }
        size++;
        return true;
    }

    public boolean deleteFront() {
        if (isEmpty()) {
            return false;
        }
        head = (head + 1) % capacity;
        size--;
        return true;
    }

    public boolean deleteLast() {
        if (isEmpty()) {
            return false;
        }
        tail = (tail - 1 + capacity) % capacity;
        size--;
        return true;
    }

    public int getFront() {
        if (isEmpty()) {
            return -1;
        }
        return data[head];
    }

    public int getRear() {
        if (isEmpty()) {
            return -1;
        }
        return data[tail];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }
}
