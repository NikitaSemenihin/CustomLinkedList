package com.semenihin;

public interface CustomLinkedList<E> {
    int size();
    void addFirst(E element);
    void addLast(E element);
    void add(int index, E element);
    E getFirst();
    E getLast();
    E get(int index);
    void removeFirst();
    void removeLast();
    void remove(int index);
}
