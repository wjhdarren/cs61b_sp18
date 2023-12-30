public class ArrayDeque<T> {
    private T[] items;

    private int front = 0; //position of the first element in the deque
    private int rear = 0; //position of the last element in the deque + 1
    private static final int RFACTOR = 2;


    /** Resize items with capability length. */
     @SuppressWarnings("unchecked")
     public void resize(int capacity) {
         T[] tmp = (T[]) new Object[capacity];
         System.arraycopy(items, 0, tmp, 0, size);
         items = tmp;
     }

    /** Create an empty array deque */
    @SuppressWarnings("unchecked")
    public ArrayDeque() {
        items = (T[]) new Object[8];
    }

    /**
     * Adds an item of type T to the front of the deque.
     */
    public void addFirst(T item) {
        if (size() == items.length){
            resize(size() * RFACTOR);
        }
        front = (front - 1 + items.length) % items.length;
        items[front] = item;
    }

    /**
     * Adds an item of type T to the back of the deque.
     */
    public void addLast(T item) {
        if (size() == items.length){
            resize(size() * RFACTOR);
        }
        items[rear] = item;
        rear = (rear + 1) % items.length;
    }

    /**
     * Returns true if deque is empty, false otherwise.
     */
    public boolean isEmpty() {
        return front == rear;
    }

    /**
     * Returns the number of items in the deque.
     */
    public int size() {
        return (rear - front + items.length) % items.length;
    }

   /** Prints the items in the deque from first to last, separated by a space.*/
    public void printDeque() {
        for (int i = 0; i< this.size(); i++) {
            System.out.print(items[(front + i) % items.length] + " ");
        }
        System.out.println();
    }

    /** Removes and returns the item at the front of the deque.
     * If no such item exists, returns null.*/
    public T removeFirst() {
        if (front == rear) {
            return null;
        }
        T firstitem = items[front];
        front = (front + 1) % items.length;
        return firstitem;
    }

    /** Removes and returns the item at the back of the deque.
     * If no such item exists, returns null. */
    public T removeLast() {
        if (front == rear) {
            return null;
        }
        rear = (rear - 1 + items.length) % items.length;
        return items[rear];
    }

    /** Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null. Must not alter the deque! */
    public T get(int index) {
        if (index < 0 || index >= size()) {
            return null;
        }
        return items[(front + index + items.length) % items.length];
    }
}
