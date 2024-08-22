import org.w3c.dom.Node;

public class LinkedListDeque <T> {
    private static class Node <T> {
        public T value;
        public Node<T> prev;
        public Node<T> next;
        public Node() {
            value = null;
        }
        public Node(T value, Node<T> prev, Node<T> next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }

    private Node<T> head;
    private Node<T> tail;
    private int size;

    public LinkedListDeque () {
        size = 0;
        head = new Node<>();
        tail = new Node<>();
        head.next = tail;
        tail.prev = head;
        head.prev = head;
        tail.next = tail;
    }

    public boolean isEmpty () {
        return size == 0;
    }

    public int size () {
        return size;
    }

    public void addFirst (T value) {
        Node<T> node = new Node<>(value, head, head.next);
        head.next = node;
        node.next.prev = node;
        size++;
    }

    public void addLast (T value) {
        Node<T> node = new Node<>(value, tail.prev, tail);
        tail.prev.next = node;
        tail.prev = node;
        size++;
    }

    public void printDeque() {
        Node<T> ptr = head.next;
        while (ptr != tail) {
            System.out.print(ptr.value + " ");
            ptr = ptr.next;
        }
    }

    public T removeFirst () {
        if (isEmpty()) {
            return null;
        }
        Node<T> ptr = head.next;
        head.next = ptr.next;
        ptr.next.prev = head;
        size--;
        return ptr.value;
    }

    public T removeLast () {
        if (isEmpty()) {
            return null;
        }
        Node<T> ptr = tail.prev;
        tail.prev = ptr.prev;
        ptr.prev.next = tail;
        size--;
        return ptr.value;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        Node<T> ptr = head.next;
        for (int i = 0; i < index; i++) {
            ptr = ptr.next;
        }
        return ptr.value;
    }

    private T getRecursiveHelper (Node<T> ptr, int index) {
        if (index == 0) {
            return ptr.value;
        } else {
            return getRecursiveHelper(ptr.next, index - 1);
        }
    }

    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return getRecursiveHelper(head.next, index);
    }
}
