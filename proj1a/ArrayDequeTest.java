public class ArrayDequeTest {

    public static void testArray(){
        ArrayDeque<Integer> test = new ArrayDeque<>();
        for (int i = 0; i < 100; i++) {
            test.addFirst(i);
        }
        System.out.println(test.get(2));
        test.printDeque();
        test.removeFirst();
        test.removeLast();
        test.printDeque();
    }


    public static void main(String[] args){
        testArray();
    }
}