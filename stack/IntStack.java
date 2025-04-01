package stack;

import java.util.Arrays;

public class IntStack implements StackInterface {
    private int[] stackArr;
    private int ptr;
    private int max;

    //스택 기본 사이즈 10
    public IntStack() {
        this(10);
    }

    public IntStack(int max) {
        this.max = max;
        ptr = 0;
        try {
            stackArr = new int[max];
        } catch (OutOfMemoryError oome) {
            this.max = 0;
        }
    }

    public class EmptyStackException extends RuntimeException {
        EmptyStackException() {
            super("스택이 비어있습니다.");
        }
    }

    public class FullStackException extends RuntimeException {
        FullStackException() {
            super("스택이 꽉 찼습니다.");
        }
    }

    //push(int value)
    //매개변수 value를 스택에 넣는 메서드
    @Override
    public void push(int value) throws FullStackException {
        if (isFull()) {
            throw new FullStackException();
        }
        stackArr[ptr++] = value;
        System.out.println("스택상황: " + Arrays.toString(stackArr));
    }

    //pop()
    //제일 마지막에 들어온 값을 출력하는 메서드
    @Override
    public void pop() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        System.out.println("꺼낸값: " + stackArr[--ptr]);
        System.out.println("스택상황: " + Arrays.toString(stackArr));
    }


    //스택 제일 상단에 있는 값 출력 메서드
    @Override
    public void peek() {
        if (isEmpty()) {
            System.out.println("스택이 비어있습니다.");
            return;
        }
        System.out.println("제일 상단 값: " + stackArr[ptr - 1]);
    }

    //value 매개변수와 같은 값이 스택에 존재하는지 찾는 메서드
    //찾는 과정은 선형검색으로 O(n)
    @Override
    public int indexOf(int value) {
        if (isEmpty()) {
            System.out.println("스택이 비어있습니다.");
            return -1;
        }
        for (int i = 0; i < ptr; i++) {
            if (value == stackArr[i]) {
                System.out.println(value + "값은  stackArr[" + i + "] 에 존재합니다.");
                return i;
            }
        }
        System.out.println(value + "값은 스택에 존재하지 않습니다.");
        return -1;
    }


    //스택을 초기화 시킵니다. 스택 배열 재할당
    @Override
    public void clear() {
        stackArr = new int[max];
        ptr = 0;
    }

    //스택의 총 용량을 출력하는 메서드
    @Override
    public void capacity() {
        System.out.println("스택의 총 크기: " + max);
    }

    //현재 스택이 가지고 있는 요소들의 개수 출력(기본값 제외)
    @Override
    public void size() {
        System.out.println("현재 스택에 있는 요소 개수: " + (ptr));
    }

    //스택이 비어있는지 확인하는 메서드
    @Override
    public boolean isEmpty() {
        return ptr <= 0;
    }

    //스택이 가득차있는지 확인하는 메서드
    @Override
    public boolean isFull() {
        return max <= ptr;
    }


    //스택의 바닥부터 상단 순서로 출력하는 메서드
    @Override
    public void dump() {
        if (isEmpty()) {
            System.out.println("스택이 비어있습니다.");
            return;
        }
        System.out.print("[");
        for (int i = 0; i < ptr; i++) {
            if (i < ptr - 1) {
                System.out.print(stackArr[i] + ", ");
            } else {
                System.out.print(stackArr[i]);
            }
        }
        System.out.print("]");
        System.out.println();
    }
}
