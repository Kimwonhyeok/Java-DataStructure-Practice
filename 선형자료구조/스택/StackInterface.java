package stack;

public interface StackInterface {
    public void push(int value);

    public void pop();

    public void peek();

    public int indexOf(int value);

    public void clear();

    public void capacity();

    public void size();

    public boolean isEmpty();

    public boolean isFull();

    public void dump();
}
