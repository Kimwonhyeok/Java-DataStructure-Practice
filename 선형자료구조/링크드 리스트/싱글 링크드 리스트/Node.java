package linkedlist;

public class Node<E> {
    private E data;
    public Node next;

    Node() {
        data = null;
        next = null;
    }

    Node(E data) {
        this.data = data;
        next = null;
    }

    public void setData(E data) {
        this.data = data;
    }

    public E getData() {
        return data;
    }
}
