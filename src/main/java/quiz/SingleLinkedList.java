package quiz;

import java.util.Iterator;

import org.omg.CORBA.TRANSACTION_UNAVAILABLE;

public class SingleLinkedList implements Iterable<Integer> {

    private static class Node {
        int val;
        Node prev;
        Node(int val, Node prev) {
            this.val = val;
            this.prev = prev;
        }
    }

    private Node tail;
    private int size = 0;

    public int size() {
        return size;
    }

    public SingleLinkedList() {}

    public void append(int val) {
        tail = new Node(val, tail);
        size++;
    }

    public int removeTail() {
        if(tail == null) 
            throw new NullPointerException("List is empty");
        int ret = tail.val;
        tail = tail.prev;
        size--;
        return ret;
    }

    public void removeGreaterThan(int target) {

        Node next = null;
        Node cur = tail; 
        while(cur != null)  {
            if(cur.val > target) {
                if(next == null) { 
                    this.removeTail();
                    cur = tail;
                } else {
                    next.prev = cur.prev;
                    cur = cur.prev;
                    size--;
                }
            } else {
                next = cur;
                cur = cur.prev;
            }
        }
    }

    @Override
    public Iterator<Integer> iterator() {
        return new SingleListIterator(tail);
    }

    class SingleListIterator implements Iterator<Integer> {
        Node cur;
        SingleListIterator(Node cur) {
            this.cur = cur;
        }

        @Override
        public boolean hasNext() {
            return cur != null;
        }

        @Override
        public Integer next() {
            int ret = cur.val;
            cur = cur.prev;
            return ret;
        }

        @Override
        public void remove() {
            Iterator.super.remove();
        }

    }

}