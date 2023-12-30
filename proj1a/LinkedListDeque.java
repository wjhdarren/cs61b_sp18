public class LinkedListDeque<T>{
    private final ItemNode sentinel;
    private int size;
   
    private class ItemNode {
        public ItemNode prev;
        public T item;
        public ItemNode next; 
        public ItemNode(T something, ItemNode p, ItemNode n){
            prev = p;
            item = something;
            next = n;
        }
    }
    /** Create an empty deque */
    public LinkedListDeque(){

        sentinel = new ItemNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    /**Add an item to the front of the deque */
    public void addFirst(T item){
        ItemNode newNode = new ItemNode(item, sentinel, sentinel.next);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size ++;
    }

    /** Add an item to the back of the deque */
    public void addLast(T item){
        ItemNode newNode = new ItemNode(item, sentinel.prev, sentinel);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        size ++;
    }

    /** Return true if deque is empty, false otherwise */
    public boolean isEmpty(){
        return sentinel.next == sentinel;
    }

    /** Return the number of items in the deque */
    public int size(){
        return size;
    }

    /** Prints the items in the deque from first to last, separated by a space. */
    public void printDeque(){
        ItemNode p = sentinel.next;
        while(p != sentinel){
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.println();
    }

    /** Removes and returns the item at the front of the deque. If no such item exists, returns null.*/
    public T removeFirst(){
        if(sentinel.next == sentinel){
            return null;
        }
        ItemNode p = sentinel.next;
        sentinel.next = p.next;
        p.next.prev = sentinel;
        size --;
        return p.item;
    }

    /** Removes and returns the item at the back of the deque. If no such item exists, returns null.*/
    public T removeLast(){
        if(sentinel.next == sentinel){
            return null;
        }
        ItemNode p = sentinel.prev;
        sentinel.next = p.prev;
        p.prev.next = sentinel;
        size --;
        return p.item;
    }

    /** Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null. Must not alter the deque! */
    public T get(int index){
        if (index < 0 || index >= size){
            return null;
        }
        ItemNode p = sentinel.next;
        for (int i = 0; i < index; i++){
            p = p.next;
            }
        return p.item;
        }
    /** Same as get, but uses recursion. */
    public T getRecursive(int index){
        if ( index < 0 || index >= size){
            return null;
        } else if (index == 0){
            return sentinel.next.item;
        } else {
            return getRecursive(index - 1);
        }
    }
}