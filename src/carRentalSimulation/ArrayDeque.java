package carRentalSimulation;

public class ArrayDeque<T> implements DequeInterface<T> {
    private T[] deque;
    private int frontIndex;
    private int backIndex;
    private boolean initialized = false;
    private static final int DEFAULT_CAPACITY = 50;
    private static final int MAX_CAPACITY = 10000;

    private int size = 0;


    public ArrayDeque() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayDeque(int initialCapacity) {
        @SuppressWarnings("unchecked")
        T[] tempDeque = (T[]) new Object[initialCapacity];
        deque = tempDeque;
        initialized = true;
    }

    private void checkInitialization() {
        if (!initialized) {
            throw new SecurityException("Deque object is not initialized properly.");
        }
    }

    private void checkCapacity(int newLength) {
        if (newLength > MAX_CAPACITY) {
            throw new SecurityException("The capacity exceeds the max capacity.");
        }
    }
    private void ensureCapacity() {
        if (frontIndex == ((backIndex+2) % deque.length)){
            T[] oldQueue = deque;
            int oldSize = oldQueue.length;
            int newSize = oldSize*2;

            checkCapacity(newSize);

            @SuppressWarnings("unchecked")
            T[] tempQueue = (T[]) new Object[newSize];
            deque = tempQueue;

            for (int index = 0; index < oldSize-1; index++) {
                deque[index] = oldQueue[frontIndex];
                frontIndex = (frontIndex+1) % oldSize;
            }

            frontIndex = 0;
            backIndex = oldSize-2;
        }
    }

    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return frontIndex == ((backIndex+1) % deque.length);
    }


    @Override
    public void clear() {
        while (!isEmpty()){
            removeFront();
        }
    }

    @Override
    public T getFront() {
        checkInitialization();
        if (isEmpty()){
            throw new EmptyDequeException();
        } else
            return deque[frontIndex];
    }

    @Override
    public T getBack() {
        checkInitialization();
        if (isEmpty()){
            throw new EmptyDequeException();
        } else
            return deque[backIndex];
    }

    @Override
    public void addToFront(T newEntry){
        checkInitialization();
        ensureCapacity();
        frontIndex = (frontIndex - 1) % deque.length;
        deque[frontIndex] = newEntry;
        size++;
    }

    @Override
    public void addToBack(T newEntry){
        checkInitialization();
        ensureCapacity();
        backIndex = (backIndex + 1) % deque.length;
        deque[backIndex] = newEntry;
        size++;
    }

    @Override
    public T removeFront() {
        checkInitialization();
        if (isEmpty()) {
            throw new EmptyDequeException();
        }else {
            T removedFront = deque[frontIndex];
            deque[frontIndex] = null;
            frontIndex = (frontIndex + 1) % deque.length;
            size--;

            return removedFront;
        }
    }

    @Override
    public T removeBack() {
        checkInitialization();
        if (isEmpty()) {
            throw new EmptyDequeException();
        }else {
            T removedBack = deque[backIndex];
            deque[backIndex] = null;
            backIndex = (backIndex - 1) % deque.length;
            size--;

            return removedBack;
        }
    }
}