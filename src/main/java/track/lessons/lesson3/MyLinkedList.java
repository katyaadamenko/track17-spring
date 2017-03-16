package track.lessons.lesson3;

import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * Должен наследовать List
 * Односвязный список
 */
public class MyLinkedList extends List implements Stack, Queue {
    private Node head;
    private Node tail;
    /**
     * private - используется для сокрытия этого класса от других.
     * Класс доступен только изнутри того, где он объявлен
     * <p>
     * static - позволяет использовать Node без создания экземпляра внешнего класса
     */

    private static class Node {
        Node prev;
        Node next;
        int val;

        Node(Node prev, Node next, int val) {
            this.prev = prev;
            this.next = next;
            this.val = val;
        }
    }

    private Node find(int idx) throws NoSuchElementException {
        if (idx < 0  || idx >= size) {
            throw new NoSuchElementException("MyLinkedList: wrong index");
        }
        Node cur = head;
        for (int index = 0; index < idx; ++index) {
            cur = cur.next;
        }
        return cur;
    }

    @Override
    public void add(int item) {
        Node newNode = new Node(tail, null, item);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    @Override
    public int remove(int idx) throws NoSuchElementException {
        Node cur = find(idx);
        if (cur.equals(head) && cur.equals(tail)) {
            head = null;
            tail = null;
        } else if (cur.equals(head)) {
            head = cur.next;
            cur.next.prev = null;
        } else if (cur.equals(tail)) {
            tail = cur.prev;
            cur.prev.next = null;
        } else {
            cur.prev.next = cur.next;
            cur.next.prev = cur.prev;
        }
        size--;
        return cur.val;
    }

    @Override
    public int get(int idx) throws NoSuchElementException {
        Node cur = find(idx);
        return cur.val;
    }

    public void push(int value) {
        add(value);
    }

    public int pop() {
        return remove(size - 1);
    }

    public void enqueue(int value) {
        add(value);
    }

    public int dequeue() {
        return remove(0);
    }

    /*@Override
    public int size() {
        return size;
    }*/
}
