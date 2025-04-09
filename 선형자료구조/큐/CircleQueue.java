package queue;

import java.util.Arrays;

public class CircleQueue<T> implements QueueInterface<T> {
    private T[] queue;
    private int front, rear;
    private int count, size;

    /**
     * 큐 기본 사이즈 10
     */
    CircleQueue() {
        this(10);
    }


    /**
     * @param size 큐 사이즈 지정 변수
     */
    CircleQueue(int size) {
        try {
            queue = (T[]) new Object[size];
            this.size = size;
        } catch (OutOfMemoryError oome) {
            System.out.println("메모리 부족");
        }
    }

    /**
     * 사용자 예외 큐가 빈 상태
     */
    private static class UnderFlow extends RuntimeException {
        public UnderFlow() throws RuntimeException {
            super("큐가 비어있는 상태");
        }
    }

    /**
     * 사용자 예외 큐가 가득 찬 상태
     */
    private static class OverFlow extends RuntimeException {
        public OverFlow() throws RuntimeException {
            super("큐가 꽉찬 상태");
        }
    }

    /**
     * 순환 구조 시계 방향 (rear+1)%size , count 기반
     *
     * @param item 큐에 추가할 요소
     * @throws OverFlow 큐 가득찬 상태 경우 예외 발생
     */
    @Override
    public void enQueue(T item) throws OverFlow {
        if (isFull()) {
            throw new OverFlow();
        }
        queue[rear] = item;
        rear = (rear + 1) % size;
        count++;

        System.out.println(Arrays.toString(queue));
    }

    /**
     * 순환 구조 시계 방향 (front+1)%front , count 기반
     *
     * @throws UnderFlow 큐가 빈 상태일 때 예외 발생
     */
    @Override
    public void deQueue() throws UnderFlow {
        if (isEmpty()) {
            throw new UnderFlow();
        }
        System.out.println("꺼낸 값: " + queue[front]);
        front = (front + 1) % size;
        count--;

        System.out.println(Arrays.toString(queue));
    }

    /**
     * 큐 front에 존재하는 값 출력
     *
     * @throws UnderFlow 큐가 빈 상태일 때 예외 바생
     */
    @Override
    public void peek() throws UnderFlow {
        if (isEmpty()) {
            throw new UnderFlow();
        }
        System.out.println(queue[front]);
    }

    /**
     * count기반 isEmpty 체크 count가 0보다 작거나 크면 큐에 아무것도 없는 상태
     * front==rear 확인은 count기반이 아닌 큐 1자리 남기고 포화상태 체크할 때 사용하는 조건
     *
     * @return count<= 0 인 경우 false 반환
     */
    @Override
    public boolean isEmpty() {
        //return front==rear;
        return count <= 0;
    }

    /**
     * count기반 isFull 체크 count가 size와 크거나 같으면 큐가 꽉 찬 상태이다.
     * count기반 사용하기 싫다면 주석부분 삭제 후 사용
     * 큐의 한 자리 남기고 포화상태 체크하는 알고리즘 (rear+1)%size == front인 경우 한자리를 제외한 rear==front이므로 포화
     *
     * @return count>=size 일 경우 true 반환
     */
    @Override
    public boolean isFull() {
        //return ((rear+1)%size)==front; //마지막 자리 남기고 isFull 확인 가능
        return count >= size;
    }

    /**
     * 순차 탐색으로 배열안에 값 찾는 메서드
     *
     * @param key 메서드 호출 시 인수로 넘어올 값을 큐에 존재하는지 순차 탐색한다.
     */
    @Override
    public void indexOf(T key) {
        int copyFront = front;
        for (int i = 0; i < count; i++) {
            if (key.equals(queue[copyFront])) {
                System.out.println("해당 " + key + " 는 index queue[" + copyFront + "]에 있습니다.");
                return;
            }
            copyFront = (copyFront + 1) % size;
        }
    }

    /**
     * 큐를 초기화하는 메서드 rear, front, count도 모두 0으로 값을 바꾼다.
     */
    @Override
    public void clear() {
        queue = (T[]) new Object[size];
        front = 0;
        rear = 0;
        count = 0;
    }
}
