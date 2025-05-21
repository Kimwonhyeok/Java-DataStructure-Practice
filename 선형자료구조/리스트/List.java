package list;

public interface ListI<E> {

    /**
     * List 맨 앞에 요소를 추가합니다. (append)
     * @param element 새롭게 추가되는 값
     */
    public void addFirst(E element);

    /**
     * List 특정 index에 요소를 추가합니다.
     * @param index 특정 index에 접근하는 변수
     * @param element 특정 index에 넣을 값
     */
    public void add(int index, E element);


    /**
     * List에 요소 추가 기본적으로 append 맨 뒤에 추가.
     * @param element list에 삽입할 값
     */
    public void add(E element);


    /**
     * List 맨 마지막에 요소를 추가합니다.
     * @param element 새롭게 추가되는 값
     */
    public void addLast(E element);

    /**
     * 특정 요소가 몇 번째 index에 존재하는지 반환하는 메서드
     * @param element 찾고자 하는 값
     * @return 찾았을 때 index값 반환
     *         없다면 -1 반환
     */
    public int indexOf(E element);


    /**
     * 특정 index에 요소 제거
     * @param index 제거하고자 하는 특정 index값
     * @return  제거한 값 반환
     */
    public E remove(int index);

    /**
     * 리스트가 공백 상태인지 확인하는 메서드
     * @return  공백일 시 true 공백이 아닐 시 false
     */
    public boolean isEmpty();

    /**
     * 리스트가 포화 상태인지 확인하는 메서드
     * @return 포화일 시 true 포화가 아닐 시 false
     */
    public boolean isFull();

    /**
     * 
     * @param index
     * @return
     */
    public E get(int index);

    /**
     * 특정 index의 요소를 매개변수 요소로 변경하는 메서드
     * @param index 바꿀려는 index 위치 값
     * @param element 바뀌어지는 값
     */
    public void set(int index, E element);

    /**
     *
     * @param element
     * @return element값이 리스트에 존재하면 true 아니면 false
     */
    public boolean contains(E element);

    /**
     * 리스트에 존재하는 요소의 개수 반환
     * @return 리스트의 총 용량
     */
    public int size();

    /**
     * 리스트의 총 용량 반환.
     * @return 리스트의 총 용량
     */
    public int capacity();
}
