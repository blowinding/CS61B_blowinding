public class ArrayDeque <T>{
    private static final int DEFAULT_CAPACITY = 8;
    private static final int MIN_USAGE_FACTOR = 4;
    private T[] array;
    private int size;
    // head and tail point to the next space to fill
    private int head;
    private int tail;

    public ArrayDeque(){
        array = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
        head = array.length - 1;
        tail = 0;
    }

    private int getIncrement(int ptr) {
        return (ptr + 1) % array.length;
    }

    private int getIncrement(int ptr, int incr) {
        return (ptr + incr) % array.length;
    }

    private int getDecrement(int ptr) {
        return (ptr - 1 + array.length) % array.length;
    }

    private int getDecrement(int ptr, int decr) {
        return (ptr - decr + array.length) % array.length;
    }

    private void resize(int newCapacity){
        T[] newArray = (T[]) new Object[newCapacity];
        int cnt = size;
        int i = getDecrement(tail), j = 0;
        tail = j;
        for(; cnt > 0; cnt--, i = getDecrement(i), j = (j + newCapacity - 1) % newCapacity){
            newArray[j] = array[i];
        }
        array = newArray;
        head = j;
        tail = getIncrement(tail);
    }

    public void addFirst(T item){
        if(size == array.length){
            resize(array.length * 2);
        }
        array[head] = item;
        head = getDecrement(head);
        size++;
    }

    public void addLast(T item){
        if(size == array.length){
            resize(array.length * 2);
        }
        array[tail] = item;
        tail = getIncrement(tail);
        size++;
    }

    public T removeLast(){
        if(size == 0){
            return null;
        }
        tail = getDecrement(tail);
        T result = array[tail];
        array[tail] = null;
        size--;
        if (size < array.length / MIN_USAGE_FACTOR && array.length > DEFAULT_CAPACITY){
            resize(array.length / 2);
        }
        return result;
    }

    public T removeFirst(){
        if(size == 0){
            return null;
        }
        head = getIncrement(head);
        T result = array[head];
        array[head] = null;
        size--;
        if (size < array.length / MIN_USAGE_FACTOR && array.length > DEFAULT_CAPACITY){
            resize(array.length / 2);
        }
        return result;
    }

    public T get(int index){
        if(size == 0){
            return null;
        }
        index = getIncrement(index, head + 1);
        return array[index];
    }

    public int size(){
        return size;
    }

    public void printDeque() {
        for(int i = getIncrement(head); i < getDecrement(tail); i = getIncrement(i)){
            System.out.print(array[i] + " ");
        }
    }
}
