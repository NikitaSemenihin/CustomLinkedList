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
            for (int i = 0; i <= otherSize - 1; i++) {
                add(i, customLinkedList.get(i));
            }
        }
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public void addFirst(E element) {
        CustomNode<E> newNode = new CustomNode<>(element);
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
    public void add(int index, E element) {
        checkIndexForAdd(index);
        if (index == 0) {
            addFirst(element);
        } else if (index == size) {
            addLast(element);
        } else {
            CustomNode<E> currentNode = getNode(index);
            CustomNode<E> prevNode = currentNode.getPrevious();
            CustomNode<E> newNode = new CustomNode<>(element);

            newNode.setPrevious(prevNode);
            newNode.setNext(currentNode);

            prevNode.setNext(newNode);
            currentNode.setPrevious(newNode);

            increaseSize();
        }
    }

    @Override
    public void addLast(E element) {
        CustomNode<E> newNode = new CustomNode<>(element);
        if (this.lastNode == null) {
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

    private E getNodeValue(CustomNode<E> node) {
        if (node == null) {
            throw new CustomListEmptyException("List is empty");
        }

        return node.getValue();
    }

    @Override
    public E get(int index) {
        checkIndexForGetAndRemove(index);
        if (index == 0) {
            return firstNode.getValue();
        } else if (index == this.size - 1) {
            return lastNode.getValue();
        }

        return getNode(index).getValue();
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
        if (index == 0) {
            removeFirst();
        } else if (index == this.size - 1) {
            removeLast();
        } else {
            CustomNode<E> curr = getNode(index);
            CustomNode<E> prev = curr.getPrevious();
            CustomNode<E> next = curr.getNext();

            prev.setNext(next);
            next.setPrevious(prev);

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
            for (int i = 0; i < index; i++) {
                iteratorNode = iteratorNode.getNext();
            }
            return iteratorNode;
        } else {
            CustomNode<E> iterNode = lastNode;
            for (int i = size; i > index; i--) {
                iterNode = iterNode.getPrevious();
            }
            return iterNode;
        }
    }


    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    private void checkIndexForGetAndRemove(int index) {
        if (index < 0 || index > size - 1) {
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
