package quiz;

import java.util.Iterator;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * Two-way linked list
 * @author Li Qiu
 */
public class LinkedList<T extends Comparable<T>> implements Iterable<T> {

    private static class Node<T> {
        Node(T value, Node<T> prev, Node<T> next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
        Node<T> next;
        Node<T> prev;
        T value;
    }

    private Node<T> head;
    private Node<T> tail;

    private int size;

    public LinkedList() {
        size = 0;
        head = null;
        tail = null;
    }

    private boolean emptyList() {
        return head == null && tail == null && size == 0;
    }

    private void resetPointerToNull() {
        this.head = null;
        this.tail = null;
    }

    private Node<T> removeNode(Node<T> node) {
        Objects.requireNonNull(node, "node can not be null");
        Node<T> prev = node.prev;
        Node<T> next = node.next;
        if(prev != null) { 
            //if prev is last element, reset tail
            if(next == null) tail = prev;
            prev.next = next;
        }
        if(next != null) { 
            //if next is first element, reset head
            if(prev == null) head = next;
            next.prev = prev;
        }
        size--;
        return next;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return this.size;
    }

    /**
     * Append an element into list
     */
    public void push(T value) {
        if(emptyList()) {
            head = new Node<T>(value, null, null);
            tail = head;
        } else {
            tail.next = new Node<T>(value, tail, null);
            tail = tail.next;
        }
        size++;
    }

    /**
     * 
     */
    public T pop() {
        if(emptyList()) 
            throw new NullPointerException("Can not pop with empty list");
        size--;
        Node<T> poped = tail;
        tail = poped.prev;
        //if tail is not first element
        if(tail != null) tail.next = null;
        else resetPointerToNull();
        return poped.value;
    }

    public void removeAllGeater(T value) {
        removeIf(t->t.compareTo(value) > 0);
    }

    /**
     * go through list remove node with condition
     */
    public void removeIf(Predicate<T> pred) {
        Node<T> cur = head;
        while(cur != null) {
            if(pred.test(cur.value))
                cur = removeNode(cur);
            else
                cur = cur.next;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator(this.head);
    }

    private class ListIterator implements Iterator<T> {

        Node<T> cur;
        ListIterator(Node<T> cur) {
            this.cur = cur;
        }

        @Override
        public void remove() {
            cur = LinkedList.this.removeNode(cur.prev);
        }

        @Override
        public boolean hasNext() {
            return cur != null;
        }

        @Override
        public T next() {
            T val = cur.value;
            cur = cur.next;
            return val;
        }

    }

}