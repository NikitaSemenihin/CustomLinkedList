package com.semenihin;

public interface CustomLinkedList<E> {
    int size();
    void addFirst(E el);
    void addLast(E el);
    void add(int index, E el);
    E getFirst();
    E getLast();
    E get(int index);
    void removeFirst();
    void removeLast();
    void remove(int index);
}
