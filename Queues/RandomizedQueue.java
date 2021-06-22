/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private int size;
    private Item[] queue = (Item[]) new Object[1];

    // construct an empty randomized queue
    public RandomizedQueue() {
    }

    private void resize(int max) {
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < size; i++) {
            temp[i] = queue[i];
        }
        queue = temp;
     }

    // is the randomized queue empty?
    public boolean isEmpty() {
        if (size == 0)
            return true;
        return false;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();

        if (size == queue.length) resize(2*queue.length);
        queue[size] = item;
        size++;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();

        int randomIndex = StdRandom.uniform(0, size);

        Item temp = queue[randomIndex];
        queue[randomIndex] = queue[--size];
        queue[size] = null;
        if (size > 0 && size == queue.length/4) resize(queue.length/2);

        return temp;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException();

        int randomIndex = StdRandom.uniform(0, size);
        Item item = queue[randomIndex];

        return item;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            public boolean hasNext() {
                if (isEmpty())
                    return false;
                return true;
            }

            public Item next() {
                if (!hasNext()) throw new NoSuchElementException();
                return dequeue();
            }
        };
    }

    // unit testing (required)
    public static void main(String[] args) {

        RandomizedQueue<String> rQ = new RandomizedQueue<String>();

        rQ.enqueue("Hi");
        rQ.enqueue("There");
        rQ.enqueue("Bob");
        rQ.enqueue("Marley");

        System.out.println("size:" + " " + rQ.size());

        System.out.println("de:" + " " + rQ.dequeue());

        System.out.println("sample:" + " " + rQ.sample());

        System.out.println("is empty:" + " " + rQ.isEmpty());

        System.out.println("sample:" + " " + rQ.sample() + "\n");

        for (String s: rQ) {
            System.out.println(s);
        }

        System.out.println("size:" + " " + rQ.size());
    }
}
