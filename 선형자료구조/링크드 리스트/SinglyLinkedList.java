package linkedlist;

public class SinglyLinkedList {
    private Node head;
    private Node tail;
    private int size;//요소의 개수

    SinglyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }
    

    /**
     * 리스트가 공백상태인지 확인하는 메서드
     *
     * @return size==0은 노드가 한 개도 생성되지 않은 상태.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 동적 할당이므로 꽉 찬 상태 없다고 가정
     *
     * @return false
     */
    public boolean isFull() {
        return false;
    }

    /**
     * search method는 특정 index 노드를 반환하는 메서드이다.
     * O(N)
     *
     * @param index
     * @return
     */
    private Node search(int index) {
        if (index == 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        Node node = head;   //검색은 헤드 노드부터 시작

        for (int i = 0; i < index; i++) {
            node = node.link;
        }

        return node;
    }

    /**
     * 링크드 리스트 맨 앞에 추가
     * 빈 상태의 리스트에 처음 노드 추가하면 그 노드는 link값이 NULL임.
     * head variable는 addFirst로 추가된 노드들 계속 추가
     *
     * @param data Object타입 data
     */
    public void addFirst(Object data) {
        Node node = new Node(data);
        node.link = head;   //만약 addFirst 2번 수행했으면 처음 추가된 맨 앞 노드를 연결
        //addFirst 처음 수행했으면 head null 연결
        head = node;
        size++;
        if (head.link == null) {
            tail = head;
        }
    }

    /**
     * Java에서 제공하는 LinkedList도 addLast가 기본값이므로
     * addLast() 호출
     *
     * @param data
     * @return
     */
    public boolean add(Object data) {
        addLast(data);
        return true;
    }


    /**
     * addLast는 리스트의 맨 끝에 노드 추가
     * tail만 관리하면 된다.
     * size==0일 시 리스트 empty니 addFirst 호출
     * tail.link = new node로 기존 맨 끝 노드 새로운 노드로 연결
     * @param data
     */
    public void addLast(Object data) {
        Node node = new Node(data);
        if (size == 0) {
            addFirst(data);
            return;
        }

        tail.link = node;
        tail = node;
        size++;
    }

    /**
     * index에 맞게 node 추가하는 메서드
     * index==0이면 addFirst로 추가
     * index==size일 시 addLast로 추가
     *
     * @param index
     * @param data
     */
    public void add(int index, Object data) {
        if (index == 0) {
            addFirst(data);
            return;
        }
        if (index == size) {
            addLast(data);
            return;
        }

        //추가할려는 위치 이전 노드 search로 가져오기
        Node prevNode = search(index);

        //삽입하려는 위치에 있던 기존 노드.
        Node nextNode = prevNode.link;

        //새로운 노드
        Node node = new Node(data);

        //추가하려는 위치 이전 노드 연결 먼저 끊기
        prevNode.link = null;
        prevNode.link = node;
        node.link = nextNode;
        size++;
    }


    public void remove() {
        if (head == null) {
            throw new RuntimeException("노드가 하나도 없음");
        }
        Node headNode = head;
        Node nextNode = headNode.link;
        System.out.println("삭제한 값: " + headNode.data);
        headNode.data = null;
        headNode.link = null;

        head = nextNode;
        size--;

        if (size == 0) {
            tail = null;
        }
    }

    public void remove(int index) {
        if (index == 0) {
            remove();
        }

    }
}