public class ArrayDequeTest {

    public static void testArray(){
        ArrayDeque<Integer> test = new ArrayDeque<>();
        for (int i = 0; i < 1000000; i++) {
            test.addFirst(i);
            test.addLast(i);
        }
        System.out.println(test.get(0));

        test.removeFirst();
        test.removeLast();
        System.out.println(test.get(0));

    }


    public static void main(String[] args){
        testArray();
    }
}