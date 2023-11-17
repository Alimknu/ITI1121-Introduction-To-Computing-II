import java.util.NoSuchElementException;

public class OrderedList implements OrderedStructure {

    // Implementation of the doubly linked nodes (nested-class)

    private static class Node {

      	private Comparable value;
      	private Node previous;
      	private Node next;

      	private Node ( Comparable value, Node previous, Node next ) {
      	    this.value = value;
      	    this.previous = previous;
      	    this.next = next;
      	}
    }

    // Instance variables

    private Node head;

    // Representation of the empty list.

    public OrderedList() {
    }

    // Calculates the size of the list

    public int size() {
      	int x = 0;
        Node current = head;

        while (current != null){
            x++;
            current = current.next;
        }
        return x;
    }


    public Object get( int pos ) {
        int i = 0;
        Node current = head;

        if (pos < 0 || pos >= size()){
            throw new IndexOutOfBoundsException();
        }

        while (i != pos){
            i++;
            current = current.next;
        }
        return current.value;
    }

    // Adding an element while preserving the order

    public boolean add( Comparable o ) {
        Node current;
        
        if (o == null){
            throw new IllegalArgumentException();
        }

        if (head == null){
            head = new Node(o, null, null);
            return true;
        }

        else if(head.value.compareTo(o) >= 0){
            Node x = new Node(o, null, head);
            x.next.previous = x;
            head = x;
            return true;
        }

        else{
            current = head;

            while(current.next!= null && current.next.value.compareTo(o) < 0){
                current = current.next;
            }

            Node x = new Node(o, null, current.next);

            if (current.next != null){
                x.next.previous = x;
            }

            current.next = x;
            x.previous = current;
            return true;
        }
    }

    //Removes one item from the position pos.

    public void remove( int pos ) {
      Node current = head;
      int i = 0;

      if (pos < 0 || pos >= size()){
        throw new IndexOutOfBoundsException();
    }

      if (head == null){
        return;
      }

      if (pos == 0){
        head = head.next;
      }

      while (current != null && i != pos){
        current = current.next;
        i++;
      }

      if (current.next != null){
        current.next.previous = current.previous;
      }

      if (current.previous != null){
        current.previous.next = current.next;
      }

    }

    // Knowing that both lists store their elements in increasing
    // order, both lists can be traversed simultaneously.

    public void merge( OrderedList other ) {
      Node current;
      Node currentTwo = other.head;

      while (currentTwo != null){
        if (head == null){
            head = new Node(currentTwo.value, null, null);
            currentTwo = currentTwo.next;
        }

        else if (head.value.compareTo(currentTwo.value) >= 0){
            Node x = new Node(currentTwo.value, null, head);
            x.next.previous = x;
            head = x;
            currentTwo = currentTwo.next;
        }

        else{
            current = head;

            while (current.next != null && current.next.value.compareTo(currentTwo.value) < 0){
                current = current.next;
            }

            Node x = new Node(currentTwo.value, null, current.next);

            if (current.next != null){
                x.next.previous = x;
            }

            current.next = x;
            x.previous = current;
            currentTwo = currentTwo.next;
        }
      }
    }
}