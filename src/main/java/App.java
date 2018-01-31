import java.util.Iterator;

import quiz.LinkedList;

/*
 * Simple use case for LinkedList
 */
public class App {
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        list.push(1);
        list.push(2);
        list.push(3);
        for(Integer i : list) { System.out.println(i); }

        Iterator<Integer> iter = list.iterator();
        for(; iter.hasNext(); ) {
            if(iter.next() == 2) iter.remove();
        }

        for(Integer i : list) { System.out.println(i); }



    }
}
