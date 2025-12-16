package com.semenihin;


public class CustomLinkedListImpl<E> implements CustomLinkedList<E> {

    private int size;
    private CustomNode<E> firstNode;
    private CustomNode<E> lastNode;


    public CustomLinkedListImpl() {
    }

    public CustomLinkedListImpl(CustomLinkedList<? extends E> customLinkedList) {
        if (customLinkedList == null) {
            throw new CustomListEmptyException("The provided list is null");
        }

        int otherSize = customLinkedList.size();
        if (otherSize != 0) {

            for (int i = 1; i <= otherSize; i++) {
                add(i, customLinkedList.get(i));
            }
        }
    }

    private void addAll(int index, CustomLinkedList<? extends E> other) {

    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public void addFirst(E el) {
        CustomNode<E> newNode = new CustomNode<>(el);
        if (this.firstNode == null) {
            addSingleFirstMode(newNode);
        } else {
            newNode.setNext(firstNode);
            firstNode.setPrevious(newNode);
            this.firstNode = newNode;
            increaseSize();
        }
    }

    @Override
    public void add(int index, E el) {
        checkIndexForAdd(index);
        if (index == 1) {
            addFirst(el);
        } else if (index == size + 1) {
            addLast(el);
        } else {
            CustomNode<E> currentNode = getNode(index);
            CustomNode<E> newNode = new CustomNode<>(el);

            newNode.setPrevious(currentNode.getPrevious());
            newNode.setNext(currentNode);

            newNode.getPrevious().setNext(newNode);
            currentNode.setPrevious(newNode);

            increaseSize();
        }
    }

    @Override
    public void addLast(E el) {
        CustomNode<E> newNode = new CustomNode<>(el);
        if (this.firstNode == null) {
            addSingleFirstMode(newNode);
        } else {
            this.lastNode.setNext(newNode);
            newNode.setPrevious(this.lastNode);
            this.lastNode = newNode;
            increaseSize();
        }
    }

    private void addSingleFirstMode(CustomNode<E> newNode) {
        this.firstNode = newNode;
        this.lastNode = newNode;
        increaseSize();
    }

    @Override
    public E getFirst() {
        return getNodeValue(firstNode);
    }

    @Override
    public E getLast() {
        return getNodeValue(lastNode);
    }

    @Override
    public E get(int index) {
        checkIndexForGetAndRemove(index);
        if (index == 1) {
            return firstNode.getValue();
        } else if (index == this.size) {
            return lastNode.getValue();
        }

        return getNode(index).getValue();
    }

    private E getNodeValue(CustomNode<E> node) {
        if (node == null) {
            throw new CustomListEmptyException("List is empty");
        }

        return node.getValue();
    }

    @Override
    public void removeFirst() {
        if (size == 0) {
            throw new CustomListEmptyException("List is empty");
        } else if (size == 1) {
            removeSingleLastMode();
        } else {
            this.firstNode = firstNode.getNext();
            decreaseSize();
        }
    }

    @Override
    public void removeLast() {
        if (size == 0) {
            throw new CustomListEmptyException("List is empty");
        } else if (size == 1) {
            removeSingleLastMode();
        } else {
            CustomNode<E> prev = lastNode.getPrevious();
            prev.setNext(null);
            this.lastNode = prev;
            decreaseSize();
        }
    }

    private void removeSingleLastMode() {
        firstNode = null;
        lastNode = null;
        decreaseSize();
    }

    @Override
    public void remove(int index) {
        checkIndexForGetAndRemove(index);
        if (index == 1) {
            removeFirst();
        } else if (index == this.size) {
            removeLast();
        } else {
            CustomNode<E> prev = getNode(index - 1);
            CustomNode<E> curr = prev.getNext();
            prev.setNext(curr.getNext());
            decreaseSize();
        }
    }

    private void increaseSize() {
        this.size++;
    }

    private void decreaseSize() {
        this.size--;
    }

    private CustomNode<E> getNode(int index) {
        if (index <= this.size / 2) {
            CustomNode<E> iteratorNode = firstNode;
            for (int i = 1; i < index; i++) {
                iteratorNode = iteratorNode.getNext();
            }
            return iteratorNode;
        } else {
            CustomNode<E> iterNode = lastNode;
            for (int i = size + 1; i > index; i--) {
                iterNode = iterNode.getPrevious();
            }
            return iterNode;
        }
    }

    private void checkIndexForAdd(int index) {
        if (index < 1 || index > size + 1) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    private void checkIndexForGetAndRemove(int index) {
        if (index < 1 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    protected static class CustomNode<E> {
        private final E value;
        private CustomNode<E> next;
        private CustomNode<E> previous;

        private CustomNode(E value) {
            this.value = value;
        }

        private void setPrevious(CustomNode<E> previous) {
            this.previous = previous;
        }

        private CustomNode<E> getPrevious() {
            return previous;
        }

        private void setNext(CustomNode<E> next) {
            this.next = next;
        }

        private CustomNode<E> getNext() {
            return next;
        }

        private E getValue() {
            return this.value;
        }
    }
}
