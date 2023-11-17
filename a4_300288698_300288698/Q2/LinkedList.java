import java.util.NoSuchElementException;

public class LinkedList<E> implements List<E> {

    private static class Node<T> {

        private T value;
        private Node<T> prev;
        private Node<T> next;

        private Node(T value, Node<T> prev, Node<T> next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }

    private Node<E> head;
    private int size;

    public LinkedList() {
        head = new Node<E>(null, null, null); // dummy node!
        head.prev = head.next = head;
        size = 0;
    }

    private class LinkedListIterator implements Iterator<E> {

        private Node<E> current = head;
        private int counter = 0;

        public boolean hasNext() {
            return (current.next != head);
        }

        public E next() {

            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            current = current.next;
            counter++;

            return current.value;
        }

        public int nextIndex(){
            return counter;
        }
    }

    public Iterator<E> iterator() {
        return new LinkedListIterator();
    }

    public Iterator<E> iterator(int nextIndex){
        if (nextIndex < 0 || nextIndex > size){
            throw new IndexOutOfBoundsException();
        }

        LinkedListIterator x = new LinkedListIterator();
        Node<E> current = head.next;
        int counter = 0;

        while (counter < nextIndex-1){ //nextIndex is literally the next Index so if we decrease by 1 it's the actual index
            current = current.next;
            counter++;
        }

        x.current = current;
        x.counter = counter+1; //add 1 to make the counter match up to nextIndex

        return x;
    }

    public Iterator<E> iterator(Iterator<E> other){
        LinkedListIterator x = new LinkedListIterator();
        LinkedListIterator y = (LinkedListIterator)other;
        x.current = y.current;
        x.counter = y.counter;

        return x;
    }

    public int size() {
        return size;
    }

    public E get(int index) {

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }

        Node<E> p = head.next;

        for (int i = 0; i < index; i++) {
            p = p.next;
        }

        return p.value;
    }

    public void addFirst(E elem) {

        if (elem == null) {
            throw new NullPointerException();
        }

        Node<E> second = head.next;

        head.next = new Node<E>(elem, head, second);
        second.prev = head.next;

        size++;
    }

    public void add(E elem) {

        if (elem == null) {
            throw new NullPointerException();
        }

        Node<E> before = head.prev, after = head;

        before.next = new Node<E>(elem, before, after);
        after.prev = before.next;

        size++;
    }
    
}
