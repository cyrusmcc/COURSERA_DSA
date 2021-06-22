/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {


    private class Node {
        Item item;
        Node nextNode;
        Node prevNode;
    }

    private Node firstNode;
    private Node lastNode;
    private int numNodes = 0;

    // construct an empty deque
    public Deque() {
    }

    // is the deque empty?
    public boolean isEmpty() {
        if (numNodes == 0)
            return true;
        return false;
    }

    // return the number of items on the deque
    public int size() {
        return numNodes;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException();

        Node newFirst = new Node();
        newFirst.item = item;
        newFirst.prevNode = newFirst;

        if(!isEmpty()) {
            Node oldFirst = firstNode;
            newFirst.nextNode = oldFirst;
        } else {
            newFirst.nextNode = null;
            lastNode = newFirst;
        }

        firstNode = newFirst;
        numNodes++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();

        Node newLast = new Node();
        newLast.item = item;

        if (isEmpty()) {
            firstNode = newLast;
        } else {
            Node oldLast = lastNode;
            newLast.prevNode = oldLast;

            oldLast.nextNode = newLast;
        }

        lastNode = newLast;
        numNodes++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();

        Item item = firstNode.item;
        firstNode.item = null;
        firstNode = firstNode.nextNode;

        numNodes--;
        if (firstNode != null) firstNode.prevNode = firstNode;

        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException();

        if()



        /*
        Item item = lastNode.item;
        lastNode.item = null;
        lastNode = lastNode.prevNode;
        numNodes--;

        if (size() == 1)  {
            firstNode = lastNode;
        }

        return item;
    */
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return  new Iterator<Item>() {
            Node current = firstNode;

            public boolean hasNext() {
                if (isEmpty())
                    return false;
                return true;
            }

            public Item next() {
                if (!hasNext()) throw new NoSuchElementException();
                return removeFirst();
            }
        };
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<String> deck = new Deque<String>();

        deck.addLast("hi1");
        deck.addLast("hi2");
        deck.addLast("hi3");
        System.out.println("de: " + deck.removeFirst());
        System.out.println("de: " + deck.removeFirst());
        System.out.println("de: " + deck.removeFirst());


       /* System.out.println("is empty: " + deck.isEmpty());
        System.out.println("elements in deck: " + deck.size());

        deck.addLast("second");
        deck.addFirst("first");
        deck.addLast("third");

        System.out.println(deck.removeFirst());
        System.out.println(deck.removeLast());

        System.out.println("\nis empty: " + deck.isEmpty());
        System.out.println("elements in deck: " + deck.size());

        for (String s : deck) {
            System.out.println(s);
        }
        */
    }

}