package ua.edu.ucu.collections.immutable;

public class Node {
    private final Object value;
    private Node next;
    private Node prev;
    public Node(Object value) {
        this.value = value;
    }

    public Node getNext() {
        return next;
    }

    public Node getPrev() {
        return prev;
    }

    public void setNext(Node setNext) {
        this.next = setNext;
    }

    public void setPrev(Node setPrev) {
        this.prev = setPrev;
    }

    public Object getValue() {
        return value;
    }

}
