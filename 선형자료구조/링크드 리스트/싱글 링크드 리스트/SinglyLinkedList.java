package linkedlist;

import list.ListI;

/**
 * Singly LinkedList 구현
 *
 * @param <E> 제네릭 객체 사용 (객체만 저장 가능)
 */
public class SinglyLinkedList<E> implements ListI<E> {

    private int size;
    private Node<E> head;
    private Node<E> tail;


    /**
     * 기본생성자.
     */
    SinglyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }


    /**
     * 특정 index의 노드 반환하는 메서드
     * <strong>찾는 과정</strong><br>
     * 1. index가 0미만, size보다 같거나 크면 예외 발생 <br>
     * 2. head 노드를 시작으로 반복문을 통해 index 값까지 순차적으로 탐색<br>
     * O(N)
     *
     * @param index 찾고자하는 index값
     * @return 예외 발생 안했으면 해당 index값의 node 반환
     * @throws IndexOutOfBoundsException index 접근 잘못된 경우
     */
    private Node<E> search(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        Node<E> node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }


    /**
     * 리스트 맨 앞에 요소를 추가,
     * 처음 추가한 요소는 링크값(연결된 노드값)은 null이다. head가 null이므로
     *
     * @param element 새롭게 추가되는 값
     */
    @Override
    public void addFirst(E element) {
        Node<E> node = new Node<>(element);
        node.next = head;
        head = node;
        if (size == 0) {
            tail = node;
        }
        size++;
    }


    /**
     * 리스트 특정 index부분에 요소를 추가하는 메서드
     * 특정 index 이 전 노드는 새로운 노드와 연결하고 새로운 노드는 특정 index 이 후 노드와 연결
     *
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
        Node<E> prevNode = search(index - 1);
        Node<E> nextNode = prevNode.next;
        Node<E> newNode = new Node<E>(element);
        prevNode.next = newNode;
        newNode.next = nextNode;
        size++;
    }


    /**
     * add method는 기본적으로 addLast 호출
     * Java 공식적인 LinkedList도 맨 뒤 요소 추가가 기본이다.
     *
     * @param element list에 삽입할 값
     */
    @Override
    public void add(E element) {
        addLast(element);
    }


    /**
     * 리스트에서 추가할 수 있는 맨 마지막 부분에 요소를 추가한다.
     *
     * @param element 새롭게 추가되는 값
     */
    @Override
    public void addLast(E element) {
        if (size == 0) {
            addFirst(element);
            return;
        }
        Node<E> node = new Node<>(element);
        tail.next = node;
        tail = node;
        size++;
    }


    /**
     * 찾고자하는 element 값을 index0~n까지 순차적으로 검색하는 메서드
     *
     * @param element 찾고자 하는 값
     * @return -element 찾았을 경우 index값 반환, 못 찾을 경우 -1 반환
     */
    @Override
    public int indexOf(E element) {
        Node<E> headNode = head;
        for (int i = 0; i < size; i++, headNode = headNode.next) {
            if (element.equals(headNode.getData())) {
                return i;
            }
        }
        return -1;
    }


    /**
     * 맨 앞 head가 가리키는 노드를 삭제하는 메서드이다.
     *
     * @return 삭제된 data값
     */
    public E remove() {
        if (size <= 0) {
            return null;
        }

        Node<E> headNode = head;
        Node<E> headNextNode = headNode.next;
        E returnData = headNode.getData();
        head = headNextNode;

        size--;
        if (size == 0) {
            tail = null;
        }
        return returnData;
    }


    /**
     * 리스트의 특정 index의 요소를 삭제 후 요소의 데이터값 반환하는 메서드이다.
     *
     * @param index 제거하고자 하는 특정 index값
     * @return index==0 일 시 기본 remove() 호출 삭제한 데이터 값 반환
     * @throws IndexOutOfBoundsException
     */
    @Override
    public E remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        E returnData = null;
        if (index == 0) {
            return remove();
        }

        Node<E> prevNode = search(index - 1);
        Node<E> deleteNode = prevNode.next;

        prevNode.next = deleteNode.next;
        returnData = deleteNode.getData();
        deleteNode.next = null;
        size--;

        if (prevNode.next == null) {
            tail = prevNode;
        }

        return returnData;
    }


    /**
     * 리스트의 특정 data값과 일치하는 요소를 삭제하는 메서드이다.
     *
     * @param element 삭제하고자 하는 데이터 값
     * @return 삭제한 값 반환
     */
    @Override
    public E remove(E element) {
        if (isEmpty()) {
            System.out.println("리스트가 빈 상태이다.");
            return null;
        }
        int index = 0;
        Node<E> node = head;
        while (node != null) {
            if (node.getData().equals(element)) {
                break;
            }
            node = node.next;
            index++;
        }
        if (index >= size) {
            System.out.println(element + "는 리스트에 존재하지 않습니다.");
            return null;
        }
        if (node.equals(head)) {
            return remove();
        }

        Node<E> prevNode = search(index - 1);
        prevNode.next = node.next;
        E data = node.getData();
        node.next = null;
        node.setData(null);
        System.out.println("삭제한 값 " + data);
        size--;
        if (size == 1) {
            tail = prevNode;
        }
        return data;
    }


    /**
     * size는 add, remove 기능 수행할 때마다 size값을 업데이트한다.
     * 이 때 size값이 0보다 작거나 같다면 리스트에 노드가 하나도 없는 상태이므로
     * 공백상태이다.
     *
     * @return size<= 0 이 true이면 공백 false이면 공백상태가 아님
     */
    @Override
    public boolean isEmpty() {
        return size <= 0;
    }


    /**
     * 동적 할당이므로 포화 상태는 존재하지 않는다.
     * 메모리 누수일 시 포화이다.
     *
     * @return 기본 false
     */
    @Override
    public boolean isFull() {
        return false;
    }


    /**
     * 리스트의 특정 위치의 노드의 데이터 값을 반환하는 메서드이다.
     *
     * @param index 찾을 위치 index
     * @return
     * @throws IndexOutOfBoundsException
     */
    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> searchNode = search(index);
        System.out.println(index + "번째에 꺼낸 데이터: " + searchNode.getData());
        return searchNode.getData();
    }


    /**
     * 리스트의 특정 위치의 노드의 데이터 값을 parameter 값으로 변경하는 메서드이다.
     * 리스트의 특정 위치의 노드 요소값 변경 기능.
     *
     * @param index   바꿀려는 index 위치 값
     * @param element 바뀌어지는 값
     * @throws IndexOutOfBoundsException
     */
    @Override
    public void set(int index, E element) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        Node<E> node = search(index);
        node.setData(element);
    }


    /**
     * @param element
     * @return
     */
    @Override
    public boolean contains(E element) {
        Node<E> headNode = head;

        while (headNode != null) {
            if (headNode.getData().equals(element)) {
                return true;
            }
            headNode = headNode.next;
        }
        return false;
    }


    /**
     * 리스트에 존재하는 노드의 개수 반환하는 메서드
     *
     * @return 노드의 개수
     */
    @Override
    public int size() {
        return size;
    }


    /**
     * 리스트의 총 용량을 반환하는 메서드 연결된 구조에서는
     * 총 용량은 제한되지 않는다.
     *
     * @return
     */
    @Override
    public int capacity() {
        return size;
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
                sb.append(headNode.getData() + ",");
            } else {
                sb.append(headNode.getData() + "]");
            }
            headNode = headNode.next;
        }
        return sb;
    }
}
