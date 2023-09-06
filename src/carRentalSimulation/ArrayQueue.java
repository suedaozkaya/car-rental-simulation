package carRentalSimulation;

public class ArrayQueue <T> implements QueueInterface<T>{

    private T[] queue;
    private int frontIndex;
    private int backIndex;
    private boolean initialized = false;
    private static final int DEFAULT_CAPACITY = 50;
    private static final int MAX_CAPACITY = 10000;

    public ArrayQueue(){
        this(DEFAULT_CAPACITY);
    }

    public ArrayQueue(int initialCapacity){
        @SuppressWarnings("unchecked")
        T[] tempQueue = (T[]) new Object[initialCapacity+1];
        queue = tempQueue;
        initialized = true;
    }

    private void checkInitialization(){
        if (!initialized){
            throw new SecurityException("Queue object is not initialized properly.");
        }
    }

    private void checkCapacity(int newLength) {
        if (newLength>MAX_CAPACITY){
            throw new SecurityException("The capacity exceeds the max capacity.");
        }
    }

    private void ensureCapacity() {
        if (frontIndex == ((backIndex+2) % queue.length)){
            T[] oldQueue = queue;
            int oldSize = oldQueue.length;
            int newSize = oldSize*2;

            checkCapacity(newSize);

            @SuppressWarnings("unchecked")
            T[] tempQueue = (T[]) new Object[newSize];
            queue = tempQueue;

            for (int index = 0; index < oldSize-1; index++) {
                queue[index] = oldQueue[frontIndex];
                frontIndex = (frontIndex+1) % oldSize;
            }

            frontIndex = 0;
            backIndex = oldSize-2;
        }
    }

    @Override
    public void enqueue(T newEntry) {
        checkInitialization();
        ensureCapacity();

        backIndex = (backIndex+1) % queue.length;

        queue[backIndex] = newEntry;
    }

    @Override
    public T dequeue() {
        checkInitialization();
        if (isEmpty()){
            throw new EmptyQueueException();
        }else{
            T front = queue[frontIndex];
            queue[frontIndex] = null;
            frontIndex = (frontIndex+1) % queue.length;
            return front;
        }
    }

    @Override
    public T getFront() {
        checkInitialization();
        if (isEmpty()){
            throw new EmptyQueueException();
        } else
            return queue[frontIndex];
    }

    @Override
    public boolean isEmpty() {
        return frontIndex == ((backIndex+1) % queue.length);
    }

    @Override
    public void clear() {
        while (!isEmpty()){
            dequeue();
        }
    }

}




