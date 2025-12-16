package com.semenihin;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomLinkedListImplTest {

    private CustomLinkedListImpl<Integer> list;

    @BeforeEach
    void setUp() {
        list = new CustomLinkedListImpl<>();
    }

    @Test
    void shouldCreateEmptyList() {
        assertEquals(0, list.size());
    }

    @Test
    void addFirst_ShouldAddElementAtBeginning() {
        list.addFirst(10);

        assertEquals(1, list.size());
        assertEquals(10, list.getFirst());
    }

    @Test
    void addLast_ShouldAddElementToEnd() {
        list.addLast(5);
        list.addLast(10);

        assertEquals(2, list.size());
        assertEquals(10, list.getLast());
    }


    @Test
    void addAtIndex_ShouldInsertElementInMiddle() {
        list.addLast(1);
        list.addLast(3);
        list.add(1, 2);

        assertEquals(3, list.size());
        assertEquals(2, list.get(1));
    }


    @Test
    void addAtIndex_One_ShouldBehaveAsAddFirst() {
        list.add(1, 100);
        assertEquals(100, list.getFirst());
    }


    @Test
    void addAtIndex_Size_ShouldBehaveAsAddLast() {
        list.addLast(1);
        list.addLast(2);
        list.add(3, 3);

        assertEquals(3, list.getLast());
    }


    @Test
    void removeFirst_ShouldRemoveHead() {
        list.addLast(1);
        list.addLast(2);

        list.removeFirst();

        assertEquals(1, list.size());
        assertEquals(2, list.getFirst());
    }

    @Test
    void removeLast_ShouldRemoveTail() {
        list.addLast(1);
        list.addLast(2);

        list.removeLast();

        assertEquals(1, list.size());
        assertEquals(1, list.getLast());
    }


    @Test
    void removeAtIndex_ShouldRemoveElementInMiddle() {
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);

        list.remove(2);

        assertEquals(2, list.size());
        assertEquals(3, list.get(2));
    }

    @Test
    void get_ShouldReturnCorrectValue() {
        list.addLast(10);
        list.addLast(20);

        assertEquals(20, list.get(2));
    }

    @Test
    void getFirst_EmptyList_ShouldThrow() {
        assertThrows(CustomListEmptyException.class, () -> list.getFirst());
    }

    @Test
    void get_InvalidIndex_ShouldThrow() {
        list.addLast(1);

        assertThrows(IndexOutOfBoundsException.class, () -> list.get(5));
    }

    @Test
    void shouldCreateNewListWithOtherElements() {

        CustomLinkedListImpl<Integer> other = new CustomLinkedListImpl<>();
        other.addLast(3);
        other.addLast(4);

        list = new CustomLinkedListImpl<>(other);

        list.addLast(1);
        list.addLast(2);

        assertEquals(4, list.size());
        assertEquals(3, list.get(1));
        assertEquals(4, list.get(2));
    }
}