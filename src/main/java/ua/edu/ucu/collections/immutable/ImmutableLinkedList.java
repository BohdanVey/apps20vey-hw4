package ua.edu.ucu.collections.immutable;


import java.util.Arrays;

public class ImmutableLinkedList implements ImmutableList {
    private Node head;
    private Node tail;
    private int len;


    public ImmutableLinkedList() {
        this.head = null;
        this.tail = null;
    }

    public ImmutableLinkedList(Object[] source) {
        this();
        this.head = new Node(source[0]);
        Node now = this.head;
        for (int i = 1; i < source.length; i += 1) {
            Node next = new Node(source[i]);
            next.setPrev(now);
            now.setNext(next);
            now = next;
        }
        len = source.length;
        this.tail = now;

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
        if (head == null) {
            return new ImmutableLinkedList(c);
        }
        ImmutableLinkedList copy = new ImmutableLinkedList(this.toArray());
        Node next;
        if (index != len) {
            next = copy.getNode(index);
        } else {
            next = null;
        }
        Node prev;
        if (next != null) {
            prev = next.getPrev();
        } else {
            prev = copy.tail;
        }
        Node first = new Node(c[0]);
        if (prev != null) {
            prev.setNext(first);
            first.setPrev(prev);
        } else {
            copy.head = first;
        }
        Node now = first;
        for (int i = 1; i < c.length; i += 1) {
            Node newNode = new Node(c[i]);
            now.setNext(newNode);
            newNode.setPrev(now);
            now = newNode;
        }
        if (next != null) {
            next.setPrev(now);
            now.setNext(next);
        } else {
            copy.tail = now;
        }
        copy.len += c.length;
        return copy;
    }


    @Override
    public Object get(int index) {
        return getNode(index).getValue();
    }

    private Node getNode(int index) {
        checkBoundsBig(index);
        Node now = this.head;
        for (int i = 0; i < index; i++) {
            now = now.getNext();
        }
        return now;
    }


    @Override
    public ImmutableList remove(int index) {
        checkBoundsBig(index);
        ImmutableLinkedList copy = new ImmutableLinkedList(this.toArray());
        Node now = copy.getNode(index);
        if (len == 1) {
            return new ImmutableLinkedList();
        }
        if (index == 0) {
            now.getNext().setPrev(now);
            copy.head = now.getNext();
        } else if (index == copy.len - 1) {
            now.getPrev().setNext(now);
            copy.tail = now.getPrev();
        } else {
            now.getPrev().setNext(now.getNext());
            now.getNext().setPrev(now.getPrev());
        }
        copy.len -= 1;
        return copy;
    }

    @Override
    public ImmutableList set(int index, Object e) {
        checkBoundsBig(index);
        Object[] res = this.toArray();
        res[index] = e;
        return new ImmutableLinkedList(res);
    }

    @Override
    public int indexOf(Object e) {
        Node now = head;
        for (int i = 0; i < len; i++) {
            if (now.getValue().equals(e)) {
                return i;
            }
            now = now.getNext();
        }
        return -1;
    }

    @Override
    public int size() {
        return len;
    }

    @Override
    public ImmutableList clear() {
        return new ImmutableLinkedList();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[len];
        Node curNode = head;
        for (int i = 0; i < len; i += 1) {
            result[i] = curNode.getValue();
            curNode = curNode.getNext();
        }
        return result;
    }


    @Override
    public String toString() {
        String str = Arrays.toString(this.toArray());
        return str.substring(1, str.length() - 1);
    }

    public ImmutableLinkedList addFirst(Object e) {
        return (ImmutableLinkedList) add(0, e);
    }

    public ImmutableLinkedList addLast(Object e) {
        return (ImmutableLinkedList) add(e);
    }

    public Object getFirst() {
        return get(0);
    }

    public Object getLast() {
        return get(len - 1);
    }

    public ImmutableLinkedList removeFirst() {
        return (ImmutableLinkedList) remove(0);
    }

    public ImmutableLinkedList removeLast() {
        return (ImmutableLinkedList) remove(len - 1);
    }


}
