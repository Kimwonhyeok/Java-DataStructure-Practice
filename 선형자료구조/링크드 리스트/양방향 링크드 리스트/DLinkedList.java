package dlinkedlist;

import list.ListI;

import java.util.NoSuchElementException;

/**
 * 양방향 구조의 연결된 리스트
 *
 * @param <E>
 */
public class DLinkedList<E> implements ListI<E> {

    private Node<E> head;
    private Node<E> tail;
    private int size;

    public DLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * SinglyLinkedList와 달리 양방향으로 연결된 구조이므로 검색 과정이 더 효율적이다.
     * index 값이 tail에 가까우면 리스트 뒤에서부터 조회
     * index 값이 head에 가까우면 리스트 앞에서부터 조회
     *
     * @param index 찾고자하는 리스트의 위치
     * @return 검색 성공 시 Node 객체 반환, 검색 실패 시 null 반환
     * @throws IndexOutOfBoundsException index 잘못 접근핳 때 예외 (index음수이거나, 리스트보다 크면)
     */
    private Node<E> search(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        //뒤에서부터 조회
        if (index + 1 > size / 2) {
            Node<E> node = tail;

            for (int i = size - 1; i > index; i--) {
                node = node.prev;
            }
            return node;
        } else {
            Node<E> node = head;

            for (int i = 0; i < index; i++) {
                node = node.link;
            }
            return node;
        }

    }

    @Override
    public void addFirst(E element) {
        Node<E> node = new Node<>(element);
        node.link = head;
        node.prev = null;

        if (head != null) {
            head.prev = node;
        }

        head = node;

        if (size == 0) {
            tail = node;
        }

        size++;

    }

    @Override
    public void add(int index, E element) throws IndexOutOfBoundsException {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        if (index == 0) {
            addFirst(element);
            return;
        }

        if (index == size) {
            addLast(element);
            return;
        }

        Node<E> newnode = new Node<>(element);

        Node<E> prevNode = search(index - 1);
        Node<E> nextNode = prevNode.link;

        prevNode.link = newnode;
        nextNode.prev = newnode;

        newnode.prev = prevNode;
        newnode.link = nextNode;

        size++;
    }

    @Override
    public void add(E element) {
        addLast(element);
    }

    @Override
    public void addLast(E element) {
        if (size == 0) {
            addFirst(element);
            return;
        }
        Node<E> newNode = new Node<>(element);
        tail.link = newNode;
        newNode.prev = tail;
        tail = newNode;
        size++;
    }

    public E remove()throws NoSuchElementException {
        if (head == null) {
            throw new NoSuchElementException();
        }
        E data = head.data;
        Node<E> headNextNode = head.link;

        if (headNextNode != null) {
            headNextNode.prev = null;
        }
        head.data = null;
        head.link = null;
        head = headNextNode;
        size--;

        if (size == 0) {
            tail = null;
        }
        return data;
    }

    @Override
    public E remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        if (index == 0) {
            return remove();
        }

        Node<E> deleteNode = search(index);
        Node<E> prevNode = deleteNode.prev;
        Node<E> nextNode = deleteNode.link;

        E data = deleteNode.data;

        if (nextNode != null) {
            prevNode.link = nextNode;
            nextNode.prev = prevNode;
        } else {
            tail = prevNode;
        }
        deleteNode.link = null;
        deleteNode.prev = null;
        deleteNode.data = null;

        size--;

        return data;
    }


    @Override
    public E remove(E element)throws NoSuchElementException {
        if(size==0){
            throw new NoSuchElementException();
        }

        Node<E> removeNode = head;

        while(removeNode !=null){
            if(removeNode.data.equals(element)){
                break;
            }
            removeNode = removeNode.link;
        }

        if(removeNode==null){
            throw new NoSuchElementException();
        }

        if(removeNode.equals(head)){
            return remove();
        }
        E data = removeNode.data;
        Node<E> nextNode = removeNode.link;
        Node<E> prevNode = removeNode.prev;

        if(nextNode!=null){
            prevNode.link = nextNode;
            nextNode.prev = prevNode;
        }else{
            tail = prevNode;
        }
        removeNode.prev = null;
        removeNode.link = null;
        removeNode.data = null;
        size--;
        if(size==0){
            tail = null;
        }
        return data;
    }

    @Override
    public boolean isEmpty() {
        return size <= 0;
    }

    @Override
    public boolean isFull() {
        return false;
    }


    @Override
    public E get(int index)throws IndexOutOfBoundsException {
        if(index<0 || index>=size){
            throw new IndexOutOfBoundsException();
        }
        return search(index).data;
    }


    @Override
    public void set(int index, E element)throws IndexOutOfBoundsException {
        if(index<0||index>=size){
            throw new IndexOutOfBoundsException();
        }

        Node<E> node = search(index);
        node.data = element;
    }


    @Override
    public int indexOf(E element)throws NoSuchElementException {
        if(size==0){
            throw new NoSuchElementException();
        }
        Node<E> headNode = head;
        int index = 0;
        while(headNode!=null){
            if(headNode.data.equals(element)){
                break;
            }
            index++;
            headNode = headNode.link;
        }

        if(index==size){
            return -1;
        }else{
            return index;
        }

    }


    @Override
    public boolean contains(E element)throws NoSuchElementException {
        if(size==0){
            throw new NoSuchElementException();
        }
        Node<E> headNode = head;
        boolean isCheck = false; //찾으면 true
        while(headNode!=null){
            if(headNode.data.equals(element)){
                isCheck = true;
                break;
            }
            headNode = headNode.link;
        }

        return isCheck;
    }


    @Override
    public int size() {
        return size;
    }


    @Override
    public int capacity() {
        return Integer.MAX_VALUE;
    }


    /**
     * 리스트의 데이터들을 Object[] 배열에 담아 문자열로 반환하는 메서드이다.
     * 리스트의 상태를 보기 쉽게 하기 위한 메서드이다.
     *
     * @return 가변 스트링을 반환한다.
     */
    public StringBuilder toArray() {
        Object[] arr = new Object[size];
        StringBuilder sb = new StringBuilder().append("[");
        Node<E> headNode = head;
        for (int i = 0; i < size; i++) {
            if (i != size - 1) {
                sb.append(headNode.data + ",");
            } else {
                sb.append(headNode.data + "]");
            }
            headNode = headNode.link;
        }
        return sb;
    }
}
