package org.example;

import java.util.Collection;
import java.util.LinkedList;

public class MyLinkedList<T> extends LinkedList<T> {
    public MyLinkedList() {
        super();
    }

    public MyLinkedList(Collection<? extends T> collection) {
        super(collection);
    }

    @Override
    public boolean add(T element) {
        return super.add(element);
    }

    @Override
    public void add(int index, T element) {
        super.add(index, element);
    }

    @Override
    public void addFirst(T element) {
        super.addFirst(element);
    }

    @Override
    public void addLast(T element) {
        super.addLast(element);
    }

    @Override
    public void clear() {
        super.clear();
    }

    @Override
    public boolean contains(Object o) {
        return super.contains(o);
    }

    @Override
    public T element() {
        return super.element();
    }

    @Override
    public T get(int index) {
        return super.get(index);
    }

    @Override
    public T getFirst() {
        return super.getFirst();
    }

    @Override
    public T getLast() {
        return super.getLast();
    }

    @Override
    public int indexOf(Object o) {
        return super.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return super.lastIndexOf(o);
    }

    @Override
    public T peek() {
        return super.peek();
    }

    @Override
    public T peekFirst() {
        return super.peekFirst();
    }

    @Override
    public T peekLast() {
        return super.peekLast();
    }

    @Override
    public T pollFirst() {
        return super.pollFirst();
    }

    @Override
    public T pollLast() {
        return super.pollLast();
    }

    @Override
    public T pop() {
        return super.pop();
    }

    @Override
    public void push(T element) {
        super.push(element);
    }

    @Override
    public T remove() {
        return super.remove();
    }

    @Override
    public T remove(int index) {
        return super.remove(index);
    }

    @Override
    public boolean remove(Object o) {
        return super.remove(o);
    }

    @Override
    public T removeFirst() {
       return super.removeFirst();
    }

    @Override
    public T removeLast() {
        return super.removeLast();
    }

    @Override
    public T set(int index, T element) {
        return super.set(index, element);
    }

    @Override
    public int size() {
        return super.size();
    }

    @Override
    public Object[] toArray() {
        return super.toArray();
    }
}
