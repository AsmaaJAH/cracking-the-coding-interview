/*
 * 
7.12 Hash Table: Design and implement a hash table which uses chaining (linked lists) to handle
collisions.
 * 
 */
package ch7_oop_design;

public class HashTable12 {

    static class HashTableNode {
        String key;
        String value;
        HashTableNode next;

        HashTableNode(String key, String value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    static class HashTable {
        private HashTableNode[] table;
        private int size;

        public HashTable(int capacity) {
            table = new HashTableNode[capacity];
            size = 0;
        }

        private int hash(String key) {
            return Math.abs(key.hashCode()) % table.length;
        }

        public void put(String key, String value) {
            int index = hash(key);
            HashTableNode head = table[index];

            HashTableNode current = head;
            while (current != null) {
                if (current.key.equals(key)) {
                    current.value = value; // update
                    return;
                }
                current = current.next;
            }

            HashTableNode newNode = new HashTableNode(key, value);
            newNode.next = head;
            table[index] = newNode;
            size++;
        }

        public String get(String key) {
            int index = hash(key);
            HashTableNode current = table[index];

            while (current != null) {
                if (current.key.equals(key)) {
                    return current.value;
                }
                current = current.next;
            }

            return null;
        }

        public void remove(String key) {
            int index = hash(key);
            HashTableNode current = table[index];
            HashTableNode prev = null;

            while (current != null) {
                if (current.key.equals(key)) {
                    if (prev == null) {
                        table[index] = current.next;
                    } else {
                        prev.next = current.next;
                    }
                    size--;
                    return;
                }

                prev = current;
                current = current.next;
            }
        }

        public boolean containsKey(String key) {
            return get(key) != null;
        }

        public int size() {
            return size;
        }

        public void printTable() {
        }

        public boolean isEmpty() {
            return size == 0;
        }

        // Clear all entries
        public void clear() {
            for (int i = 0; i < table.length; i++) {
                table[i] = null;
            }
            size = 0;
        }

        public void printKeysOnly() {
            System.out.println("Keys:");
            for (int i = 0; i < table.length; i++) {
                HashTableNode current = table[i];
                while (current != null) {
                    System.out.print(current.key + " ");
                    current = current.next;
                }
            }
            System.out.println();
        }

        public void printValuesOnly() {
            System.out.println("Values:");
            for (int i = 0; i < table.length; i++) {
                HashTableNode current = table[i];
                while (current != null) {
                    System.out.print(current.value + " ");
                    current = current.next;
                }
            }
            System.out.println();
        }
    }

}