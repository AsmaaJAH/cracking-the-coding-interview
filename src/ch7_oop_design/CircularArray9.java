/*
 * 7.9 Circular Array: Implement a CircularArray class that supports an array-like data structure which
can be efficiently rotated. If possible, the class should use a generic type (also called a template), and
should support iteration via the standard for (Obj o : circularArray) notation.

Note:
// Generic circular array means that it accepts T templete type

 */
package ch7_oop_design;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class CircularArray9 {

    public static class CircularArray<T> implements Iterable<T> {
        private T[] items;
        private int head = 0;

        @SuppressWarnings("unchecked")
        public CircularArray(int size) {
            items = (T[]) new Object[size]; // generic array creation
        }

        private int convertIndex(int i) {
            if (i < 0) throw new IndexOutOfBoundsException();
            return (head + i) % items.length;
        }

        public void set(int i, T value) {
            int index = convertIndex(i);
            items[index] = value;
        }

        public T get(int i) {
            int index = convertIndex(i);
            return items[index];
        }

        public void rotate(int shiftRight) {
            head = (head + shiftRight) % items.length;
        }

        public int size() {
            return items.length;
        }

        @Override
        public Iterator<T> iterator() {
            return new CircularIterator();
        }

        private class CircularIterator implements Iterator<T> {
            private int current = 0;

            @Override
            public boolean hasNext() {
                return current < items.length;
            }

            @Override
            public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                T item = get(current);
                current++;
                return item;
            }
        }
    }
}

