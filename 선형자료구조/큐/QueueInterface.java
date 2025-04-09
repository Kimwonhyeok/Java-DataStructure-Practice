package queue;

/*
  enpueue(Object o)
  depueue()
  peek()
  isEmpty()
  isFull()
  indexOf()
  clear()
 */

public interface QueueInterface<T> {
    public void enQueue(T item);
    public void deQueue();
    public void peek();
    public boolean isEmpty();
    public boolean isFull();
    public void indexOf(T key);
    public void clear();
}
