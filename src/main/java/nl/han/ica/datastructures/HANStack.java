package nl.han.ica.datastructures;

import java.util.ArrayList;

public class HANStack<T> implements IHANStack<T> {

    private ArrayList<T> items;

    public HANStack() {
        items = new ArrayList<T>();
    }
    @Override
    public void push(T value) {
        items.add(value);
    }

    @Override
    public T pop() {
        if(items.isEmpty()) return null;
        return items.remove(items.size() - 1 );
    }

    @Override
    public T peek() {
        if(items.isEmpty()) return null;
        return items.get( items.size() - 1 );
    }
}
