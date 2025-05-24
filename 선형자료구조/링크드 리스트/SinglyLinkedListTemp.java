import list.List;

public class LinkedList<E> implements List<E> {

    private Node<E> tail;
    private Node<E> head;
    private int size;

    /**
     * Default Constructure
     */
    LinkedList() {
        this.tail = null;
        this.head = null;
        this.size = 0;
    }

    /**
     * head 노드부터 매개변수 index까지 순차 탐색을 한다.
     *
     * @param index 찾고자하는 노드의 index값
     * @return 검색 수행 결과 반환되는 노드(index에 존재하는 노드)
     * @throws IndexOutOfBoundsException index를 잘 못 접근했을 시 예외 발생
     */
    public Node search(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> node = head;

        for (int i = 0; i < index; i++) {
            node = node.link;
        }
        return node;
    }

    /**
     * @param element 새롭게 추가되는 값
     */
    @Override
    public void addFirst(E element) {
        Node<E> node = new Node<E>(element);
        node.link = head;
        head = node;

        if (head.link == null) {
            tail = node;
        }
        size++;
    }

    /**
     * @param index   특정 index에 접근하는 변수
     * @param element 특정 index에 넣을 값
     */
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
        Node<E> prevNode = search(index-1);
        Node<E> nextNode = prevNode.link;

        Node<E> node = new Node<E>(element);
        prevNode.link = null;
        prevNode.link = node;
        node.link = nextNode;
        size++;
    }


    /**
     * @param element list에 삽입할 값
     */
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
        Node<E> node = new Node<E>(element);
        tail.link = node;
        tail = node;
        size++;
    }


    /**
     * parameter로 들어온 element값이 존재하면
     * 존재하는 위치의 index값 반환하는 메서드
     * @param element 찾고자 하는 값
     * @return index값 반환, 못 찾을 시 -1 반환
     */
    @Override
    public int indexOf(E element) {
        if (size == 0) {
            System.out.println("리스트에 노드가 하나도 없습니다.");
            return -1;
        }
        int index = 0;
        for (Node<E> node = head; node.link != null; node = node.link) {
            if (node.data == element) {
                return index;
            }
            index++;
        }
        return -1;
    }



    @Override
    public E remove(int index)throws IndexOutOfBoundsException {
        if(index<0||index>=size){
            throw new IndexOutOfBoundsException();
        }
        if(index==0){
            return remove();
        }
        Node<E> removeNode = search(index);
        Node<E> removePrevNode = search(index-1);
        Node<E> nextNode = search(index+1);

        E deleteData = removeNode.data;

        removePrevNode.link = nextNode;

        if(removeNode.link==null){
            tail = removeNode;
        }

        removeNode.link = null;
        removeNode.data = null;
        size--;
        return deleteData;
    }



    public E remove(){
        if(head==null){
            System.out.println("리스트에 노드가 하나도 없습니다.");
            return null;
        }
        Node<E> headNode = head;
        Node<E> headNextNode = headNode.link;
        E data = headNode.data;
        System.out.println("삭제한 값: "+data);
        headNode.data = null;
        headNode.link = null;

        head = headNextNode;
        size--;
        if(size==0){
            tail = null;
        }
        return data;
    }



    /**
     *
     * @return 공백 상태일 시 true, 공백 상태 아니면 false
     */
    @Override
    public boolean isEmpty() {
        return size<=0;
    }


    /**
     * 동적 할당이므로 포화 상태는 메모리 누수 상태아니면 대부분의 경우 포화 상태가 안 발생한다.
     * @return 기본값으로 false반환
     */
    @Override
    public boolean isFull() {
        return false;
    }


    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public void set(int index, E element) {

    }

    @Override
    public boolean contains(E element) {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public int capacity() {
        return 0;
    }



    public String toArray(){
        Object[] arr = new Object[size];
        Node<E> headNode = head;

        int index = 0;
        while(headNode!=null){
            arr[index] = headNode.data;
            headNode = headNode.link;
            index++;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(int i=0; i<arr.length; i++){
            if(i!=size-1){
                sb.append(arr[i]+",");
            }else{
                sb.append(arr[i]+"]");
            }

        }
        return sb.toString();
    }
}



/**
*
*
*/
package list;

public interface List<E> {

    /**
     * List 맨 앞에 요소를 추가합니다. (append)
     *
     * @param element 새롭게 추가되는 값
     */
    public void addFirst(E element);

    /**
     * List 특정 index에 요소를 추가합니다.
     *
     * @param index   특정 index에 접근하는 변수
     * @param element 특정 index에 넣을 값
     */
    public void add(int index, E element);


    /**
     * List에 요소 추가 기본적으로 append 맨 뒤에 추가.
     *
     * @param element list에 삽입할 값
     */
    public void add(E element);


    /**
     * List 맨 마지막에 요소를 추가합니다.
     *
     * @param element 새롭게 추가되는 값
     */
    public void addLast(E element);


    /**
     * 특정 요소가 몇 번째 index에 존재하는지 반환하는 메서드
     *
     * @param element 찾고자 하는 값
     * @return 찾았을 때 index값 반환
     * 없다면 -1 반환
     */
    public int indexOf(E element);


    /**
     * 특정 index에 요소 제거
     *
     * @param index 제거하고자 하는 특정 index값
     * @return 제거한 값 반환
     */
    public E remove(int index);

    /**
     * 리스트가 공백 상태인지 확인하는 메서드
     *
     * @return 공백일 시 true 공백이 아닐 시 false
     */
    public boolean isEmpty();

    /**
     * 리스트가 포화 상태인지 확인하는 메서드
     *
     * @return 포화일 시 true 포화가 아닐 시 false
     */
    public boolean isFull();



    /**
     * @param index
     * @return
     */
    public E get(int index);

    /**
     * 특정 index의 요소를 매개변수 요소로 변경하는 메서드
     *
     * @param index   바꿀려는 index 위치 값
     * @param element 바뀌어지는 값
     */
    public void set(int index, E element);

    /**
     * @param element
     * @return element값이 리스트에 존재하면 true 아니면 false
     */
    public boolean contains(E element);

    /**
     * 리스트에 존재하는 요소의 개수 반환
     *
     * @return 리스트의 총 용량
     */
    public int size();

    /**
     * 리스트의 총 용량 반환.
     *
     * @return 리스트의 총 용량
     */
    public int capacity();
}











//main

public class SinglyLinkedListMain {
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();

        list.addFirst(8000);
        System.out.println(list.toArray());
        list.add(1,8);
        System.out.println(list.toArray());
        list.add(1,5);
        System.out.println(list.toArray());
        list.remove(1);
        System.out.println(list.toArray());

    }
}
