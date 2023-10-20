package nl.han.ica.datastructures;

public class HANQueue<T> implements IHANQueue<T> {

    private T[] array;
    private int currentSize, first, last;

    public HANQueue() {
        array = (T[]) new Object[10];
        clear();
    }
    @Override
    public void clear() {
        currentSize = 0;
        first = 0;
        last = -1;
    }

    @Override
    public boolean isEmpty() {
        return currentSize == 0;
    }

    @Override
    public void enqueue(T value) {
        if( currentSize == array.length ) doubleQueue();
        last = increment(last);
        array[ last ] = value;
        currentSize++;
    }

    @Override
    public T dequeue() {
        if( isEmpty() ) return null;
        currentSize--;
        T returnValue = array[ first ];
        first = increment( first );
        return returnValue;
    }

    @Override
    public T peek() {
        if( isEmpty() ) return null;
        return array[ first ];
    }

    @Override
    public int getSize() {
        return currentSize;
    }

    private int increment(int x) {
        if( ++x == array.length) x = 0;
        return x;
    }

    private void doubleQueue() {
        T [ ] newArray;
        newArray = (T []) new Object[ array.length * 2 ];

        for( int i = 0; i < currentSize; i++, first = increment( first ) )
            newArray[ i ] = array[ first ];
        array = newArray;
        first = 0;
        last = currentSize - 1;
    }
}
