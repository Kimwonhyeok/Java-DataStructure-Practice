package deque;

import java.util.Arrays;

public class Deque<T> implements DequeInterface<T> {
    private int front;
    private int rear;
    private int size;
    private int count;  //count기반 isFull isEmpty 확인 시 사용할 변수.

    private T[] arr;

    public Deque() {
        this(10);
    }

    public Deque(int size) {
        try {
            front = 0;
            rear = 0;
            count = 0;
            this.size = size;
            arr = (T[]) (new Object[size]);
        } catch (OutOfMemoryError oome) {
            oome.printStackTrace();
            System.exit(1);
        }
    }

    @Override
    public boolean isFull() {
        return (rear + 1) % size == front;
        //return count>=size;
    }

    @Override
    public boolean isEmpty() {
        return rear == front;
        //return count==0
    }

    @Override
    public void addFront(T item) throws OverFlowException {
        if (isFull()) {
            throw new OverFlowException();
        } else {
            front = (front - 1 + size) % size;
            arr[front] = item;
            System.out.println(Arrays.toString(arr));
            System.out.println("front=" + front);
        }
    }

    @Override
    public void getFront() throws UnderFlowException {
        if (isEmpty()) {
            throw new UnderFlowException();
        } else {
            T returnValue = arr[front];
            System.out.println("front(" + front + ")  꺼낸값: " + returnValue);
            arr[front] = null;
            front = (front + 1) % size;
        }
    }

    @Override
    public void peekFront() throws UnderFlowException {
        if (isEmpty()) {
            throw new UnderFlowException();
        }
        System.out.println("peek연산 수행: front[" + front + "] 값: " + arr[front]);
    }

    @Override
    public void addRear(T item) throws OverFlowException {
        if (isFull()) {
            throw new OverFlowException();
        } else {
            arr[rear] = item;
            System.out.println(Arrays.toString(arr));
            rear = (rear + 1) % size;
            System.out.println("rear= " + rear);
        }
    }

    @Override
    public void getRear() throws UnderFlowException {
        if (isEmpty()) {
            throw new UnderFlowException();
        } else {
            rear = (rear - 1 + size) % size;
            System.out.println("rear(" + rear + ") 꺼낸값: " + arr[rear]);
            System.out.println("rear=" + rear);
            arr[rear] = null;
        }
    }

    @Override
    public void peekRear() throws UnderFlowException {
        if (isEmpty()) {
            throw new UnderFlowException();
        } else {
            int getRearIndex = (rear - 1 + size) % size;
            System.out.println("peek연산 수행: rear[" + getRearIndex + "] 값: " + arr[getRearIndex]);
        }
    }

    @Override
    public int size() {
        return (rear - front + size) % size;
    }

    @Override
    public void contains(T item) throws UnderFlowException {
        if (isEmpty()) {
            throw new UnderFlowException();
        }
        for (int i = front; i != rear; i = (i + 1) % size) {
            if (arr[i] == null) {
                continue;
            }
            if (arr[i].equals((Object) item) || arr[i] == item) {
                System.out.println("덱에 존재합니다.! arr[" + i + "]에 존재");
                return;
            }
        }
        System.out.println(item + "값은 존재하지 않습니다.");
    }

    @Override
    public void clear() {
        Arrays.fill(arr, null);
        front = 0;
        rear = 0;
        size = 0;
    }


    private static class UnderFlowException extends RuntimeException {
        public UnderFlowException() throws RuntimeException {
            super("덱이 비어있는 상태입니다.");
        }
    }

    private static class OverFlowException extends RuntimeException {
        public OverFlowException() throws RuntimeException {
            super("덱이 꽉 찬 상태");
        }
    }


}
