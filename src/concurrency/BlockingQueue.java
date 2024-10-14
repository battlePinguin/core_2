package concurrency;

public class BlockingQueue<T> {
    private final T[] queue;
    private int indexToTake = 0;
    private int indexToPut = 0;
    private int count = 0;

    public BlockingQueue(int capacity) {
        queue = (T[]) new Object[capacity];
    }

    public synchronized void enqueue(T element) throws InterruptedException {
        while (count == queue.length) {
            wait();
        }

        queue[indexToPut] = element;
        indexToPut = (indexToPut + 1) % queue.length;
        count++;

        notifyAll();
    }

    public synchronized T dequeue() throws InterruptedException {
        while (count == 0) {
            wait();
        }

        T element = queue[indexToTake];
        queue[indexToTake] = null;
        indexToTake = (indexToTake + 1) % queue.length;
        count--;


        notifyAll();

        return element;
    }

    public synchronized int size() {
        return count;
    }
}
