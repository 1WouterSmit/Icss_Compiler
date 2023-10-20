package nl.han.ica.datastructures;

public class HANLinkedList<T> implements IHANLinkedList<T> {

    private LLNode<T> root;
    private int size;

    public HANLinkedList() {
        root = null;
        size = 0;
    }

    @Override
    public void addFirst(T value) {
        root = new LLNode<>(value, root);
        size++;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public void insert(int index, T value) {
        if( index == 0 ) addFirst(value);
        else if( index > size ) throw new IndexOutOfBoundsException("Cannot insert value at index, the index is too high");
        else {
            LLNode<T> current = root;
            for( int i=1; i<index; i++ ) {
                current = current.next;
            }
            current.next = new LLNode<>(value, current.next);
        }
        size++;
    }

    @Override
    public void delete(int pos) {
        if( pos < 0 || pos >= size ) return;
        else if( pos == 0 ) root = root.next;
        else {
            LLNode<T> current = root;
            for( int i=1; i<pos; i++ ) {
                current = current.next;
            }
            current = current.next.next;
        }
        size--;
    }

    @Override
    public T get(int pos) {
        if( size < 0 || pos >= size ) return null;
        if( pos == 0 ) return root.item;

        LLNode<T> current = root;
        for( int i=0; i<pos; i++ ) {
            current = current.next;
        }
        return current.item;
    }

    @Override
    public void removeFirst() {
        if( size == 0 ) return;
        if( size == 1 ) {
            root = null;
            size = 0;
        } else {
            root = root.next;
            size--;
        }
    }

    @Override
    public T getFirst() {
        return root.item;
    }

    @Override
    public int getSize() {
        // SIZE - 1 ?????? "..., but not the header node..."
        return size;
    }

    class LLNode<T> {
        T item;
        LLNode<T> next;

        public LLNode() {};
        public LLNode(T value) {
            this.item = value;
        }
        public LLNode(T value, LLNode next) {
            this.item = value;
            this.next = next;
        }
    }
}
