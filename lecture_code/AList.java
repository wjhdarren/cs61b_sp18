/** Array based list.
 *  @author Josh Hug
 */

public class AList {
    int[] items;
    int size;
    /** Creates an empty list. */
    public AList() {
        items = new int[100];
        size = 0;
    }

    /** Resize items with capability length. */
    public void resize(int capability){
        int[] tmp = new int[capability];
        System.arraycopy(items, 0, tmp, 0, size);
        items = tmp;
    }
    /** Inserts X into the back of the list. */
    public void addLast(int x) {
        if (size == items.length){
            resize(size*2); //Geometric resizing.
        }
        items[size] = x;
        size++;
    }

    /** Returns the item from the back of the list. */
    public int getLast() {
        return 0;        
    }
    /** Gets the ith item in the list (0 is the front). */
    public int get(int i) {
        return 0;        
    }

    /** Returns the number of items in the list. */
    public int size() {
        return 0;        
    }

    /** Deletes item from back of the list and
      * returns deleted item. */
    public int removeLast() {
        return 0;
    }
} 
