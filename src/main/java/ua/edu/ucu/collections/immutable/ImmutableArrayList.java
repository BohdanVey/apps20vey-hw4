package ua.edu.ucu.collections.immutable;

import java.util.Arrays;

public class ImmutableArrayList implements ImmutableList {
    private Object[] elements;
    private int len = 0;

    public ImmutableArrayList() {
        elements = new Object[0];
    }

    private ImmutableArrayList(int length) {
        elements = new Object[length];
        len = length;
    }

    public ImmutableArrayList(Object[] list) {
        elements = list.clone();
        len = elements.length;
    }


    private void checkBounds(int index) {
        if (index < 0 || index > len) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void checkBoundsBig(int index) {
        if (index < 0 || index >= len) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public ImmutableList add(Object e) {
        return add(len, e);
    }

    @Override
    public ImmutableList add(int index, Object e) {
        return addAll(index, new Object[]{e});
    }

    @Override
    public ImmutableList addAll(Object[] c) {
        return addAll(len, c);

    }

    @Override
    public ImmutableList addAll(int index, Object[] c) {
        checkBounds(index);
        ImmutableArrayList copy = new ImmutableArrayList(c.length + len);
        System.arraycopy(elements, 0, copy.elements, 0, index);
        System.arraycopy(c, 0, copy.elements, index, c.length);
        int from = index + c.length;
        int size = elements.length - index;
        System.arraycopy(elements, index, copy.elements, from, size);
        return copy;
    }


    @Override
    public Object get(int index) {
        checkBoundsBig(index);
        return elements[index];
    }

    @Override
    public ImmutableList remove(int index) {
        checkBoundsBig(index);
        ImmutableArrayList copy = new ImmutableArrayList(len - 1);
        System.arraycopy(elements, 0, copy.elements, 0, index);
        for (int i = index + 1; i < len; i += 1) {
            copy.elements[i - 1] = elements[i];
        }
        return copy;
    }

    @Override
    public ImmutableList set(int index, Object e) {
        checkBoundsBig(index);
        ImmutableArrayList copy = new ImmutableArrayList(elements);
        copy.elements[index] = e;
        return copy;
    }

    @Override
    public int indexOf(Object e) {
        for (int i = 0; i < len; i += 1) {
            if (get(i).equals(e)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return len;
    }

    @Override
    public ImmutableList clear() {
        return new ImmutableArrayList();
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public Object[] toArray() {
        return elements.clone();
    }

    @Override
    public String toString() {
        String str = Arrays.toString(elements);
        return str.substring(1, str.length() - 1);
    }
}
