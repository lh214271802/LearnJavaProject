package cn.lh.learnproject.learn;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MyLinkedList<E> implements List<E> {

    private class Node<E> {
        private Node<E> pre;
        private E e;
        private Node<E> next;

        public Node(Node<E> pre, E e, Node<E> next) {
            this.pre = pre;
            this.e = e;
            this.next = next;
        }
    }


    private int size;

    private Node<E> first;
    private Node<E> last;

    public MyLinkedList() {

    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        Node<E> current = first;
        do {
            if (current.equals(o)) {
                return true;
            }
            current = current.next;
        } while (current != null);
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(E e) {
        if (size == 0) {
            first = new Node<>(null, e, null);
            last = first;
        } else {
            Node<E> node = new Node<>(last, e, null);
            last.next = node;
            last = node;
        }
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public E get(int index) {
        Node node = first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return (E) node.e;
    }

    @Override
    public E set(int index, E element) {
        Node node = first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        E e = (E) node.e;
        node.e = element;
        return e;
    }

    @Override
    public void add(int index, E element) {
        Node node = first;
        for (int i = 0; i < index - 1; i++) {
            node = node.next;
        }
        Node newNode = new Node(node, element, node.next);
        node.next = newNode;
        node.next.next.pre = newNode;
        size++;
    }

    @Override
    public E remove(int index) {
        E e = null;
        Node node;
        if (index == size - 1) {
            node = last;
            e = last.e;
            node.pre.next = null;
            last = node.pre;
        } else if (index == 0) {
            e = first.e;
            first.next.pre = null;
            first = first.next;
        } else {
            node = first;
            for (int i = 0; i < index - 1; i++) {
                node = node.next;
            }
            e = (E) node.next.e;
            node.next = node.next.next;
            node.next.pre = node;
        }
        size--;
        return e;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }
}
