package com.skillbox;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CustomStack<E> implements Stack<E>{

    private List<E> list;
    private int capacity;

    @Override
    public void push(E element) throws StackException {
        if (isFull()) {
            throw new StackException("Невозможно добавить элемент, так как стэк переполнен.");
        }

        list.add(element);
    }

    @Override
    public E pop() throws StackException {
        if (isEmpty()) {
            throw new StackException("Невозможно произвести удаление верхнего элемента, так как стэк пуст.");
        }

        int indexOfLastElement = list.size() - 1;
        return list.remove(indexOfLastElement);
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        } else {
            int indexOfLastElement = list.size() - 1;
            return list.get(indexOfLastElement);
        }
    }

    @Override
    public int getSize() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public boolean isFull() {
        return list.size() == capacity;
    }

    @Override
    public void setMaxSize(int size) {
        capacity = Math.max(size, 1);
        list = new ArrayList<>(capacity);
    }

    @Override
    public void pushAll(Collection<? extends E> src) throws StackException {
        if ((capacity - list.size()) < src.size()) {
            throw new StackException("Не удается добавить все элементы из коллекции-источника, " +
                    "так как при добавлении стэк будет переполнен.");
        }

        list.addAll(src);
    }

    @Override
    public void popAll(Collection<? super E> dst) throws StackException {
        if (list.isEmpty()) {
            throw new StackException("Невозможно произвести перемещение элементов в коллекцию-приемник, " +
                    "так как стэк пуст.");
        }

        List<E> listReverse = new ArrayList<>(list);
        Collections.reverse(listReverse);
        dst.addAll(listReverse);
        list.clear();
    }
}
