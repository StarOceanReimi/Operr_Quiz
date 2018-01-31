import java.util.Iterator;

import quiz.LinkedList;
import quiz.SingleLinkedList;

/*
 * Simple use case for LinkedList
 */
public class App {
    public static void main(String[] args) {
        // LinkedList<Integer> list = new LinkedList<>();
        // list.push(1);
        // list.push(2);
        // list.push(3);
        // list.push(6);
        // list.push(8);
        // list.removeAllGeater(3);
        // System.out.println(list.pop());

        SingleLinkedList sl = new SingleLinkedList();
        sl.append(2);
        sl.append(3);
        sl.append(1);
        sl.append(4);
        sl.append(5);

        while(sl.size() != 0) {
            System.out.println(sl.removeTail());
        }

        

    }
}
