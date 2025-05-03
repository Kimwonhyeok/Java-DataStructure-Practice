package deque;

/**
 * 2025-05-03
 * 순환 배열 구조 덱 인터페이스
 *
 * @param <T>
 */

public interface DequeInterface<T> {
    public boolean isFull();

    public boolean isEmpty();

    /**
     * 큐에는 없던 기능.
     * 덱 전단부 데이터 삽입
     *
     * @param item T타입 데이터
     */
    public void addFront(T item);

    /**
     * 전단부 데이터 삭제   (dequeue)
     */
    public void getFront();

    /**
     * 전단부 데이터 조회 (삭제 연산은 안함)
     */
    public void peekFront();

    /**
     * 후단부 데이터 삽입(enqueue)
     *
     * @param item T타입 데이터
     */
    public void addRear(T item);

    /**
     * 후단부 데이터 삭제 큐에 없던 기능
     */
    public void getRear();

    /**
     * 후단부 데이터 조회
     */
    public void peekRear();
}
