import org.w3c.dom.Node;

public class LinkedListDeque <Item> implements Deque<Item> {
    private static class Node <Item> {
        public Item value;
        public Node<Item> prev;
        public Node<Item> next;
        public Node() {
            value = null;
        }
        public Node(Item value, Node<Item> prev, Node<Item> next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }

    private Node<Item> head;
    private Node<Item> tail;
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

    @Override
    public boolean isEmpty () {
        return size == 0;
    }

    @Override
    public int size () {
        return size;
    }

    @Override
    public void addFirst (Item value) {
        Node<Item> node = new Node<>(value, head, head.next);
        head.next = node;
        node.next.prev = node;
        size++;
    }

    @Override
    public void addLast (Item value) {
        Node<Item> node = new Node<>(value, tail.prev, tail);
        tail.prev.next = node;
        tail.prev = node;
        size++;
    }

    @Override
    public void printDeque() {
        Node<Item> ptr = head.next;
        while (ptr != tail) {
            System.out.print(ptr.value + " ");
            ptr = ptr.next;
        }
    }

    @Override
    public Item removeFirst () {
        if (isEmpty()) {
            return null;
        }
        Node<Item> ptr = head.next;
        head.next = ptr.next;
        ptr.next.prev = head;
        size--;
        return ptr.value;
    }

    @Override
    public Item removeLast () {
        if (isEmpty()) {
            return null;
        }
        Node<Item> ptr = tail.prev;
        tail.prev = ptr.prev;
        ptr.prev.next = tail;
        size--;
        return ptr.value;
    }

    @Override
    public Item get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        Node<Item> ptr = head.next;
        for (int i = 0; i < index; i++) {
            ptr = ptr.next;
        }
        return ptr.value;
    }

    private Item getRecursiveHelper (Node<Item> ptr, int index) {
        if (index == 0) {
            return ptr.value;
        } else {
            return getRecursiveHelper(ptr.next, index - 1);
        }
    }

    public Item getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return getRecursiveHelper(head.next, index);
    }
}
