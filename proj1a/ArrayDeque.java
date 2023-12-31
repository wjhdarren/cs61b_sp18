public class ArrayDeque<T> {
    private T[] items;

    private int size;
    private int front = 0; //position of the first element in the deque
    private int rear = 0; //position of the last element in the deque + 1
    private static final int RFACTOR = 2;


    /** Resize items with capability length. */
    @SuppressWarnings("unchecked")
    private void resize(int capacity) {
        T[] tmp = (T[]) new Object[capacity];
        int oldSize = size;

        for (int i = 0; i < oldSize; i++) {
            tmp[i] = get(i);
        }
        items = tmp;
        front = 0;
        rear = oldSize - 1;
    }

    /** Check and resize down after removals. */
    private void downsize() {
        double ratio = (double) size / items.length;
        if (ratio < 0.25 && size > 0) {
            T[] tmp = (T[]) new Object[(int) (0.5 * items.length)];
            for (int i = 0; i < size; i++) {
                tmp[i] = get(i);
            }
            items = tmp;
            front = 0;
            rear = size - 1; //can size = 0 here?
        }
    }

    /** Create an empty array deque */
    @SuppressWarnings("unchecked")
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
    }

    /**
     * Adds an item of type T to the front of the deque.
     */
    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * RFACTOR);
        }

        if (!isEmpty()) {
            front = (front - 1 + items.length) % items.length;
            //add items.length before moduling to keep front from being negative
        }
        items[front] = item;
        size++;

    }

    /**
     * Adds an item of type T to the back of the deque.
     */
    public void addLast(T item) {
        if (size == items.length) {
            resize(size * RFACTOR);
        }

        if (!isEmpty()) {
            rear = (rear + 1) % items.length;
        }
        items[rear] = item;
        size++;

    }

    /**
     * Returns true if deque is empty, false otherwise.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of items in the deque.
     */
    public int size() {
        return size;
    }

   /** Prints the items in the deque from first to last, separated by a space.*/
    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(items[(front + i) % items.length] + " ");
        }
        System.out.println();
    }

    /** Removes and returns the item at the front of the deque.
     * If no such item exists, returns null.*/

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }

        T firstItem = items[front];
        items[front] = null; // Help with garbage collection
        front = (front + 1) % items.length;
        size--;

        if (isEmpty()) {
            front = 0;
            rear = 0;
        }
        downsize();
        return firstItem;
    }

    /** Removes and returns the item at the back of the deque.
     * If no such item exists, returns null. */
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }

        T lastItem = items[rear];
        items[rear] = null;
        rear = (rear - 1 + items.length) % items.length;
        size--;

        if (isEmpty()) {
            front = 0;
            rear = 0;
        }
        downsize();
        return lastItem;
    }

    /** Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null. Must not alter the deque! */
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return items[(front + index) % items.length];
    }
}
