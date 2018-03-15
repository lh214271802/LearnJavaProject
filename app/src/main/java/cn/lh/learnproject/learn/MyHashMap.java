package cn.lh.learnproject.learn;

import android.support.annotation.NonNull;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Created by liaohui on 2018/3/14.
 * <p>数组加链表的结构实现
 * 分两次索引，先索引key对应hash值的数组的元素的头结点，
 * 然后根据头结点（链表）的key继续索引真实的节点数据
 */

public class MyHashMap<K, V> implements Map<K, V> {

    int size;

    Node[] nodes;

    static class Node<K, V> {
        final int hash;
        final K key;
        V value;
        Node<K, V> next;

        Node(int hash, K key, V value, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public MyHashMap() {
        this(10);
    }

    public MyHashMap(int capatcity) {
        nodes = new Node[capatcity];
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
    public boolean containsKey(Object key) {
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public V get(Object key) {
        return null;
    }

    @Override
    public V put(K key, V value) {
        nodes[size++] = new Node(key.hashCode(), key, value, null);
        return value;
    }

    @Override
    public V remove(Object key) {
        return null;
    }

    @Override
    public void putAll(@NonNull Map<? extends K, ? extends V> m) {

    }

    @Override
    public void clear() {

    }

    @NonNull
    @Override
    public Set<K> keySet() {
        return null;
    }

    @NonNull
    @Override
    public Collection<V> values() {
        return null;
    }

    @NonNull
    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }
}
