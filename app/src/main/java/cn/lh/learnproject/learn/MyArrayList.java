package cn.lh.learnproject.learn;


import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MyArrayList<E> implements List<E> {
    private transient Object[] elements;
    private int size;


    public MyArrayList() {
        this(16);
    }

    public MyArrayList(int capacity) {
        if (capacity < 0) {
            throw new RuntimeException("容量不小于0");
        }
        elements = new Object[capacity];
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
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) return true;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (elements[i].equals(o)) return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        Object[] objects = new Object[elements.length];
        System.arraycopy(elements, 0, objects, 0, size);
        return objects;
    }


    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(E e) {
        ensureCapacity();
        elements[size++] = e;
        return true;
    }

    private void ensureCapacity() {
        if (size + 1 >= elements.length) {
            Object[] objects = new Object[2 * elements.length + 2];
            System.arraycopy(elements, 0, objects, 0, size);
            elements = objects;
        }
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
        if (index >= size || index < 0) {
            throw new RuntimeException("越界了！");
        }
        return (E) elements[index];
    }

    @Override
    public E set(int index, E element) {
        return null;
    }

    @Override
    public void add(int index, E element) {

    }

    @Override
    public E remove(int index) {
        return null;
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
